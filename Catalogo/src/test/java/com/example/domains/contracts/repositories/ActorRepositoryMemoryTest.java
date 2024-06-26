package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.domains.entities.Actor;

class ActorRepositoryMemoryTest {

	@Autowired
	private TestEntityManager em;

	@Autowired
	ActorRepository dao;

	@BeforeEach
	void setUp() throws Exception {
		var item = new Actor(0, "Pepito", "GRILLO");
		item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
		em.persist(item);
		item = new Actor(0, "Carmelo", "COTON");
		item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
		em.persist(item);
		item = new Actor(0, "Capitan", "TAN");
		item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
		em.persist(item);
	}

	@Test
	void testGetAll_isNotEmpty() {
		var rslt = dao.findAll();
		assertThat(rslt.size()).isEqualTo(3);
		assertThat(dao.findTop5ByLastNameStartingWithOrderByFirstNameDesc("C").size()).isEqualTo(2);
		assertThat(dao.findByJPQL(2).size()).isEqualTo(2);
	}

	@Test
	void findTop5ByLastNameStartingWithOrderByFirstNameDescTest() {
		assertThat(dao.findTop5ByLastNameStartingWithOrderByFirstNameDesc("p").size()).isEqualTo(5);

	}

	@Test
	void findBySQLTest() {
		assertThat(dao.findBySQL(1).size()).isGreaterThan(0);
	}

}
