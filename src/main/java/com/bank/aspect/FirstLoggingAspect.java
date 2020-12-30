package com.bank.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bank.model.Account;
import com.bank.service.AccountService;

import javassist.bytecode.SignatureAttribute.MethodSignature;

@Aspect
@Component
public class FirstLoggingAspect {
	
	private static final Logger log = LoggerFactory.getLogger(FirstLoggingAspect.class);
	
	@Before("com.bank.aspect.PointcutExpressions.forAllAccountServiceBeforeAfter()")
	public void beforeAdviceAspect(JoinPoint theJoinPoint){
		String method = theJoinPoint.getSignature().toShortString();
		log.info("@Before Advice::==>> Request for service =>>{}",method);
	}
	
	@After("com.bank.aspect.PointcutExpressions.forAllAccountServiceBeforeAfter()")
	public void afterAdviceAspect(JoinPoint theJoinPoint){
		String method = theJoinPoint.getSignature().toShortString();
		log.info("@After Advice::==>> Request Completed for service =>> {}",method);
	}	
}
