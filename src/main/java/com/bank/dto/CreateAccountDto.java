package com.bank.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.bank.enums.AccountType;

public class CreateAccountDto {

	private String firstName;

	private String lastName;

	private Double initaleBalance;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	private Long accountCreatorId;

	private Double withdrawalLimitPerDay;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getInitaleBalance() {
		return initaleBalance;
	}

	public void setInitaleBalance(Double initaleBalance) {
		this.initaleBalance = initaleBalance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Long getAccountCreatorId() {
		return accountCreatorId;
	}

	public void setAccountCreatorId(Long accountCreatorId) {
		this.accountCreatorId = accountCreatorId;
	}

	public Double getWithdrawalLimitPerDay() {
		return withdrawalLimitPerDay;
	}

	public void setWithdrawalLimitPerDay(Double withdrawalLimitPerDay) {
		this.withdrawalLimitPerDay = withdrawalLimitPerDay;
	}

	public CreateAccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateAccountDto(String firstName, String lastName, Double initaleBalance, AccountType accountType,
			Long accountCreatorId, Double withdrawalLimitPerDay) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.initaleBalance = initaleBalance;
		this.accountType = accountType;
		this.accountCreatorId = accountCreatorId;
		this.withdrawalLimitPerDay = withdrawalLimitPerDay;
	}
	
	

}

