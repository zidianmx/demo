package com.example.demo.job.csvjob.step;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.example.demo.job.csvjob.processor.ValidatingProcessor;
import com.example.demo.job.csvjob.reader.CsvReader;
import com.example.demo.job.csvjob.writer.TestWriter;
import com.example.demo.pojo.User;

/**
 * Created by zhangde on 2016/12/3. 上午11:11
 */
@Configuration
public class PreprocessorStep {

    private static final Log LOG = LogFactory.getLog(PreprocessorStep.class);


    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public CsvReader csvReader;

    @Autowired
    public TestWriter testWriter;

    @Autowired
    public ResourcePatternResolver resourcePatternResolver;

//    @Autowired
//    public StepListener stepListener;

//    @Value("${spring.batch.rootPath}")
//    private String rootPath;


    @Bean
    public ValidatingProcessor validatingProcessor() {
        return new ValidatingProcessor(resourcePatternResolver);
    }

    @Bean
    public Step validatorStep() {

        return stepBuilderFactory.get("validatorStep")
                .<User, User>chunk(2)    // 每读取多少条进行一次处理和写入
                .reader(csvReader.validatorFileReader())
                .processor(validatingProcessor())
                .writer(testWriter)
                .faultTolerant().skipLimit(0)
                .skip(Exception.class).allowStartIfComplete(true)
//                .taskExecutor(taskExecutor())
//                .allowStartIfComplete(true)     // 允许重新执行
                .build();
    }

	public Step validatorStep1() {
		return stepBuilderFactory.get("validatorStep1")
        .<User, User>chunk(1)    // 每读取多少条进行一次处理和写入
        .reader(csvReader.validatorFileReader1())
        .processor(validatingProcessor())
        .writer(testWriter)
        .faultTolerant().skipLimit(0)
        .skip(Exception.class).allowStartIfComplete(true)
//        .taskExecutor(taskExecutor())
//        .allowStartIfComplete(true)     // 允许重新执行
        .build();
	}


//>>>>>>>>>>>>>>>>>>>>>>>>>批量导入>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}
