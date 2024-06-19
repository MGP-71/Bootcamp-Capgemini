package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Pruebas de la clase Calculator")
class CalculatorTest {

	Calculator calculator;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator = new Calculator();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	@DisplayName("Metodo Add")
	class Add {
		@Nested
		class OK {
			@Test
			@DisplayName("Sumar dos enteros")
			void testAdd1() {
				assertEquals(calculator.add(1, 2), 3);
			}
			
			@Test
			@DisplayName("Sumar dos reales")
			void testAdd2() {
				assertEquals(calculator.add(0.1, 0.2), 0.3);
			}
		}
		@Nested
		class KO {
			
		}
	}

	
	
	@Nested
	@DisplayName("Metodo Div")
	class Div {
		@Nested
		class OK {
			@Test
			@DisplayName("Dividir dos enteros")
			void testDivInt() {
				assertEquals(calculator.div(3, 2), 1.5);
			}
			
			@Test
			@DisplayName("Dividir dos reales")
			void testDivDouble() {
				assertEquals(calculator.div(2.0, 3.0), 0.6666666666666666);
			}
		}
		@Nested
		class KO {
			@Test
			@DisplayName("Dividir entre 0")
			void testDivDouble0() {
				assertThrows(ArithmeticException.class, () -> calculator.div(2.0, 0));
			}
		}
	}
	
}
