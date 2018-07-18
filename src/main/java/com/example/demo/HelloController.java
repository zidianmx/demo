package com.example.demo;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.job.csvjob.CsvOperationJob;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@EnableBatchProcessing
@RestController
public class HelloController {
	
	@Value("${commonp.name}")
	private String name;
	@Value("${commonp.age}")
	private String age;
	@Autowired
	private CommonProperties cp;
	@Autowired
	private UserService userService;
	
	@Autowired
    private JobLauncher jobLauncher;
	
	@Autowired
    private CsvOperationJob csvOperationJob;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/start")
    String start() {
        System.out.println("开始执行ImportJob-------------");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("Date", new Date());
        JobParameters dateJobParameters = jobParametersBuilder.toJobParameters();
        try {
            jobLauncher.run(csvOperationJob.outputUserJob(), dateJobParameters);
            String a = Thread.currentThread().getName();
            System.out.println(a);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
        return "执行完成";
    }
	
	@RequestMapping("/hello")
	public List<User> hello() {
//		List<User> list = ur.findAll();
//		List<User> list = userMapper.getAll();
//		User u = list.get(0);
//		List<User> list = userService.getUserList();
		List<User> list1 = userService.getUserList1();
		return list1;
	}	
	
}
