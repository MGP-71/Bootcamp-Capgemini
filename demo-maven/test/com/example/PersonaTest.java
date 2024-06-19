package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

class PersonaTest {
	Persona persona;
	
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
		persona = null;
	}

	@Nested
	@DisplayName("Instantacion")
	class Add {
		@Nested
		class OK {
			@ParameterizedTest(name = "{0} {1}")
			@CsvSource(value = {"1,Pepito","2,Grillo","3,Grillo,Pepito"})
			void soloNombre(int id, String nombre) {
				persona = new Persona(id, nombre);
				assertNotNull(persona);
				
				assertAll("Persona", 
						() -> assertEquals(id, persona.getId(), "id"),
						() -> assertEquals(nombre, persona.getNombre(), "nombre"),
						() -> assertNull(persona.getApellido(), "apellidos")
				);
			}
			@ParameterizedTest(name = "{0} {1}")
			@CsvSource(value = {"1,Pepito","2,Grillo","3,Grillo,Pepito"})
			void soloNombre(ArgumentsAccessor args) {
				persona = new Persona(args.getInteger(0), args.getString(1));
				assertNotNull(persona);
				
				assertAll("Persona", 
						() -> assertEquals(args.getInteger(0), persona.getId(), "id"),
						() -> assertEquals(args.getString(1), persona.getNombre(), "nombre"),
						() -> assertNull(persona.getApellido(), "apellidos")
				);
			}
		}
		@Nested
		class KO {
			@ParameterizedTest(name = "{0} {1}")
			@CsvSource(value = {"3,","4,''","5,'	'"})
			void soloNombre(int id, String nombre) {
				assertThrows(Exception.class, () -> new Persona(id, nombre));
			}
		}
	}

}
