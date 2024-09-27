package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Role;
import com.ProjetJavaApp.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByRole(Role role);

}
