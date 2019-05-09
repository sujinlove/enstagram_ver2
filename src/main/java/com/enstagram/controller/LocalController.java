package com.enstagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LocalController {
	@RequestMapping(value = "{path:[^\\\\.]*}", method = {RequestMethod.POST, RequestMethod.GET})
	public String html5Forwarding() {
		return "forward:/index.html";
	}
}
