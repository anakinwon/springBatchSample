package com.pisien.batchSample.job;

import com.pisien.batchSample.config.RunIdIncrementer;
import com.pisien.batchSample.config.StopWatchJobListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**  <배치작업 상태코드>
 *   1. BatchStatus  : COMPLETED, STARTING, STARTED, STOPPING, STOPPED, FAILED, ABANDONED, UNKNOWN
 *   2. ExitStatus   : UNKNOWN, EXECUTING, COMPLETED, NOOP, FAILED, STOPPED
 *   3. FlowExecutionStatus : COMPLETED, STOPPED, FAILED, UNKNOWN

 *   <사용자 정의 상태코드>
 *   4. CustomStatus : COMPLETED, STOPPED, FAILED, UNKNOWN
 * */

@Slf4j
//@Configuration                     // 단일 배치 실행 애노테이션
@RequiredArgsConstructor
public class DummyToDummyBatch {
    private final Logger logger = LoggerFactory.getLogger("DummyToDummyBatch 의 로그");   // 로그 생성
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");  // 날짜 파라미터 생성
    private final JobBuilderFactory jobBuilderFactory;                                      // Job 생성 필수 DI
    private final StepBuilderFactory stepBuilderFactory;                                    // Step 생성 필수 DI
    private final int chunkSize = 10;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("dummyToDummyJob")
                .incrementer(new RunIdIncrementer())                                        // JOB 재실행을 위한 파라미터 자동생성
                .listener(new StopWatchJobListener())                                       // 수행 시간 확인하기
                .start(dummyToDummyStep1(null))
                .next(dummyToDummyStep2(null))
                .build();
    }

    @Bean
    @JobScope    // 매개변수 사용 시 반드시 @JobScope 애노테이션 작성
    public Step dummyToDummyStep1(@Value("#{jobParameters['param']}") String param) {
        return stepBuilderFactory.get("dummyToDummyStep1")
                .tasklet((contribution, chunkContext) -> {
                    logger.info(" \t dummyToDummyStep1 has Executed!! param =" + param);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    @JobScope    // 매개변수 사용 시 반드시 @JobScope 애노테이션 작성
    public Step dummyToDummyStep2(@Value("#{jobParameters['param']}") String param) {
        return stepBuilderFactory.get("dummyToDummyStep2")
                .tasklet((contribution, chunkContext) -> {
                    logger.info(" \t dummyToDummyStep2 has Executed!! param =" + param);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
