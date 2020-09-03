package com.bank.aspect;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.bank.dto.SelfAmountTransferRequest;
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
	
	
	@AfterReturning(pointcut = "com.bank.aspect.PointcutExpressions.forGetAllAccountDetailsService()",
			returning = "result")
	public void afterReturningGetAllAccountDetails(JoinPoint theJoinPoint,Object result) {
		if(result !=null) {
			log.info("@AfterReturning Advice ::==>> Fetched All Account Details Sucessfully");
		}
	}
	
	@AfterThrowing(pointcut = "com.bank.aspect.PointcutExpressions.forWithdrawAmountService()",
			throwing = "theExc")
	public void afterThrowingWidthdrawAmount(JoinPoint theJoinPoint,Throwable theExc){
		System.out.println(theExc);
	}
	
	
	
}
