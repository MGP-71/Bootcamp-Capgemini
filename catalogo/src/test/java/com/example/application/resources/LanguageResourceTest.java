package com.example.application.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Language;
import com.example.domains.entities.models.LanguageDTO;
import com.example.domains.entities.models.LanguageShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(LanguageResource.class)
class LanguageResourceTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LanguageService srv;

	@Autowired
	ObjectMapper objectMapper;

	@Value
	static class LanguageShortMock implements LanguageShort {
		int id;
		String nombre;
	}

	@Test
	void testGetAllString() throws Exception {
		List<Language> lista = new ArrayList<>(Arrays.asList(new Language(1, "Ingles"), new Language(2, "Frances")));
		when(srv.getAll()).thenReturn(lista);
		mockMvc.perform(get("/api/idiomas/v1?").accept(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.size()").value(2));
	}

	@Test
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Language(1, "Ingles");
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/idiomas/v1/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id)).andExpect(jsonPath("$.nombre").value(ele.getName()))
				.andDo(print());
	}

	@Test
	void testGetOne404() throws Exception {
		int id = 1;
		var ele = new Language(id, "Tituquito");
		when(srv.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/idiomas/v1/{id}", id)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.title").value("Not Found")).andDo(print());
	}

	@Test
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Language(id, "Español");
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/idiomas/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(LanguageDTO.from(ele)))).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/idiomas/v1/1")).andDo(print());
	}
}
