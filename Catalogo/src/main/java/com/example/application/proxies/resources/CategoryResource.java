package com.example.application.proxies.resources;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Category;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/peliculas/v1")
public class CategoryResource {
	private CategoryService srv;

	public CategoryResource(CategoryService srv) {
		this.srv = srv;
	}

	@GetMapping(params = "page")
	public Page<Category> getAll(Pageable page) {
		return srv.getAll(page);
	}

	@GetMapping(path = "/{id}")
	public Category getOne(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get();

	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Category item)
			throws DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getCategoryId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	public void update(@PathVariable int id, @Valid @RequestBody Category item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (id != item.getCategoryId())
			throw new BadRequestException("No coinciden los ids");
		srv.modify(item);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}

}