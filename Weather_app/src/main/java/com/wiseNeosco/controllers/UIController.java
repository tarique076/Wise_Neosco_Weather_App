package com.wiseNeosco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wiseNeosco.services.WeatherService;

@Controller
public class UIController {

	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/")
    public String home(Model model) {
		
        model.addAttribute("message", "Welcome to Thymeleaf in Spring Boot!");
        return "weatherApp";
    }
}
