package com.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bank.dto.CreateAccountDto;
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

}
