package com.bank.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bank.dto.CreateAccountDto;
import com.bank.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	@Mapping(source = "initaleBalance",target="balance")
	@Mapping(source = "accountCreatorId",target="accountCreator.id")
	public Account createAccountDtoToAccount(CreateAccountDto createAccountDto);
}
