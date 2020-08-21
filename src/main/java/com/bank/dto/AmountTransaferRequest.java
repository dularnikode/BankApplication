package com.bank.dto;

public class AmountTransaferRequest {

	private Double amount;

	private String fromAccount;

	private String toAccount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public AmountTransaferRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AmountTransaferRequest(Double amount, String fromAccount, String toAccount) {
		super();
		this.amount = amount;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}

}
