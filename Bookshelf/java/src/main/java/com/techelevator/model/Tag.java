package com.techelevator.model;

/**
 * Labels given to books
 * @author Yichen Zhang
 *
 */

public class Tag {
	
	private int tagID;
	private String tagName;
	
	public Tag(String label) {
		this.tagName = label;
	}
	
	public Tag(int tagIdentifier, String label) {
		this.tagID = tagIdentifier;
		this.tagName = label;
	}

	public int getTagID() {
		return tagID;
	}

	public void setTagID(int tagID) {
		this.tagID = tagID;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public String toString() {
		return tagName;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o == null || this.getClass() != o.getClass()) {
			return false;
		} else {
			Tag otherTag = (Tag)o;
			return (this.tagID == otherTag.getTagID() && this.tagName.equalsIgnoreCase(otherTag.getTagName()));
		}
	}

}
