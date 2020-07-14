package com.spring.application.repository;

import com.spring.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс описывающий взаимодействие сущности User с БД
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
