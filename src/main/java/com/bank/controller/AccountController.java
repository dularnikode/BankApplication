package com.bank.controller;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bank.dto.AmountTransaferRequest;
import com.bank.dto.CreateAccountDto;
import com.bank.dto.FilterParameterAccountDto;
import com.bank.dto.SelfAmountTransferRequest;
import com.bank.enums.AccountType;
import com.bank.model.Account;
import com.bank.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("")
	public ModelAndView homePage(Model model) {
		return new ModelAndView("index");
	}
	
	@GetMapping("/create")
	public ModelAndView CreateAccountPage(Model model) {
		return new ModelAndView("createAccount");
	}
		
	@PostMapping()
	public ModelAndView createAccount(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("initaleBalance") Double initaleBalance,
			@RequestParam("accountType") AccountType accountType,
			@RequestParam("accountCreatorId") Long accountCreatorId,
			@RequestParam("withdrawalLimitPerDay") Double withdrawalLimitPerDay) {
		try {
			CreateAccountDto createAccountDto = new CreateAccountDto(firstName, lastName, initaleBalance, accountType,
					accountCreatorId, withdrawalLimitPerDay);

			Account account = accountService.createAccount(createAccountDto);
			Map<String, Object> map = new HashMap<>();
			map.put("account", account);
			ModelAndView modelAndView = new ModelAndView("accountDetails", map);
			return modelAndView;
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			map.put("errorMessage", e.getMessage());
			ModelAndView modelAndView = new ModelAndView("accountDetails", map);
			return modelAndView;
		}
	}
	
	@GetMapping("/{accountId}")
	public ModelAndView getAccount(@PathVariable("accountId")String accountId) {
		
		Account account =accountService.getAccount(accountId);
		Map<String,Object> map = new HashMap<>();
		map.put("account", account);
		ModelAndView modelAndView = new ModelAndView("accountDetails",map);
		return modelAndView;
	}
	
	@GetMapping("/viewAll")
	public ModelAndView getAllAccountDetails(@PathParam("minBalance") Double minBalance,Pageable pageable) {
		try {
			
			FilterParameterAccountDto filterParameterAccountDto =new FilterParameterAccountDto(minBalance);
			Page<Account> accounts =accountService.getAllAccountDetails(filterParameterAccountDto, pageable);
			Map<String, Object> map = new HashMap<>();
			map.put("accounts", accounts.getContent());
			map.put("noOfPages", accounts.getTotalPages());
			map.put("currentPage", accounts.getPageable().getPageNumber());
			ModelAndView modelAndView = new ModelAndView("accounts", map);
			return modelAndView;
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			map.put("errorMessage", e.getMessage());
			ModelAndView modelAndView = new ModelAndView("accounts", map);
			return modelAndView;
		}
	}
	
	@GetMapping("/filter/viewAll")
	public ModelAndView getFilterPage(Model model) {
		return new ModelAndView("filterPage");
	}
	
	@GetMapping("/viewAll/minBalance")
	public ModelAndView getMinBalancePage(Model model) {
		return new ModelAndView("minBalanceFilter"); 
	}
	
	@GetMapping("/credit")
	public ModelAndView getCreditAmountPage(Model model) {
		return new ModelAndView("creditAmount");
	}

	@PostMapping("/credit")
	public ModelAndView depositeAmount(@RequestParam("bankEmployeeId") Long bankEmployeeId,
			@RequestParam("amount") Double amount, @RequestParam("fromAccountId") String fromAccountId) {
		SelfAmountTransferRequest selfAmountTransferRequest = new SelfAmountTransferRequest(amount, fromAccountId);
		accountService.creditAmount(selfAmountTransferRequest);
		Map<String, Object> map = new HashMap<>();
		map.put("successMessage", "successfully credit amount " + amount + " to account number " + fromAccountId);
		ModelAndView modelAndView = new ModelAndView("creditAmount", map);
		return modelAndView;
	}

	@GetMapping("/withdraw")
	public ModelAndView getWithdrawAmountPage(Model model) {
		return new ModelAndView("withdrawAmount");
	}

	@PostMapping("/withdraw")
	public ModelAndView withdrawAmount(@RequestParam("bankEmployeeId") Long bankEmployeeId,
			@RequestParam("amount") Double amount, @RequestParam("fromAccountId") String fromAccountId) {
		SelfAmountTransferRequest selfAmountTransferRequest = new SelfAmountTransferRequest(amount, fromAccountId);
		accountService.withdrawAmount(selfAmountTransferRequest);
		Map<String, Object> map = new HashMap<>();
		map.put("successMessage", "successfully withdraw amount " + amount + " from account number " + fromAccountId);
		ModelAndView modelAndView = new ModelAndView("withdraw", map);
		return modelAndView;
	}
	
	
	@GetMapping("/transferAmount")
	public ModelAndView getTransferAmountPage(Model model) {
		return new ModelAndView("amountTransfer");
	}
	
	@PostMapping("/transferAmount")
	public ModelAndView transferAmount(@RequestParam("bankEmployeeId") Long bankEmployeeId,
			@RequestParam("amount") Double amount, @RequestParam("fromAccountId") String fromAccountId,
			@RequestParam("toAccountId") String toAccountId) {
		AmountTransaferRequest amountTransferRequest = new AmountTransaferRequest(amount, fromAccountId, toAccountId);
		accountService.transferAmount(amountTransferRequest);
		Map<String, Object> map = new HashMap<>();
		map.put("successMessage", "transaction request for amount " + amount.toString()
				+ " transfer from account number " + fromAccountId + " to account number " + toAccountId);
		ModelAndView modelAndView = new ModelAndView("amountTransfer", map);
		return modelAndView;
	}
	
	
	

}
