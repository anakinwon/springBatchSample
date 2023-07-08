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
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 *    <CSV 파일 읽고 콘솔에 쓰기>
 *
 * */
@Slf4j
//@Configuration                                                                                  // 단일 배치 실행 애노테이션
@RequiredArgsConstructor
public class FileDelimitedCsvToConsoleBatch {
    private final Logger logger = LoggerFactory.getLogger("FileDelimitedCsvToConsoleBatch 의 로그"); // 로그 생성
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");      // 날짜 파라미터 생성
    private final JobBuilderFactory jobBuilderFactory;                                          // Job 생성 필수 DI
    private final StepBuilderFactory stepBuilderFactory;                                        // Step 생성 필수 DI
    private final int chunkSize = 10;


    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("fileDelimitedCsvToConsoleBatchJob")
                .incrementer(new RunIdIncrementer())             // JOB 재실행을 위한 파라미터 자동생성
                .listener(new StopWatchJobListener())            // 수행 시간 확인하기
                .start(fileDelimitedCsvToConsoleBatchStep1())
                .next(fileDelimitedCsvToConsoleBatchStep2())
                .build();
    }

    @Bean
    public Step fileDelimitedCsvToConsoleBatchStep1() {
        return stepBuilderFactory.get("fileDelimitedCsvToConsoleBatchStep1")
                .<String, String>chunk(chunkSize)// chunk-size 설정 = Commit Interval
                .reader(itemReader())
//                .processor(itemProcess())
                .writer( new ItemWriter() {
                    @Override
                    public void write(List items) throws Exception {
                        for (Object item : items) {
                            logger.info("item = " + item);
                        }
                        Thread.sleep(2000);
                    }
                })
                .build();
    }

    @Bean
    public ItemReader itemReader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("flatFileItem")
                .resource(new ClassPathResource("/files/in/customer.csv"))   // 읽어올 파일
                //.fieldSetMapper(new CustomerFieldSetMapper())     // 직접 만들어서 매핑하는 파일
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())  // 이 내장함수를 이용하면, 파일 만들 필요 없다.
                .targetType(Customer.class)                            // 그 대신 요거 추가...
                .linesToSkip(1)                                     // 첫째 줄 제목 제외
                .delimited().delimiter(",")                         // 구분자
                .names("name","age","year")                         // 필드 제목
                .build();
    }

    @Bean
    public Step fileDelimitedCsvToConsoleBatchStep2() {
        return stepBuilderFactory.get("fileDelimitedCsvToConsoleBatchStep2")
                .tasklet((contribution, chunkContext) -> {
                    logger.info(" fileDelimitedCsvToConsoleBatchStep2 was Executed~!");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
