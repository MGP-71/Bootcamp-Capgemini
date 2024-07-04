package com.example.application.resources;

import java.net.URI;
import java.util.List;

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
import com.example.domains.entities.models.CategoryDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias/v1")
public class CategoryResource {
	private CategoryService srv;

	public CategoryResource(CategoryService srv) {
		this.srv = srv;
	}

	@GetMapping
	public List<Category> getAll() {
		return srv.getAll();
	}

	@GetMapping(params = "page")
	public List<Category> getAll(Pageable page) {
		return srv.getAll();
	}

	@GetMapping(path = "/{id}")
	public CategoryDTO getOne(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return CategoryDTO.from(item.get());
	}

	@Operation(summary = "Añadir una nueva categoría")
	@ApiResponse(responseCode = "201", description = "Categoria añadida")
	@ApiResponse(responseCode = "404", description = "Categoria no encontrada")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody CategoryDTO item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(CategoryDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getCategoryId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Operation(summary = "Modificar una categoria existente", description = "Los identificadores deben coincidir")
	@ApiResponse(responseCode = "200", description = "Categoria encontrada")
	@ApiResponse(responseCode = "404", description = "Categoria no encontrada")
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody CategoryDTO item)
			throws NotFoundException, InvalidDataException, BadRequestException {
		if (id != item.getCategoryId())
			throw new BadRequestException("No coinciden los identificadores");
		srv.modify(CategoryDTO.from(item));
	}

	@Operation(summary = "Borrar una categoria existente")
	@ApiResponse(responseCode = "204", description = "Categoria borrada")
	@ApiResponse(responseCode = "404", description = "Categoria no encontrada")
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}