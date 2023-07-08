package com.pisien.batchSample.job;

import com.pisien.batchSample.config.RunIdIncrementer;
import com.pisien.batchSample.config.StopWatchJobListener;
import com.pisien.batchSample.job.processor.CustomItemProcessor;
import com.pisien.batchSample.job.reader.CustomItemReader;
import com.pisien.batchSample.job.writer.CustomItemWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 *   <MemoryToConsoleBatch 구성하기>
 *       - ItemReader    : CustomItemReader    - chunkSize 개수 만큼 읽어서,
 *       - ItemProcessor : CustomItemProcessor - chunkSize 개수 만큼 처리하고,
 *       - ItemWriter    : CustomItemWriter    - chunkSize 개수 만큼 쓰기한다.
 *
 * */

@Slf4j
//@Configuration                                                                                  // 단일 배치 실행 애노테이션
//@Component
@RequiredArgsConstructor
public class MemoryToConsoleBatch {
    private final Logger logger = LoggerFactory.getLogger("MemoryToConsoleBatch 의 로그"); // 로그 생성
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");      // 날짜 파라미터 생성
    private final JobBuilderFactory jobBuilderFactory;                                          // Job 생성 필수 DI
    private final StepBuilderFactory stepBuilderFactory;                                        // Step 생성 필수 DI
    private final int chunkSize = 10;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("memoryToConsoleBatchJob")
                .incrementer(new RunIdIncrementer())             // JOB 재실행을 위한 파라미터 자동생성
                .listener(new StopWatchJobListener())            // 수행 시간 확인하기
                .start(memoryToConsoleBatchStep1())
                .next(memoryToConsoleBatchStep2())
                .build();
    }

    /**
     * <청크 기반 테스트하기>
     *     1. ListItemReader    : E-Extract
     *       > 2. ItemProcessor : T-Transform
     *          > 3. ItemWriter : L-Load
     * */
    @Bean
    public Step memoryToConsoleBatchStep1() {
        return stepBuilderFactory.get("memoryToConsoleBatchStep1")
                .<Customer, Customer>chunk(chunkSize)                      // chunk-size 설정 = Commit Interval
                .reader(itemReader())
                .processor(itemProcess())
                .writer(itemWriter())
                .build();
    }
    @Bean
    public Step memoryToConsoleBatchStep2() {
        return stepBuilderFactory.get("memoryToConsoleBatchStep2")
                .tasklet((contribution, chunkContext) -> {
                    logger.info(" memoryToConsoleBatchStep2 was Executed~!");
                    return RepeatStatus.FINISHED;
                }).build();
    }


    @Bean    // 총 6개 읽은 데이터 중 2개씩 청크로 묶어서
    public ItemReader<Customer> itemReader() {
        return new CustomItemReader(Arrays.asList(new Customer("anakin")
                , new Customer("Padme")
                , new Customer("Yoda")
                , new Customer("Obiwan")
                , new Customer("Griverse")
                , new Customer("Duke")
                , new Customer("Asoka")
                , new Customer("Qwigon")
        )) ;
    }

    @Bean   // 읽은 2개를 대문자로 변환하고,
    public ItemProcessor<? super Customer, ? extends Customer> itemProcess() {
        return new CustomItemProcessor() ;
    }

    @Bean   // 대문자로 변환된 문자를 처리한다.
    public ItemWriter<? super Customer> itemWriter() {
        return new CustomItemWriter() ;
    }

}
