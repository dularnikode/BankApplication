package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
//	@Autowired
//	private AccountService accountService;
	
	
	@GetMapping("")
	public ModelAndView homePage(Model model) {
		return new ModelAndView("index");
	}
	
	@GetMapping("/create")
	public ModelAndView CreateAccountPage(Model model) {
		return new ModelAndView("createAccount");
	}
	
	
//	
//	@PostMapping()
//	public ModelAndView createAccount(@RequestParam("firstName") String firstName,)
	
}
