package com.prowings.user_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prowings.user_service.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByRole(String role);

}
