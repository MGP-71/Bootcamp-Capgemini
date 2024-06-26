package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ActorTest {

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
	@DisplayName("Test válido")
	void test() {
		var fixture = new Actor(1000, "PEPITO", "GRILLO");
		assertTrue(fixture.isValid());
	}

	@DisplayName("Apellidos de tener entre 2 y 45 caracteres, y no puede estar en blanco")
	@ParameterizedTest(name = "apellidos: -{0}-")
	@ValueSource(strings = { "", "	", "p",
			"1234567890123456789012345678901234567890123456789054354343542353425342543534253425342543543543" })
	@NullAndEmptySource
	void testApellidosIsInvalid(String valor) {
		var fixture = new Actor(0, "GRILLO", valor);
		assertTrue(fixture.isInvalid());
	}

	@Test
	@DisplayName("Solo la PK diferente")
	void testPrimaryKeyKO() {
		var fixture1 = new Actor(666, "Pepito", "GRILLO");
		var fixture2 = new Actor(665, "Pepito", "GRILLO");
		assertAll("PK", () -> assertFalse(fixture1.equals(fixture2)), () -> assertFalse(fixture2.equals(fixture1)),
				() -> assertTrue(fixture1.hashCode() != fixture2.hashCode()));
	}
}
