package com.pisien.batchSample.job;

import com.pisien.batchSample.config.RunIdIncrementer;
import com.pisien.batchSample.job.entity.Member;
import com.pisien.batchSample.job.entity.MemberRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DBToJsonToDBBatch {
    private final Logger logger = LoggerFactory.getLogger("DBToJsonFileBatch 의 로그");
    private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;
    private final int chunkSize = 10;

    /**
     *   < DB를 읽어서 -> JSon 파일로 -> 다시 DB로 입력하기. >
     *       - D:\JpaNQueryDsl\batchSample\src\main\resources\files\out
     *       - memberWriteForDB.json
     *
     * */

    @Bean
    public Job job() {
        logger.info("1. DBToJsonToDBBatch Start ");
        return this.jobBuilderFactory.get("dBToJsonToDBBatch")
                .incrementer(new RunIdIncrementer())
                .start(dBToJsonFileBatchStep())
                .next(jsonToDBBatchStep())
                .build();
    }

    // DB to File ================================================================================
    @Bean
    public Step dBToJsonFileBatchStep() {
        logger.info("\t2. dBToJsonFileBatchStep Start ");
        return stepBuilderFactory.get("dBToJsonFileBatchStep")
                .<Member, Member>chunk(chunkSize)// chunk-size 설정 = Commit Interval
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean    // Extract TL
    public JdbcPagingItemReader<Member> customItemReader() {
        logger.info("\t\t3. customItemReader Start ");
        JdbcPagingItemReader<Member> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(10);
        reader.setRowMapper(new MemberRowMapper());

        // select ~ from ~ where 세팅
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause(" username, password, created_dt ");
        queryProvider.setFromClause(" from member ");
        queryProvider.setWhereClause(" where username < :username ");

        // 파라미터 세팅
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", 11);
        reader.setParameterValues(parameters);

        // Order by 세팅
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("password", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean   // ET Load
    public ItemWriter<? super Member> customItemWriter() {
        logger.info("\t\t\t4. customItemWriter Start ");
        return new JsonFileItemWriterBuilder<Member>()
                .name("staxEventWriter")
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("D:\\JpaNQueryDsl\\batchSample\\src\\main\\resources\\files\\out\\memberWriteForDB.json"))
//                .append(true)     // 기존 파일이 존재 시 새로운 내용을 추가한다.
                .build();
    }
    // DB to File ================================================================================


    // File To DB ================================================================================
    @Bean    // ETL 시작
    public Step jsonToDBBatchStep() {
        logger.info("\t\t\t\t5. jsonToDBBatchStep Start ");
        return stepBuilderFactory.get("jsonToDBBatchStep")
                .<Member, Member>chunk(chunkSize)// chunk-size 설정 = Commit Interval
                .reader(jsonItemReader())
                .processor(JpaIemProcessor())
                .writer(jpaItemWriter())
                .build();
    }

    @Bean    // Extract  TL : 저장된 파일을 추출한다.
    public ItemReader<? extends Member> jsonItemReader() {
        logger.info("\t\t\t\t\t6. jsonItemReader Started~~!");
        return new JsonItemReaderBuilder<Member>()
                .name("jsonReader")
                .resource(new FileSystemResource("D:\\JpaNQueryDsl\\batchSample\\src\\main\\resources\\files\\out\\memberWriteForDB.json"))
                .jsonObjectReader(new JacksonJsonObjectReader<>(Member.class))
                .build();
    }

    @Bean    // E Transform L : 추출된 데이터를 가공한다.
    public ItemProcessor<Member, Member> JpaIemProcessor() {
        logger.info("\t\t\t\t\t\t7. JpaIemProcessor Started~~!");
        return member -> {
            return new Member( member.getUsername()      // 새로운 부서명
                    , "anakin_"+member.getPassword()    // 새로운 부서 주소
                    , member.getCreatedDt()  // 새로운 생성자ID
            );
        };
    }

    @Bean    // ET Load : 가공된 데이터를 DB에 저장한다.
    public JpaItemWriter<Member> jpaItemWriter() {
        logger.info("\t\t\t\t\t\t\t8. jpaItemWriter Started~~!");
        JpaItemWriter<Member> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
    // File To DB ================================================================================

}
