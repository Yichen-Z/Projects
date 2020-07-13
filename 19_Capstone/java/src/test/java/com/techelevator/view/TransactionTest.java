package com.techelevator.view;

import static org.junit.Assert.*;

import org.junit.*;

public class TransactionTest {

	private Transaction tester;
	private static final double EPSILON = 0.00001;
	
	@Before
	public void setUp() {
		tester = new Transaction(5.0);
	}
	
	@Test
	public void testGetMoneyProvided() {
		assertEquals(5.0, tester.getMoneyProvided(), EPSILON);
	}

	@Test
	public void testAddMoney() {
		tester.addMoney(5.0);
		tester.addMoney(1.0);
		assertEquals(11.0, tester.getMoneyProvided(), EPSILON);
	}

	@Test
	public void testDeductMoney1Deduct() {
		tester.deductMoney(4.0);
		assertEquals(1.0, tester.getMoneyProvided(), EPSILON);
	}
	
	@Test
	public void testDeductMoney2Deducts() {
		tester.deductMoney(3.0);
		tester.deductMoney(1.0);
		assertEquals(1.0, tester.getMoneyProvided(), EPSILON);
	}

	@Test
	public void testResetMoney() {
		tester.resetMoney();
		assertEquals(0.0, tester.getMoneyProvided(), EPSILON);
	}

	@Test
	public void testGiveChangeQuartersOnly() {
		tester.resetMoney();
		tester.addMoney(5);
		int[] expectedChange = { 20, 0, 0 };
		assertArrayEquals(expectedChange, tester.giveChange());
	}
	
	@Test
	public void testGiveChangeAllCoins() {
		int[] expectedChange = { 13, 1, 1 };
		tester.resetMoney();
		tester.addMoney(3.4);
		assertArrayEquals(expectedChange, tester.giveChange());
	}

}
