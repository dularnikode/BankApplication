package com.bank.aspect;

import javax.naming.spi.DirStateFactory.Result;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bank.model.Account;

import net.bytebuddy.asm.Advice.This;

@Aspect
@Component
public class SecondLoggingAspect {
	
	private static final Logger log = LoggerFactory.getLogger(FirstLoggingAspect.class);

	@AfterReturning(
			pointcut = "com.bank.aspect.PointcutExpressions.forCreateAccountService()",
			returning="acc")
	public void afterReturnCreateAccount(JoinPoint theJoinPoint,Account acc) {
		log.info("@AfterReturning Advice ::==>> Account created sucessfully for :: {}",acc.getFirstName());
	}
	

}
