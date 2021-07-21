package com.techelevator.dao.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.dao.DAOIntegrationTest;
import com.techelevator.dao.interfaces.BookDAO;
import com.techelevator.model.Book;

public class BookSqlDAOTest extends DAOIntegrationTest {
	private JdbcTemplate jdbcTemplate;
	private BookDAO bookDao;
	private List<Book> results;
	private Book newBook;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setupDataSource();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		closeDataSource();
	}

	@Before
	public void setUp() throws Exception {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		bookDao = new BookSqlDAO(getDataSource());
		results = new ArrayList<Book>();
	}

	@After
	public void tearDown() throws Exception {
		rollback();
	}

	@Test
	public void test_Get_all_books() {
		String sqlGetBookCount = "SELECT COUNT(*) AS book_count FROM books";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetBookCount);
		int bookCount = 0;
		if (result.next())
			bookCount = result.getInt("book_count");
		
		results = bookDao.getAllBooks();
		assertEquals(bookCount, results.size());
	}

	@Test
	public void test_Get_book_by_ID() {
		fail("Not yet implemented");
	}

	@Test
	public void test_Create_book() {
		fail("Not yet implemented");
	}

}
