package com.intellect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intellect.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
