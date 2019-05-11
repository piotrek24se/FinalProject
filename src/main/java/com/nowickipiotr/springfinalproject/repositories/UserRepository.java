package com.nowickipiotr.springfinalproject.repositories;

import com.nowickipiotr.springfinalproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUserName(String username);

}
