/**
 * 
 */
package com.bank.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bank.enums.AccountType;

/**
 *
 */
@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String accountId;

	private String firstName;

	private String lastName;
	
	private int age;

	private Double balance;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "account_creator_id", referencedColumnName = "id")
	private User accountCreator;

	private Double withdrawalLimitPerDay;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public User getAccountCreator() {
		return accountCreator;
	}

	public void setAccountCreator(User accountCreator) {
		this.accountCreator = accountCreator;
	}

	public Double getWithdrawalLimitPerDay() {
		return withdrawalLimitPerDay;
	}

	public void setWithdrawalLimitPerDay(Double withdrawalLimitPerDay) {
		this.withdrawalLimitPerDay = withdrawalLimitPerDay;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


}
