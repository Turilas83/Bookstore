package com.example.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;

@Controller
public class BookstoreController {
	@Autowired
	private BookRepository repository;
	
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
	@RequestMapping("/booklist")
	public String booklist(Model model) {
		List<Book> books = repository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}
	@RequestMapping("/addbook")
	public String add(Model model) {
		model.addAttribute(new Book());
		return "addbook";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.delete(bookId);
		return "redirect:../booklist";
	}
	@Bean
	public CommandLineRunner saveBooks(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("The Hitchhiker’s Guide to the Galaxy", "Douglass Adams", 1979, "0-330-25864-8", 19.90));
			repository.save(new Book("Ready Player One", "Ernest Cline", 2011, "9780307887436", 20.70));
			repository.save(new Book("Code Complete", "Steve McConnell", 2004, "9780735619678", 39.60));
			repository.save(new Book("Masters Of Doom", "David Kushner", 2004, "9780749924898", 10.90));
		};
	}
}
