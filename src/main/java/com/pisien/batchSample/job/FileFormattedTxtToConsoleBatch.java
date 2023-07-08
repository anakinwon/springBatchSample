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
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 *   <고정길이 Text파일 생성하기.>
 *
 * */

@Slf4j
//@Configuration                                                                                  // 단일 배치 실행 애노테이션
@RequiredArgsConstructor
public class FileFormattedTxtToConsoleBatch {
    private final Logger logger = LoggerFactory.getLogger("FileFormattedTxtToConsoleBatch 의 로그"); // 로그 생성
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");      // 날짜 파라미터 생성
    private final JobBuilderFactory jobBuilderFactory;                                          // Job 생성 필수 DI
    private final StepBuilderFactory stepBuilderFactory;                                        // Step 생성 필수 DI
    private final int chunkSize = 10;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("fileFormattedTxtToConsoleBatchJob")
                .incrementer(new RunIdIncrementer())             // JOB 재실행을 위한 파라미터 자동생성
                .listener(new StopWatchJobListener())            // 수행 시간 확인하기
                .start(fileFormattedTxtToConsoleBatchStep1())
                .next(fileFormattedTxtToConsoleBatchStep2())
                .build();
    }

    @Bean
    public Step fileFormattedTxtToConsoleBatchStep1() {
        return stepBuilderFactory.get("fileFormattedTxtToConsoleBatchStep1")
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

    /**
     *  스프링배치에서 제공하는 API 이용 방식
     *     - BeanWrapperFieldSetMapper
     * */
    @Bean
    public ItemReader itemReader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("flateFile")
                .resource(new ClassPathResource("files/in/customer.txt"))   // 읽어올 파일
                //.fieldSetMapper(new CustomerFieldSetMapper())     // 직접 만들어서 매핑하는 파일
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())  // 이 내장함수를 이용하면, 파일 만들 필요 없다.
                .targetType(Customer.class)                         // 그 대신 요거 추가...
                .linesToSkip(1)                                     // 첫째 줄 제목 제외
                .fixedLength()                                      // 구분자
//                .strict(true)                                       // 길이 체크를 엄격하게 함. Parsing error at line: 2
                .addColumns(new Range(1,7))                         // 범위 Range(1) 마지막은 생략하면 끝까지 읽어옴
                .addColumns(new Range(8,10))                        // 범위 Range(8) 마지막은 생략하면 끝까지 읽어옴
                .addColumns(new Range(11,14))                       // 범위 Range(11) 마지막은 생략하면 끝까지 읽어옴
                .names("name","age","year")                         // 필드 제목
                .build();
    }

    @Bean
    public Step fileFormattedTxtToConsoleBatchStep2() {
        return stepBuilderFactory.get("fileFormattedTxtToConsoleBatchStep2")
                .tasklet((contribution, chunkContext) -> {
                    logger.info(" fileFormattedTxtToConsoleBatchStep2 was Executed~!");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}