package com.pisien.batchSample.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

//@Component
public class ExampleScheduler {
    private final Logger logger = LoggerFactory.getLogger("MemoryToFileFormattedTxtBatch 의 로그");

    @Scheduled(cron = "#{@schedulerCronExample1}")
    public void schedule1() {
        logger.info("Schedule1이 시작되었습니다! {}", Calendar.getInstance().getTime());

    }

}
