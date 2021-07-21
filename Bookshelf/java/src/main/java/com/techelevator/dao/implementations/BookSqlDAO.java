package com.techelevator.dao.implementations;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
		String sqlBooks = "SELECT * FROM books";
		SqlRowSet results = template.queryForRowSet(sqlBooks);
		readList = mapResultsToBooks(results);
		return readList;
	}

	@Override
	public Book get(int id) {
		Book retrievedBook = new Book("Default Title", 2021);
		String sqlFetchBook = "SELECT * FROM books WHERE book_id = ?";
		
		SqlRowSet result = template.queryForRowSet(sqlFetchBook, id);
		if(result.next()) {
			retrievedBook = mapRowToBook(result);
		}
		return retrievedBook;
	}

	@Override
	public boolean createBook(Book newBook) {
		String sqlAddBook = "INSERT INTO books (title, year) VALUES (?, ?)";
		return template.update(sqlAddBook, newBook.getTitle(), newBook.getYear()) != 0;
	}
	
	/**
	 * Maps row data to Book object fields
	 * @param result from database query
	 * @return
	 */
	private Book mapRowToBook(SqlRowSet result) {
		Book retrievedBook = new Book(result.getInt("book_id"), result.getString("title"), result.getInt("year"));
		return retrievedBook;
	}
	
	private List<Book> mapResultsToBooks(SqlRowSet results){
		List<Book> bookList = new ArrayList<Book>();
		while(results.next()) {
			bookList.add(mapRowToBook(results));
		}
		return bookList;
	}

}
