package com.memsource.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memsource.assignment.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User getById(Long aLong);

    User getByName(String name);
}

