package com.techelevator.view;

import static org.junit.Assert.*;

import org.junit.*;

public class CandyTest {

	private Candy smarties;
	
	@Before
	public void setUp() {
		smarties = new Candy("E1", "Smarties", 3.00);
	}
	
	@Test
	public void candy_dispensing_message_test() {
		assertEquals("Munch Munch, Yum!", smarties.dispensingMessage());
	}

}
