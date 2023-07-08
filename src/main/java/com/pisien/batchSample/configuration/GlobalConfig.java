package com.pisien.batchSample.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Properties;

//@Component
public class GlobalConfig {
    private final Logger logger = LoggerFactory.getLogger("MasterScheduler 의 로그");
    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");

    @Autowired ApplicationContext context;
    @Autowired ResourceLoader resourceLoader;
    private String uploadFilePath;

    /** 스케줄 등록하기. ********************************************************************************************** */
    private String schedulerCronExample1;
    private String schedulerCronExample2;
//    private String schedulerCronExample3;
    /** 스케줄 등록하기. ********************************************************************************************** */

    private boolean local;
    private boolean dev;
    private boolean prod;

    public boolean isLocal() { return local; }
    public boolean isDev()   { return   dev; }
    public boolean isProd()  { return  prod; }

    @PostConstruct
    public void init() {
        logger.info("init");
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        String activeProfile = "local";
        if (!ObjectUtils.isEmpty(activeProfiles)) {
            activeProfile = activeProfiles[0];
        }

        String resourcePath = String.format("classpath:globals/global-%s.properties", activeProfile);

        try {
            Resource resource = resourceLoader.getResource(resourcePath);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            uploadFilePath = properties.getProperty("uploadFile.path");

            this.local = activeProfiles.equals("local");
            this.dev   = activeProfiles.equals("dev");
            this.prod  = activeProfiles.equals("prod");

            /** <모든 스케줄을 등록해야 함> ***************************************************************************  */
            this.uploadFilePath = properties.getProperty(resourcePath);
            this.schedulerCronExample1 = properties.getProperty("com.pisien.batchSample.scheduler.schedule1");
            this.schedulerCronExample2 = properties.getProperty("com.pisien.batchSample.scheduler.schedule2");
//            this.schedulerCronExample3 = properties.getProperty("com.pisien.batchSample.scheduler.schedule3");

            /** <모든 스케줄을 등록해야 함> ***************************************************************************  */

        } catch (Exception e) {
            logger.error("e", e);
        }
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    /** <모든 스케줄을 등록해야 함> ********************************************************************************  */
    public String GetSchedulerCronExample1() {  return schedulerCronExample1;   }

    public String GetSchedulerCronExample2() {  return schedulerCronExample2;   }

//    public String GetSchedulerCronExample3() {  return schedulerCronExample3;   }
    /** <모든 스케줄을 등록해야 함> ********************************************************************************  */


}
