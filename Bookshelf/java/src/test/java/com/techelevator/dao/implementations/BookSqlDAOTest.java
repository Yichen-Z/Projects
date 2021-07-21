package com.techelevator.dao.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
	private final String TEST_TITLE = "Test Test Test Best Book Ever";
	private final int TEST_PUBLICATION_YEAR = 2000;
	private final String FIRST_BOOK = "Lightseekers";

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
		newBook = new Book(TEST_TITLE, TEST_PUBLICATION_YEAR);
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
	public void test_Create_book() {
		results = bookDao.getAllBooks();
		int countBefore = results.size();
		
		assertTrue(bookDao.createBook(newBook));
		
		results = bookDao.getAllBooks();
		int countAfter = results.size();
		
		assertEquals(countBefore + 1, countAfter);
	}
	
	@Test
	public void test_Get_book_by_ID() {
//		assertEquals(FIRST_BOOK, bookDao.get(1).getTitle());
		bookDao.createBook(newBook);
		results = bookDao.getAllBooks();
		int id = -1;
		for (Book book : results) {
			if (book.equals(newBook))
				id = book.getBookID();
		}
		
		try {
			assertEquals(newBook, bookDao.get(id));
		} catch(Exception e) {
			fail("Failed with exception " + e.getMessage());
		}
	}
}
