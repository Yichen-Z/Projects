package com.techelevator.model;

public class Author {
	private int authorID;
	private String lastName;
	private String firstName;
	
	public Author(String name) {
		this.firstName = name;
	}
	
	public Author(String first, String last) {
		this.firstName = first;
		this.lastName = last;
	}
	
//	Getters and Setters
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", firstName, lastName);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o == null || this.getClass() != o.getClass()) {
			return false;
		} else {
			Author otherAuthor = (Author)o;
			return (this.authorID == otherAuthor.getAuthorID() 
					&& this.firstName.equalsIgnoreCase(otherAuthor.getFirstName()) 
					&& this.lastName.equalsIgnoreCase(otherAuthor.getLastName()));
		}
	}
	
}
