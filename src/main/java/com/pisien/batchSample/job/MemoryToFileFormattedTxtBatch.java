package com.pisien.batchSample.job;

import com.pisien.batchSample.job.entity.Client;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
 *     FlatFiles Fixed Length Test
 *    <메모리를 읽어서 고정길이 파일 만들기 테스트>
 *       - D:\JpaNQueryDsl\batchSample\src\main\resources\files\out
 *       - clientFomattedw.txt
 */
//@Configuration
@RequiredArgsConstructor
public class MemoryToFileFormattedTxtBatch {
    private final Logger logger = LoggerFactory.getLogger("MemoryToFileFormattedTxtBatch 의 로그");
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final int chunkSize = 10;

    @Bean
    public Job job() throws Exception {
        logger.info("1. memoryToFileFormattedTxtBatchJob : 파일쓰기 시작.");
        return jobBuilderFactory.get("memoryToFileFormattedTxtBatchJob")
                .incrementer(new RunIdIncrementer())
                .start(memoryToFileFormattedTxtBatchStep())
                .build();
    }

    @Bean
    public Step memoryToFileFormattedTxtBatchStep() throws Exception {
        logger.info("\t2. memoryToFileFormattedTxtBatchStep : 스텝 시작.");
        return stepBuilderFactory.get("memoryToFileFormattedTxtBatchStep")
                .<Client, Client>chunk(10)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemReader<? extends Client> customItemReader() {

        logger.info("\t\t3. customItemReader : 파일을 작성할 내용 읽기");
        List<Client> clients = Arrays.asList(
                new Client(11L, "Anakin2", 28),
                new Client(12L, "Padme2", 32),
                new Client(13L, "Asoka2", 18),
                new Client(14L, "Obiwan2", 52),
                new Client(15L, "Yoda2", 232),
                new Client(16L, "Duke2", 67),
                new Client(17L, "Grivers2", 54)
        );
        ListItemReader<Client> reader = new ListItemReader<>(clients);

        return reader;
    }

    /**
     * 고정 길이 파일로 만들기.
     */
    @Bean
    public ItemWriter customItemWriter() {
        logger.info("\t\t\t4. customItemWriter : 고정길이 파일을 쓰기");
        return new FlatFileItemWriterBuilder<Client>()
                .name("flatFilesWriter")
//                .resource(new ClassPathResource("/files/out/clientFomattedw.txt"))  // 파일 저장 위치
                .resource(new FileSystemResource("D:\\JpaNQueryDsl\\batchSample\\src\\main\\resources\\files\\out\\clientFomattedw.txt"))  // 파일 저장 위치
                .append(true)                               // 이미 파일이 있으면, 내용 추가해라!!~~
                .formatted()                                // 파일 구분 방식
                .format("%-3d%-10s%-3d")                    // 파일 자릿수
                .names(new String[]{"id", "name", "age"})     // 필드 제목
                .build();
    }

}
