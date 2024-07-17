package com.example.Execution_Time_Annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@Aspect
@EnableAspectJAutoProxy
@Component
public class LogExecutionTime_Aspect {
	
    @Around("@annotation(LogExecutionTime)")
public Object LogExecutionTime(ProceedingJoinPoint pjp,LogExecutionTime logExecutionTime) throws Throwable
{
	long start = System.currentTimeMillis();
	Object obj = pjp.proceed();
	long end = System.currentTimeMillis();
	System.out.println(pjp.getSignature()+" execution time is "+(end-start));
	return obj;
}
}
