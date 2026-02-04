package com.pfms.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfms.api.bean.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);}

