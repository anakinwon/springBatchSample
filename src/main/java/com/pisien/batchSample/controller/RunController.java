package com.pisien.batchSample.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RunController {

    @Autowired
    JobLauncher jobLauncher;

//    @Autowired
//    @Qualifier("dBToDBSynchronizedBatchJob")
//    Job dBToDBSynchronizedBatchJob;

    @Autowired
    Job fileDelimitedCsvToConsoleBatchJob;

//    @RequestMapping("/ondemandbatch")
//    @ResponseBody
//    public void handle() throws Exception {
//        JobParameter param = new JobParameter(System.currentTimeMillis());
//        Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
//        parameters.put("requestDate", param);
//        jobLauncher.run(dBToDBSynchronizedBatchJob, new JobParameters(parameters));
//    }

    @RequestMapping("/fileDelimitedCsvToConsoleBatchJob")
    @ResponseBody
    public void fileDelimitedCsvToConsoleBatchJob() throws Exception {
        JobParameter param = new JobParameter(System.currentTimeMillis());
        Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
        parameters.put("requestDate", param);
        jobLauncher.run(fileDelimitedCsvToConsoleBatchJob, new JobParameters(parameters));

    }


}