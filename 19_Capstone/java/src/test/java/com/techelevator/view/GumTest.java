package com.techelevator.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GumTest {

	private Gum trident;
	
	@Before
	public void setUp() {
		trident = new Gum("Y1", "Trident Gum", 0.50);
	}
	
	@Test
	public void test_dispensing_message() {
		assertEquals("Chew Chew, Yum!", trident.dispensingMessage());
	}

}
