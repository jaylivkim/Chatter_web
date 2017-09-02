package com.hanium.chatter.user.domain;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
	Person findByUserId(String userId);
}
