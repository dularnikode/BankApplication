package com.bank.dto;

public class SelfAmountTransferRequest {

	private Double amount;

	private String fromAccountId;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public SelfAmountTransferRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SelfAmountTransferRequest(Double amount, String fromAccountId) {
		super();
		this.amount = amount;
		this.fromAccountId = fromAccountId;
	}
	
	
}
