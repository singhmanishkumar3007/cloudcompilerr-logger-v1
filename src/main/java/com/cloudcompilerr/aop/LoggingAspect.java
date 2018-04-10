package com.cloudcompilerr.aop;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("@annotation(com.cloudcompilerr.annotation.Traceable)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	long start = System.currentTimeMillis();
	Object obj = joinPoint.proceed();
	long executionTime = System.currentTimeMillis() - start;
	Logger.info("{} with parameters - {}  - executed in {} ms", joinPoint.getSignature().getName(),
		Arrays.asList(joinPoint.getArgs()).stream().map(arg -> arg.toString()).collect(Collectors.joining(",")),
		executionTime);
	return obj;
    }

    @AfterThrowing(pointcut = "execution(* com.cloudcompilerr.*.*(..))", throwing = "excep")
    public void logException(JoinPoint joinPoint, Throwable excep) throws Throwable {
	Logger.error(" Faced exception {} at {} with parameters - {} ", excep, joinPoint.getSignature().getName(),
		Arrays.asList(joinPoint.getArgs()).stream().map(arg -> arg.toString())
			.collect(Collectors.joining(",")));
    }

}
