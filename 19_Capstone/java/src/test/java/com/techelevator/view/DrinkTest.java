package com.techelevator.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DrinkTest {

private Drink coke;
	
	@Before
	public void setUp() {
		coke = new Drink("E3", "Cherry Coke Zero", 2.50);
	}
	
	@Test
	public void drink_dispensing_message_test() {
		assertEquals("Glug Glug, Yum!", coke.dispensingMessage());
	}

}
