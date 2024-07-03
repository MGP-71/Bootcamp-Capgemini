package com.example.domains.contracts.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.domains.entities.Language;

@RepositoryRestResource(path = "idiomas", itemResourceRel = "idioma", collectionResourceRel = "idomas")
public interface LanguageRepository extends ListCrudRepository<Language, Integer> {
	List<Language> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
}