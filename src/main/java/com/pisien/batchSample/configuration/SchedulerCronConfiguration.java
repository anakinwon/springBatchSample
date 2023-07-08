package com.pisien.batchSample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SchedulerCronConfiguration {

    @Autowired private GlobalConfig config;

    /** <모든 스케줄을 등록해야 함> ********************************************************************************  */
    @Bean
    public String schedulerCronExample1() { return config.GetSchedulerCronExample1(); }
    @Bean
    public String schedulerCronExample2() { return config.GetSchedulerCronExample2(); }
//    @Bean
//    public String schedulerCronExample3() { return config.GetSchedulerCronExample3(); }
    /** <모든 스케줄을 등록해야 함> ********************************************************************************  */

}
