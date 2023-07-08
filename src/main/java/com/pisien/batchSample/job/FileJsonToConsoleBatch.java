package com.pisien.batchSample.job;

import com.pisien.batchSample.config.RunIdIncrementer;
import com.pisien.batchSample.config.StopWatchJobListener;
import com.pisien.batchSample.job.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.text.SimpleDateFormat;

/**
 *   <JSON 파일 읽고 콘솔에 쓰기>
 *
 * */

@Slf4j
//@Configuration                                                                                  // 단일 배치 실행 애노테이션
@RequiredArgsConstructor
public class FileJsonToConsoleBatch {
    private final Logger logger = LoggerFactory.getLogger("FileJsonToConsoleBatch 의 로그"); // 로그 생성
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");        // 날짜 파라미터 생성
    private final JobBuilderFactory jobBuilderFactory;                                            // Job 생성 필수 DI
    private final StepBuilderFactory stepBuilderFactory;                                          // Step 생성 필수 DI
    private final int chunkSize = 10;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("fileJsonToConsoleBatchJob")
                .incrementer(new RunIdIncrementer())             // JOB 재실행을 위한 파라미터 자동생성
                .listener(new StopWatchJobListener())            // 수행 시간 확인하기
                .start(fileJsonToConsoleBatchStep())
                .build();
    }

    /**
     * <청크 기반 테스트하기>
     *     1. ListItemReader    : E-Extract
     *       > 2. ItemProcessor : T-Transform
     *          > 3. ItemWriter : L-Load
     * */
    @Bean
    public Step fileJsonToConsoleBatchStep() {
        return stepBuilderFactory.get("fileJsonToConsoleBatchStep")
                .<Customer, Customer>chunk(3)
                .reader(jsonItemReader())
                .writer(jsonItemWriter())
                .build();
    }

    @Bean
    public ItemReader<? extends Customer> jsonItemReader() {
        return new JsonItemReaderBuilder<Customer>()
                .name("jsonReader")
                .resource(new ClassPathResource("/files/in/customer.json"))
                .jsonObjectReader(new JacksonJsonObjectReader<>(Customer.class))
                .build();
    }

    /**
     *  스프링배치에서 제공하는 API 이용 방식
     *     - BeanWrapperFieldSetMapper
     * */
    @Bean
    public ItemWriter<Customer> jsonItemWriter() {
        return items -> {
            for (Customer item : items) {
                System.out.println("item = " + item.toString());
            }
        };
    }

}
