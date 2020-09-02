package com.bank.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutExpressions {
	
	@Pointcut("execution(* com.bank.service.AccountService.*(..))")
	public void forAllAccountServiceBeforeAfter(){}

	@Pointcut("execution(* com.bank.service.AccountService.createAccount(..))")
	public void forCreateAccountService() {}
	
	@Pointcut("execution(* com.bank.service.AccountService.getAccount(..))")
	public void forGetAccountService() {}
	
	@Pointcut("execution(* com.bank.service.AccountService.getAllAccountDetails(..))")
	public void forGetAllAccountDetailsService() {}
	
	@Pointcut("execution(* com.bank.service.AccountService.withdrawAmount(..))")
	public void forWithdrawAmountService() {}
}
