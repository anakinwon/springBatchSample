package com.pisien.batchSample.job;

import com.pisien.batchSample.config.RunIdIncrementer;
import com.pisien.batchSample.config.StopWatchJobListener;
import com.pisien.batchSample.job.entity.Team;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 *     FlatFiles Delimited Test
 *    <메모리를 읽어서 구분자("|")파일 만들기 테스트>
 *       - D:\JpaNQueryDsl\batchSample\src\main\resources\files\out
 *       - customerw.txt
 *
 * */
//@Configuration
@RequiredArgsConstructor
public class MemoryToFileDelimitedTxtBatch {
    private final Logger logger = LoggerFactory.getLogger("MemoryToFileDelimitedTxtBatch 의 로그");
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final int chunkSize = 10;

    @Bean
    public Job job() throws Exception {
        logger.info("1. FileJsonToDBBatch : 파일쓰기 시작.");
        return jobBuilderFactory.get("memoryToFileDelimitedTxtBatchJob")
                .incrementer(new RunIdIncrementer())             // JOB 재실행을 위한 파라미터 자동생성
                .listener(new StopWatchJobListener())            // 수행 시간 확인하기
                .start(memoryToFileDelimitedTxtBatchStep())
                .build();
    }

    @Bean
    public Step memoryToFileDelimitedTxtBatchStep() throws Exception{
        logger.info("\t2. memoryToFileDelimitedTxtBatchStep : 스텝 시작.");
        return stepBuilderFactory.get("memoryToFileDelimitedTxtBatchStep")
                .<Team, Team>chunk(10)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemReader<? extends Team> customItemReader() {

        logger.info("\t\t3. customItemReader : 파일을 작성할 내용 읽기");
        List<Team> customers = Arrays.asList(
                new Team(1L, "Anakin" , 28),
                new Team(2L, "Padme"  , 32),
                new Team(3L, "Asoka"  , 18),
                new Team(4L, "Obiwan" , 52),
                new Team(5L, "Yoda"   , 232),
                new Team(6L, "Duke"   , 67),
                new Team(7L, "Grivers", 54)
        );
        ListItemReader<Team> reader = new ListItemReader<>(customers);

        return reader;
    }

    @Bean
    public ItemWriter<? super Team> customItemWriter() {
        logger.info("\t\t\t4. customItemWriter : 파일을 쓰기");
        return new FlatFileItemWriterBuilder<Team>()
                .name("flatFilesWriter")
                .append(true)
                //.resource(new ClassPathResource("/files/out/customerw.txt"))  // 파일 저장 위치
                .resource(new FileSystemResource("D:\\JpaNQueryDsl\\batchSample\\src\\main\\resources\\files\\out\\customerw.txt"))  // 파일 저장 위치
                .delimited()                                // 파일 구분 방식
                .delimiter("|")                             // 파일 구분자
                .names(new String[]{"id","name","age"})     // 필드 제목
                .build();
    }


}
