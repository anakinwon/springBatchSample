package com.pisien.batchSample.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//@Component    // 스케줄 가동을 위해서 꼭 등록해야 할 애노테이션
public class MasterScheduler {
    private final Logger logger = LoggerFactory.getLogger("MasterScheduler 의 로그");
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");

    @Autowired private JobLauncher jobLauncher;
    @Autowired private Job DummyToDummyBatch;
    @Autowired private Job MemoryToConsoleBatch;

    @Scheduled(cron="#{@schedulerCronExample1}")
    public void schedule1() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        logger.info("\t\t 5초마다 Schedule1이 동작하고 있습니다.: {}", Calendar.getInstance().getTime());
        String id = format.format(new Date());

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", "anakin")
                .addString("date", "20230622")
                .addString("message", "20230622")
                .addString("run.id", id)                       // 재시작을 위한 년월일 시간 파라미터 전송
                .toJobParameters();

//        jobLauncher.run(DummyToDummyBatch, jobParameters);

    }

    @Scheduled(cron="#{@schedulerCronExample2}")
    public void scheduler2() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        logger.info("\t\t 7초마다 Schedule2이 동작하고 있습니다.: {}", Calendar.getInstance().getTime());
        String id = format.format(new Date());

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", "anakin")
                .addString("date", "20230622")
                .addString("message", "20230622")
                .addString("run.id", id)                       // 재시작을 위한 년월일 시간 파라미터 전송
                .toJobParameters();

//        jobLauncher.run(MemoryToConsoleBatch, jobParameters);


    }

}
