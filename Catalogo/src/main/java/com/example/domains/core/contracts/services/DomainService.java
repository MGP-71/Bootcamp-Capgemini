package com.example.domains.core.contracts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

public interface DomainService<E, K> {
	List<E> getAll();

	Page<E> getAll(Pageable pageable);

	Optional<E> getOne(K id);

	E add(E item) throws DuplicateKeyException, InvalidDataException;

	E modify(E item) throws NotFoundException, InvalidDataException;

	void delete(E item) throws InvalidDataException;

	void deleteById(K id);
}
