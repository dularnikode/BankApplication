package com.bank.dto;

public class FilterParameterAccountDto {

	public Double minBalance;

	public Double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(Double minBalance) {
		this.minBalance = minBalance;
	}
	
	@Override
	public String toString() {
		return "Filter parameters [minBalance of account=" + minBalance + "]";
	}

	public FilterParameterAccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterParameterAccountDto(Double minBalance) {
		super();
		this.minBalance = minBalance;
	}

}
