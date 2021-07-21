package com.techelevator.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.interfaces.BookDAO;
import com.techelevator.model.Book;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BookController {
	@Autowired
	BookDAO dao;

	@RequestMapping( path = "/books" )
	public List<Book> listAllBooks(){
		List<Book> readList = dao.getAllBooks();
		return readList;
	}
}
