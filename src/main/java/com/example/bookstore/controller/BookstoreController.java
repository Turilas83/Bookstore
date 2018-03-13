package com.example.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookstoreController {
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
}
