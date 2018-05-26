package com.test.springdemo.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

		private Logger logger = LoggerFactory.getLogger(this.getClass());

		@Before("execution(* com.test.springdemo.StudentController.*(..))")
		public void logMethodCalls(JoinPoint joinpoint) {
			//B.L
			//Advice
			logger.info(">>>>>>>>>>>  Invoking Method  <<<<<<<<<<<<<<<<<<<<<  " +  joinpoint.getSignature().getName() + "  >>   " + joinpoint.getArgs());
			for(Object obj : joinpoint.getArgs()) {
				logger.info(">>>>>>>>>>>  Method  Args  <<<<<<<<<<<<<<<<<<<<< " + obj);
			}
		}
		
		@AfterReturning(value = "execution(*  com.test.springdemo.jms.*.*(..))", 
				returning = "result")
		public void afterReturning(JoinPoint joinPoint, Object result) {
			logger.info("{} returned with value {}", joinPoint, result);
		}
		
		@After(value = "execution(* com.test.springjpademo.jms.MsgReceiver.*.*(..))")
		public void after(JoinPoint joinPoint) {
			logger.info(">>>>>>>>> after execution of {} >>>>>>>>", joinPoint);
		}
}
