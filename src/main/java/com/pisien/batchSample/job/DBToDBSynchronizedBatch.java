package com.pisien.batchSample.job;

import com.pisien.batchSample.config.RunIdIncrementer;
import com.pisien.batchSample.config.StopWatchJobListener;
import com.pisien.batchSample.job.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.batch.item.support.builder.SynchronizedItemStreamReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;

@Slf4j
//@Configuration
@RequiredArgsConstructor
public class DBToDBSynchronizedBatch {
    private final Logger logger = LoggerFactory.getLogger("dBToDBSynchronizedBatchJob 의 로그");
    private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final int chunkSize = 10000;

    /**
     *   < Synchronized Thread 처리 -> DB로 저장하기.>
     *       - 중복되지 않게 안전하게 처리함.
     *       - SynchronizedItemStreamReaderBuilder
     *
     * */
    @Bean
    public Job job() throws Exception{
        logger.info("1. dBToDBSynchronizedBatch Start ");
        return this.jobBuilderFactory.get("dBToDBSynchronizedBatchJob")
                .incrementer(new RunIdIncrementer())             // JOB 재실행을 위한 파라미터 자동생성
                .listener(new StopWatchJobListener())            // 수행 시간 확인하기
                .start(dBToDBSynchronizedBatchStep())
                .build();
    }

    @Bean
    public Step dBToDBSynchronizedBatchStep() throws Exception{
        logger.info("\t2. dBToDBSynchronizedBatchStep Start ");
        return stepBuilderFactory.get("dBToDBSynchronizedBatchStep")
                .<Member, Member>chunk(chunkSize)
                .reader(customItemReader())
                .listener(new ItemReadListener<Member>() {
                    @Override
                    public void beforeRead() {                    }

                    @Override
                    public void afterRead(Member item) {
                        logger.info("ThreadName = ["+ Thread.currentThread().getName() +"], item.getUsername = " + item.getUsername());
                    }

                    @Override
                    public void onReadError(Exception ex) {                    }
                })
                .writer(customItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    @StepScope
    public SynchronizedItemStreamReader<Member> customItemReader() {
        logger.info("\t\t3. customItemReader Start ");

        JdbcCursorItemReader<Member> SynchronizedThread = new JdbcCursorItemReaderBuilder<Member>()
                .fetchSize(chunkSize)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Member.class))
                .sql(" select username, password, created_dt from member ")
                .name("Synchronized-Thread")
                .build();

        return new SynchronizedItemStreamReaderBuilder<Member>()
                .delegate(SynchronizedThread)
                .build();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Member> customItemWriter() {
        logger.info("\t\t\t4. customItemWriter Start ");

        JdbcBatchItemWriter<Member> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql(" Insert into person (password) values (:password) ");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    /**
     *  쓰레드 설정
     * */
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);                          // 쓰레드 갯수 설정
        taskExecutor.setMaxPoolSize(8);                           // 최대 쓰레드 갯수
        taskExecutor.setThreadNamePrefix("SynchronizedThread");   // 접두사_쓰레드ID

        return taskExecutor;
    }

}
