package com.pisien.batchSample.job.writer;

import com.pisien.batchSample.job.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class CustomItemWriter implements ItemWriter<Customer> {
    private final Logger logger = LoggerFactory.getLogger("CustomItemWriter 의 로그");

    @Override
    public void write(List<? extends Customer> items) throws Exception {
        items.forEach(item -> logger.info(" \t\t\t 3. write.item = " + item));
    }
}
