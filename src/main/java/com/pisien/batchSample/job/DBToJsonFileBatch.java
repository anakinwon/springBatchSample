package com.pisien.batchSample.job;

import com.pisien.batchSample.config.RunIdIncrementer;
import com.pisien.batchSample.config.StopWatchJobListener;
import com.pisien.batchSample.job.entity.Member;
import com.pisien.batchSample.job.entity.MemberRowMapper;
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
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.batch.item.support.builder.SynchronizedItemStreamReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@Configuration
@RequiredArgsConstructor
public class DBToJsonFileBatch {
    private final Logger logger = LoggerFactory.getLogger("DBToJsonFileBatch 의 로그");
    private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final int chunkSize = 10000;

    /**
     *   < DB를 읽어서 -> JSon 파일로 만들기>
     *       - D:\JpaNQueryDsl\batchSample\src\main\resources\files\out
     *       - memberWrite.json
     *
     * */

    @Bean
    public Job job() {
        logger.info("1. dBToJsonFileBatch Start ");
        return this.jobBuilderFactory.get("dBToJsonFileBatch")
                .incrementer(new RunIdIncrementer())
                .start(dBToJsonFileBatchStep())
                .build();
    }

    @Bean
    public Step dBToJsonFileBatchStep() {
        logger.info("\t2. dBToJsonFileBatchStep Start ");
        return stepBuilderFactory.get("dBToJsonFileBatchStep")
                .<Member, Member>chunk(chunkSize)// chunk-size 설정 = Commit Interval
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<Member> customItemReader() {
        logger.info("\t\t3. customItemReader Start ");
        JdbcPagingItemReader<Member> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(10);
        reader.setRowMapper(new MemberRowMapper());

        // select ~ from ~ where 세팅
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause(" username, password, created_dt ");
        queryProvider.setFromClause(" from member ");
        queryProvider.setWhereClause(" where username < :username ");

        // 파라미터 세팅
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", 1001);
        reader.setParameterValues(parameters);

        // Order by 세팅
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("password", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean
    public ItemWriter<? super Member> customItemWriter() {
        logger.info("\t\t\t4. customItemWriter Start ");
        return new JsonFileItemWriterBuilder<Member>()
                .name("staxEventWriter")
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("D:\\JpaNQueryDsl\\batchSample\\src\\main\\resources\\files\\out\\memberWrite.json"))
                .build();
    }
}
