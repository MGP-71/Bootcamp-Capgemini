package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAdd1() {
		assertEquals(new Calculator().add(1, 2), 3);
	}
	
	@Test
	void testAdd2() {
		assertEquals(new Calculator().add(0.1, 0.2), 0.3);
	}
	
	@Test
	void testDivInt() {
		assertEquals(new Calculator().div(3, 2), 1.5);
	}
	
	@Test
	void testDivDouble() {
		assertEquals(new Calculator().div(2.0, 3.0), 0.6666666666666666);
	}
}
