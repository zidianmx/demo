package com.example.demo.job.csvjob.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.example.demo.pojo.User;

/**
 * Created by zhangde on 2016/12/3. 上午10:57
 */
@Configuration //此注解为标注此类为配置类
public class CsvReader {
    @Autowired
    private ResourcePatternResolver resourcePatternResolver;
//    @Value("${spring.batch.rootPath}")
//    private String rootPath;
    
//    @Bean 此注解为注册bean
    public FlatFileItemReader<User> validatorFileReader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(resourcePatternResolver.getResource("classpath:userFile.csv"));
        reader.setLinesToSkip(0);
        reader.setEncoding("UTF-8");
        reader.setLineMapper(new DefaultLineMapper<User>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"id","name","age"});
//                setDelimiter(",");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                setTargetType(User.class);
            }});
        }});
        System.out.println("读取一次");
        return reader;
    }

	@Bean
    public FlatFileItemReader<User> validatorFileReader1() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(resourcePatternResolver.getResource("classpath:userFile.csv"));
        reader.setLinesToSkip(0);
        reader.setEncoding("UTF-8");
        reader.setLineMapper(new DefaultLineMapper<User>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"id","name","age"});
//                setDelimiter(",");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                setTargetType(User.class);
            }});
        }});
        System.out.println("读取1一次");
        return reader;
    }



}
