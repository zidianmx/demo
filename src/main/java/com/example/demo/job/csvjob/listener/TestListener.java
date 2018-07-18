package com.example.demo.job.csvjob.listener;

import java.util.LinkedList;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.repository.TestAsync;

@Component
public class TestListener extends JobExecutionListenerSupport{
			
	@Autowired
    private List<TestAsync> executableProcs = new LinkedList<>();
	
	protected void invokeProcedures() {
        for (TestAsync executableProc : executableProcs) {
            executableProc.execute();
        }
    }
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("-----------------监听job后--------------------");
		invokeProcedures();
		super.afterJob(jobExecution);
	}
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("*****************监听job前********************");
		super.beforeJob(jobExecution);
	}
}
