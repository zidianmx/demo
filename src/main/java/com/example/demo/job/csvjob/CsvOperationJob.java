package com.example.demo.job.csvjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.job.csvjob.listener.TestListener;
import com.example.demo.job.csvjob.step.PreprocessorStep;

@Configuration
@EnableBatchProcessing
public class CsvOperationJob {
//    @Value("${spring.batch.rootPath}")
//    private String rootPath;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public PreprocessorStep preprocessorStep;
    @Autowired
    public TestListener listener;

//    @Bean 此注解为注册bean
    public Job outputUserJob() {
        return jobBuilderFactory.get("outputUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(preprocessorStep.validatorStep())
                .next(preprocessorStep.validatorStep1())
                .end()
                .build();
    }

}
