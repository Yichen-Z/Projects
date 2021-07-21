package com.techelevator.model;

import java.util.List;
import java.util.Map;

/**
 * Book and associated properties
 * @author Yichen Zhang
 *
 */
public class Book {
	private int bookID;
	private String title;
	private int year;
	private List<Author> authors;	
	private Map<Tag, Integer> tags;
	
	/**
	 * Most basic constructor
	 * @param title
	 * @param publishYear
	 */
	public Book(String title, int publishYear){
		this.title = title;
		this.year = publishYear;
	}
	
	/**
	 * Book Constructor without bookID for database to designate
	 * @param title
	 * @param publishYear
	 * @param authors
	 * @param tags
	 */
	public Book(String bookTitle, int publishYear, List<Author> bookAuthors, Map<Tag,Integer> bookLabels) {
		this.title = bookTitle;
		this.year = publishYear;
		this.authors = bookAuthors;
		this.tags = bookLabels;
	}
	
	/**
	 * Book Constructor without authors or tags
	 * @param bookID
	 * @param title
	 * @param publishYear
	 */
	public Book(int bookID, String bookTitle, int publishYear) {
		this.bookID = bookID;
		this.title = bookTitle;
		this.year = publishYear;
	}
	
	/**
	 * Book Constructor with all fields, including bookID
	 * @param bookID
	 * @param title
	 * @param publishYear
	 * @param authors
	 * @param tags
	 */
	public Book(int bookIdentifier, String bookTitle, int publishYear, List<Author> bookAuthors, Map<Tag,Integer> bookLabels) {
		this.bookID = bookIdentifier;
		this.title = bookTitle;
		this.year = publishYear;
		this.authors = bookAuthors;
		this.tags = bookLabels;
	}
	
	public Map<Tag,Integer> getTags() {
		return tags;
	}
	
	public void setTags(Map<Tag,Integer> newTags) {
		this.tags = newTags;
	}
	
	public void updateTag(Tag newTag, int tagWeight) {
		tags.put(newTag, Integer.valueOf(tagWeight));
	}
	
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author newAuthor) {
		if (!authors.contains(newAuthor))
				authors.add(newAuthor);
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return String.format("%s (%n)", title, year);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o == null || this.getClass() != o.getClass()) {
			return false;
		} else {
			Book otherBook = (Book)o;
			return (this.title.equalsIgnoreCase(otherBook.getTitle()) &&
					this.year == otherBook.getYear());
		}
	}

}
