package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
			@RepeatedTest(value = 5, name ="{displayName} {currentRepetition}/{totalRepetitions}")
			void testAdd1() {
				assertEquals(calculator.add(1, 2), 3);
			}
			
			@ParameterizedTest(name = "Caso {index}: {0} + {1} = {2}")
			@DisplayName("Sumar dos reales")
			@CsvSource(value = {"1,2,3","2,2,4","-4,-6,-10","0.2,0.5,0.7"})
			void testAdd2(double op1, double op2, double res) {
				assertEquals(res, calculator.add(op1, op2));
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
