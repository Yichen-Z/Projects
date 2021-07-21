package com.techelevator.dao.implementations;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.dao.interfaces.BookDAO;
import com.techelevator.model.Book;

@Component
public class BookSqlDAO implements BookDAO {
	
	private JdbcTemplate template;
	private List<Book> readList;
	
	public BookSqlDAO(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createBook(Book newBook) {
		// TODO Auto-generated method stub
		return false;
	}

}
