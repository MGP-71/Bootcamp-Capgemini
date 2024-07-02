package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

//	@Autowired
//	ActorService srv;
//	@Autowired
//	FilmService fsr;
//	@Autowired
//	CategoryService csr;
//	@Autowired
//	LanguageService lsr;

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicación arrancada...");
//		srv.getAll().forEach(System.out::println);
//		fsr.getAll().forEach(System.out::println);
//		csr.getAll().forEach(System.out::println);
//		lsr.getAll().forEach(System.out::println);
	}
}
