package com.example.domains.contracts.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.core.contracts.repositories.ProjectionsAndSpecificationJpaRepository;
import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.ActorShort;

public interface ActorRepository extends ProjectionsAndSpecificationJpaRepository<Actor, Integer> {
	List<Actor> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);

	List<Actor> findTop5ByLastNameStartingWithOrderByFirstNameDesc(String prefijo);

	List<Actor> findTop5ByLastNameStartingWith(String prefijo, Sort orderBy);

	List<Actor> findByActorIdGreaterThanEqual(int actorId);

	@Query(value = "from Actor a where a.actorId >= ?1")
	List<Actor> findByJPQL(int actorId);

	@Query(value = "SELECT * FROM actor WHERE actor_id >= :id", nativeQuery = true)
	List<Actor> findBySQL(int id);

	List<ActorDTO> readByActorIdGreaterThanEqual(int actorId);

	List<ActorShort> queryByActorIdGreaterThanEqual(int actorId);

	<T> List<T> findByActorIdGreaterThanEqual(int actorId, Class<T> proyeccion);

}
