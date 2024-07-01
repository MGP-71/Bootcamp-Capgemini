package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domains.contracts.proxies.CalculatorProxy;

@SpringBootTest
class CalculatorProxyTest {

	@Autowired
	CalculatorProxy calculadora;

	@Test
	void testAdd() {
		assertEquals(0.3, calculadora.add(0.1, 0.2));
	}

	@Test
	void testSubtract() {
		assertEquals(0.1, calculadora.add(1, 0.9));
	}

	@Test
	void testMultiply() {
		assertEquals(5, calculadora.add(2, 2.5));
	}

	@Test
	void testDivideOK() {
		assertEquals(0.5, calculadora.add(1, 2));
	}

	@Test
	void testDivideKO() {
		assertThrows(IllegalArgumentException.class, () -> calculadora.div(5, 0));
	}
}
