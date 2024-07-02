package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ActorRepositoryRealTest {
	@Autowired
	ActorRepository dao;

	@Test
	void test() {
		assertThat(dao.findAll().size()).isGreaterThan(200);
	}

}
