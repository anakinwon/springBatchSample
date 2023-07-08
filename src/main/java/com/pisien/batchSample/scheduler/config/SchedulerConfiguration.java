package com.pisien.batchSample.scheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


/**
 *    ThreadPoolTaskScheduler는 태스크 실행 및 스케줄링에 사용되는 스프링 라이브러리이다.
 *    Batch를 관리할 때 Thread를 개발자가 직접 제어하지 않고 실행하고 싶은 Task와 시간을 Pool에 넣는다.
 *    그러면 ThreadPoolTaskScheduler에서 제어하여 해당 시간에 맞춰서 Thread를 생성 및 실행시키고 종료까지 해준다.
 * */

/**
 *    <Job 실행>
 *        - JobLauncher는 Job을 실행하는 역할을 담당한다
 *        - jobLauncher.run(exampleJobChunkConfiguration.exampleJobChunk(), jobParameters);
 *  */

//@Configuration
public class SchedulerConfiguration implements SchedulingConfigurer {

    // PoolSize를 2로 설정하였기에 등록된 스케줄 중 동일한 시간에 스케줄이 있다면 2개의 Thread만 동작할 것이다.
    private final int POOL_SIZE = 2;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("DevLog-Scheduler-");
        threadPoolTaskScheduler.initialize();

        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }
}