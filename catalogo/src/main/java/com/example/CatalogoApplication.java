package com.example;

import java.util.TreeMap;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Microservicio: Catalogo de peliculas", version = "1.0", description = "Ejemplo de Microservicio utilizando la base de datos **Sakila**."), externalDocs = @ExternalDocumentation(description = "Documentación del proyecto", url = "https://github.com/MGP-71/Bootcamp-Capgemini.git"))
@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	@Bean
	OpenApiCustomizer sortSchemasAlphabetically() {
		return openApi -> {
			var schemas = openApi.getComponents().getSchemas();
			openApi.getComponents().setSchemas(new TreeMap<>(schemas));
		};
	}

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
