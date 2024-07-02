package com.example.application.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.contracts.CatalogoService;
import com.example.application.models.NovedadesDTO;
import com.example.domains.contracts.services.ActorService;
import com.example.domains.contracts.services.CategoryService;
import com.example.domains.contracts.services.FilmService;
import com.example.domains.contracts.services.LanguageService;

@Service
public class CatalogoServiceImpl implements CatalogoService {
	@Autowired
	private FilmService filmSrv;
	@Autowired
	private ActorService artorSrv;
	@Autowired
	private CategoryService categorySrv;
	@Autowired
	private LanguageService languageSrv;

	@Override
	public NovedadesDTO novedades(Timestamp fecha) {
		// Timestamp fecha = Timestamp.valueOf("2019-01-01 00:00:00");
		if (fecha == null)
			fecha = Timestamp.from(Instant.now().minusSeconds(36000));
		return null;
	}

}
