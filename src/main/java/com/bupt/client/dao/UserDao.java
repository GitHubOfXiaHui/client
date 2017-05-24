package com.bupt.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.client.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
