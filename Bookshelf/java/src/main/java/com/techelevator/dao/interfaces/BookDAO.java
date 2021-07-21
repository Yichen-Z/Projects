package com.techelevator.dao.interfaces;

import java.util.List;

import com.techelevator.model.Book;

public interface BookDAO {
	
	List<Book> getAllBooks();
	
	Book get(int id);
	
	boolean createBook(Book newBook);

}
