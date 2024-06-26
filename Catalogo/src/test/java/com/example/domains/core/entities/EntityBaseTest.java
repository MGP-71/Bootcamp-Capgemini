package com.example.domains.core.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Value;

class EntityBaseTest {

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

	@Value
	class Dummy extends EntityBase<Dummy> {
		@Positive
		int id;
		@NotBlank
		@Size(min = 2)
		String nombre;
	}

	@Nested
	class Ok {
		@Test
		void isValid() {
			var dummy = new Dummy(1, "algo");
			assertAll("valido", () -> assertTrue(dummy.isValid()), () -> assertFalse(dummy.isInvalid()),
					() -> assertEquals(0, dummy.getErrors().size()), () -> assertEquals("", dummy.getErrorsMessage()));
		}
	}
}
