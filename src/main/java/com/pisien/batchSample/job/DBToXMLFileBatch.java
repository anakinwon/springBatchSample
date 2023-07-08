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
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@Configuration
@RequiredArgsConstructor
public class DBToXMLFileBatch {
    private final Logger logger = LoggerFactory.getLogger("DBToXMLFileBatch 의 로그");
    private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final int chunkSize = 1000;

    /**
     *   < DB를 읽어서 -> XML 파일로 만들기>
     *       - D:\JpaNQueryDsl\springbatch\src\main\resources\
     *       - memberWrite.xml
     *
     * */
    @Bean
    public Job job() {
        logger.info("1. bBToXMLFileBatchJob Start ");
        return this.jobBuilderFactory.get("bBToXMLFileBatchJob")
                .incrementer(new RunIdIncrementer())
                .start(dBToXMLFileBatchStep())
                .build();
    }

    @Bean
    public Step dBToXMLFileBatchStep() {
        logger.info("\t2. dBToXMLFileBatchStep Start ");
        return stepBuilderFactory.get("dBToXMLFileBatchStep")
                .<Member, Member>chunk(chunkSize)// chunk-size 설정 = Commit Interval
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<Member> customItemReader() {
        logger.info("\t\t3. customItemReader Start ");
        JdbcPagingItemReader<Member> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(chunkSize);
        reader.setRowMapper(new MemberRowMapper());

        // select ~ from ~ where 세팅
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause(" username, password, created_dt ");
        queryProvider.setFromClause(" from member ");
        queryProvider.setWhereClause(" where username < :username ");

        // 파라미터 세팅
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", 10001);
        reader.setParameterValues(parameters);

        // Order by 세팅
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("password", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean
    public ItemWriter<? super Member> customItemWriter() {
        logger.info("\t\t\t4. customItemWriter Start ");
        return new StaxEventItemWriterBuilder<Member>()
                .name("staxEventWriter")
                .marshaller(itemMarshaller())
                .resource(new FileSystemResource("D:\\JpaNQueryDsl\\batchSample\\src\\main\\resources\\files\\out\\memberWrite.xml"))
                .rootTagName("member")
                .build();
    }

    @Bean
    public Marshaller itemMarshaller() {
        logger.info("\t\t\t\t5. itemMarshaller Start ");

        Map<String, Class<?>> aliases = new HashMap<>();

        aliases.put("member", Member.class);
        aliases.put("username", Integer.class);
        aliases.put("password", String.class);
        aliases.put("created_dt", String.class);

        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setAliases(aliases);

        return xStreamMarshaller;
    }


}
