package com.example.domains.entities.models;

import org.springframework.beans.factory.annotation.Value;

public interface LanguageShort {
	@Value("#{target.LanguageId}")
	int getId();

	@Value("#{target.name}")
	String getNombre();
}
