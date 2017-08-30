package com.bupt.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliveController {

	@RequestMapping("/alive")
	public String alive() {
		return "alive";
	}
	
}
