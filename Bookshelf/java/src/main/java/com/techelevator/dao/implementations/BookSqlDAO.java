package com.techelevator.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
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
		String sqlBooks = "SELECT book_id, title, year FROM books";
		SqlRowSet results = template.queryForRowSet(sqlBooks);
		return mapResultsToBooks(results);
	}

	@Override
	public Book get(int id) {
		String sqlFetchBook = "SELECT title, year FROM books WHERE book_id = ?";
		SqlRowSet result = template.queryForRowSet(sqlFetchBook, id);
		return mapRowToBook(result);
	}

	@Override
	public boolean createBook(Book newBook) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Maps row data to Book object fields
	 * @param result from database query
	 * @return
	 */
	private Book mapRowToBook(SqlRowSet result) {
		return new Book(result.getInt("book_id"), result.getString("title"), result.getInt("year"));
	}
	
	private List<Book> mapResultsToBooks(SqlRowSet results){
		List<Book> bookList = new ArrayList<Book>();
		while(results.next()) {
			bookList.add(mapRowToBook(results));
		}
		return bookList;
	}

}
