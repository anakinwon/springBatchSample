package com.pisien.batchSample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling         // 스케줄러 사용 시 등록해야 할 필수 애노테이션.
@EnableBatchProcessing    // Spring Batch 를 사용하기 위한 필수 애노터이션
@SpringBootApplication
public class BatchSampleApplication {
	private final Logger logger = LoggerFactory.getLogger("MasterScheduler 의 로그");
	static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");

	public static void main(String[] args) {
		String id = format.format(new Date());
		SpringApplication.run(BatchSampleApplication.class, args);
	}

}
