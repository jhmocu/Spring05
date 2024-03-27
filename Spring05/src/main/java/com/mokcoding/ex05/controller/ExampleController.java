package com.mokcoding.ex05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/example")
@Log4j
public class ExampleController {
	
	@GetMapping("/main")
	public void main() {
		log.info("main()");
	}
	
	@GetMapping("/login")
	public void login() {
		log.info("login");
	}
	
}
