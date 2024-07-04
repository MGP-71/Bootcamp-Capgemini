package com.example.application.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domains.contracts.services.FilmService;
import com.example.domains.entities.Film;
import com.example.domains.entities.models.FilmDTO;
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

	@Test
	void testGetAllString() throws Exception {
		Pageable pg = PageRequest.of(0, 20);
		Page<FilmDTO> lista = new PageImpl(
				Arrays.asList(new FilmDTO(1, "Blaze funnier"), new FilmDTO(2, "El baño perdido")), pg, 2);
		when(srv.getByProjection(pg, FilmDTO.class)).thenReturn(lista);
		mockMvc.perform(get("/api/peliculas/v1?page=0&size=20&modo=short").accept(MediaType.APPLICATION_JSON))
				.andExpectAll(status().isOk(), content().contentType("application/json"),
						jsonPath("$.content.size()").value(2));
	}

	@Test
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Film(1, "Blaze funnier");
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/peliculas/v1/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id)).andExpect(jsonPath("$.titulo").value(ele.getTitle()))
				.andDo(print());
	}

	@Test
	void testGetAllPageable() throws Exception {
		Pageable pg = PageRequest.of(0, 20);
		Page<FilmDTO> lista = new PageImpl(
				Arrays.asList(new FilmDTO(1, "Blaze funnier"), new FilmDTO(2, "El baño perdido")), pg, 2);
		when(srv.getByProjection(pg, FilmDTO.class)).thenReturn(lista);
		mockMvc.perform(get("/api/peliculas/v1").queryParam("page", "0")).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.content.size()").value(2),
				jsonPath("$.size").value(20));
	}

	@Test
	void testGetOne404() throws Exception {
		int id = 1;
		var ele = new Film(id, "La jungla de cristal");
		when(srv.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/peliculas/v1/{id}", id)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.title").value("Not Found")).andDo(print());
	}

	@Test
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Film(id, "Lo que el viento se llevó");
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/peliculas/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(FilmDTO.from(ele)))).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/peliculas/v1/1")).andDo(print());
	}
}
