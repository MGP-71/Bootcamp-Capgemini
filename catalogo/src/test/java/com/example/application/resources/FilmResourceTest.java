package com.example.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domains.contracts.services.FilmService;
import com.example.domains.entities.models.FilmShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(FilmResource.class)
class FilmResourceTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FilmService srv;

	@Autowired
	ObjectMapper objectMapper;

	@Value
	static class FilmShortMock implements FilmShort {
		int id;
		String titulo;
	}
}
