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

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Category;
import com.example.domains.entities.models.CategoryDTO;
import com.example.domains.entities.models.CategoryShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(CategoryResource.class)
class CategoryResourceTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService srv;

	@Autowired
	ObjectMapper objectMapper;

	@Value
	static class CategoryShortMock implements CategoryShort {
		int id;
		String nombre;
	}

	@Test
	void testGetAllString() throws Exception {
		List<Category> lista = new ArrayList<>(
				Arrays.asList(new Category(1, "Obliacgion"), new Category(2, "Fantasia")));
		when(srv.getAll()).thenReturn(lista);
		mockMvc.perform(get("/api/categorias/v1?").accept(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.size()").value(2));
	}

	@Test
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Category(1, "Obliacgion");
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/categorias/v1/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id)).andExpect(jsonPath("$.nombre").value(ele.getName()))
				.andDo(print());
	}

	@Test
	void testGetOne404() throws Exception {
		int id = 1;
		var ele = new Category(id, "Fantasia");
		when(srv.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/categorias/v1/{id}", id)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.title").value("Not Found")).andDo(print());
	}

	@Test
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Category(id, "Novela antigua");
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/categorias/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CategoryDTO.from(ele)))).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/categorias/v1/1")).andDo(print());
	}
}
