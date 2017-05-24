package com.bupt.client.dao.security;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.client.entity.security.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);

	Page<User> findByUsernameLike(String string, Pageable page);
}
