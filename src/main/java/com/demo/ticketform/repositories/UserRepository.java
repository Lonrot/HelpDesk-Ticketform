package com.demo.ticketform.repositories;

import com.demo.ticketform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

   User getUserById();
}
