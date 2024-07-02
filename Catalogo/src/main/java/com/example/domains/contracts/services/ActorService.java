package com.example.domains.contracts.services;

import java.sql.Timestamp;
import java.util.Collection;

import com.example.domains.core.contracts.services.ProjectionDomainService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;

public interface ActorService extends ProjectionDomainService<Actor, Integer> {
	void repartePremios();

	Collection<Film> novedades(Timestamp fecha);
}