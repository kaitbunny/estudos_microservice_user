package com.microservices.user.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.user.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
