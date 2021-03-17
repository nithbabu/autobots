package com.cars.autobots.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String getLogin() {
		return "redirect:/vehicles";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}

}
