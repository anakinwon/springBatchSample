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
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;


/**
 *  <배치 기본템플릿>
 *
 * */

@Slf4j
//@Configuration
@RequiredArgsConstructor
public class MainTaskletJob {
    private final Logger logger = LoggerFactory.getLogger("MainTaskletJob 의 로그");
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final int chunkSize = 10;

    @Bean
//    @Scheduled(cron="#{@schedulerCronExample3}")
    public Job job() {
        return jobBuilderFactory.get("mainTaskletJob")
                .incrementer(new RunIdIncrementer())             // JOB 재실행을 위한 파라미터 자동생성
                .listener(new StopWatchJobListener())            // 수행 시간 확인하기
                .start(mainTaskletStep1(null))
                .next(mainTaskletStep2(null))
                .build();
    }

    @Bean
    @JobScope    // 매개변수 사용 시 반드시 @JobScope 애노테이션 작성
    public Step mainTaskletStep1(@Value("#{jobParameters['param']}") String param) {
        return stepBuilderFactory.get("mainTaskletStep1")
                .tasklet((contribution, chunkContext) -> {
                    logger.info(" \t mainTaskletStep1 has Executed!! param =" + param);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    @JobScope    // 매개변수 사용 시 반드시 @JobScope 애노테이션 작성
    public Step mainTaskletStep2(@Value("#{jobParameters['param']}") String param) {
        return stepBuilderFactory.get("mainTaskletStep2")
                .tasklet((contribution, chunkContext) -> {
                    logger.info(" \t mainTaskletStep2 has Executed!! param =" + param);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
