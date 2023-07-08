package com.pisien.batchSample.job;

import com.pisien.batchSample.config.RunIdIncrementer;
import com.pisien.batchSample.config.StopWatchJobListener;
import com.pisien.batchSample.domain.Dept;
import com.pisien.batchSample.domain.Dept2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;

/**
 *  <DB to DB 샘플>
 *      - 원천 테이블 : Dept
 *      - 목적 테이블 : Dept2
 *
 * */

@Slf4j
//@Configuration
@RequiredArgsConstructor
public class DeptTableCopyJob {
    private final Logger logger = LoggerFactory.getLogger("DeptTableCopyJob 의 로그");
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private final int chunkSize = 10000;

    @Bean
    public Job job() {
//        logger.info("\t1. deptTableCopyJob Started~~!");
        return jobBuilderFactory.get("deptTableCopyJob")
                .incrementer(new RunIdIncrementer())             // JOB 재실행을 위한 파라미터 자동생성
                .listener(new StopWatchJobListener())            // 수행 시간 확인하기
                .start(deptTableCopyStep(null))
                .build();
    }

    @Bean
    @JobScope    // 매개변수 사용 시 반드시 @JobScope 애노테이션 작성
    public Step deptTableCopyStep(@Value("#{jobParameters['userid']}") String userid) {
//        logger.info("\t\t2. deptTableCopyStep Started~~!");
        return stepBuilderFactory.get("deptTableCopyStep")
                .<Dept, Dept2>chunk(chunkSize)
                .reader(itemReader(null))
                .processor(itemProcessor(null))
                .writer(itemWriter())
                .build();
    }

    /**  Reader(Extract)
     *      - DB를 10개 단위로 읽어서
     *      - 원천 데이터 : Dept
     *  */
    @Bean
    @JobScope    // 매개변수 사용 시 반드시 @JobScope 애노테이션 작성
    public JpaPagingItemReader<Dept> itemReader(@Value("#{jobParameters['userid']}") String userid) {
//        logger.info("\t\t\t3. itemReader Started~~!");
        return new JpaPagingItemReaderBuilder<Dept>()
                .pageSize(chunkSize)
                .name("jpaPagingItemReaderBuilder")
                .entityManagerFactory(entityManagerFactory)
                .queryString(" select d from Dept d order by dept_no asc ")
                .build()
                ;
    }

    /**  Processor (Transform)
     *      - 읽어 온 데이터를 가공처리 한 후
     *      - 원천 데이터 : Dept 100,000 건
     *      - 목적 데이터 : Dept2
     *  */
    @Bean
    @JobScope    // 매개변수 사용 시 반드시 @JobScope 애노테이션 작성
    public ItemProcessor<Dept, Dept2> itemProcessor(@Value("#{jobParameters['userid']}") String userid) {
//        logger.info("\t\t\t\t4. itemProcessor Started~~!");
        return dept -> {
            return new Dept2( userid + dept.getDName()      // 새로운 부서명
                            , userid + dept.getAddress()    // 새로운 부서 주소
                            , userid + dept.getCreatedBy()  // 새로운 생성자ID
                            );
        };
    }

    /**  Writer (Load)
     *      - DB에 입력한다.
     *      - 목적 데이터 : Dept2
     *  */
    @Bean
    @JobScope    // 매개변수 사용 시 반드시 @JobScope 애노테이션 작성
    public JpaItemWriter<Dept2> itemWriter() {
        JpaItemWriter<Dept2> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }




}
