package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.services.FilmService;
import com.example.domains.entities.models.FilmDTO;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

	@Autowired
	FilmService fsr;

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicación arrancada...");
		fsr.getByProjection(FilmDTO.class).forEach(System.out::println);
	}

}
