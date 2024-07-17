package com.example.Execution_Time_Annotation;

import org.springframework.stereotype.Service;

@Service
public class MyController {
	
	@LogExecutionTime
	public void serve() throws InterruptedException
	{
		Thread.sleep(2000);
	}

}
