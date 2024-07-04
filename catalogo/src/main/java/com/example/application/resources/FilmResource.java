package com.example.application.resources;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.FilmService;
import com.example.domains.entities.models.FilmDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/peliculas/v1")
public class FilmResource {
	private FilmService srv;

	public FilmResource(FilmService srv) {
		this.srv = srv;
	}

	@GetMapping(params = "page")
	public Page<FilmDTO> getAll(Pageable pageable, @RequestParam(defaultValue = "short") String mode) {
		return srv.getByProjection(pageable, FilmDTO.class);
	}

	@GetMapping(path = "/{id}")
	public FilmDTO getOne(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return FilmDTO.from(item.get());
	}

	@Operation(summary = "Añadir una nueva pelicula")
	@ApiResponse(responseCode = "201", description = "Pelicula añadida")
	@ApiResponse(responseCode = "404", description = "Pelicula no encontrada")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody FilmDTO item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(FilmDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getFilmId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Operation(summary = "Modificar una pelicula existente", description = "Los identificadores deben coincidir")
	@ApiResponse(responseCode = "200", description = "Pelicula encontrada")
	@ApiResponse(responseCode = "404", description = "Pelicula no encontrada")
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody FilmDTO item)
			throws NotFoundException, InvalidDataException, BadRequestException {
		if (id != item.getFilmId())
			throw new BadRequestException("No coinciden los identificadores");
		srv.modify(FilmDTO.from(item));
	}

	@Operation(summary = "Borrar una pelicula existente")
	@ApiResponse(responseCode = "204", description = "Pelicula borrada")
	@ApiResponse(responseCode = "404", description = "Pelicula no encontrada")
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}