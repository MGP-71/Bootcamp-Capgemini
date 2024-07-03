package com.example.domains.entities.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.domains.entities.Film;

@Projection(name = "peli-corta", types = { Film.class })
public interface FilmShortDTO {
	@Value("#{target.FilmId}")
	int getId();

	@Value("#{target.title}")
	String getTitulo();
}
