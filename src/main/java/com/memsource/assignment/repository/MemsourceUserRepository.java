package com.memsource.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memsource.assignment.model.MemsourceUser;

public interface MemsourceUserRepository extends JpaRepository<MemsourceUser, String> {

    MemsourceUser getByUserName(String name);

}
