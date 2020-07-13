package com.techelevator.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChipTest {

private Chip pringles;
	
	@Before
	public void setUp() {
		pringles = new Chip("E2", "Pringles", 1.50);
	}
	
	@Test
	public void chip_dispensing_message_test() {
		assertEquals("Crunch Crunch, Yum!", pringles.dispensingMessage());
	}
}
