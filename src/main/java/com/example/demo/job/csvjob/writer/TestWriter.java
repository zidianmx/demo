package com.example.demo.job.csvjob.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.User;
@Component
public class TestWriter implements ItemWriter<Object> {

	@Override
	public void write(List<?> items) throws Exception {
		
		System.out.println("写入一次"+items.size());
	
		
	}

}
