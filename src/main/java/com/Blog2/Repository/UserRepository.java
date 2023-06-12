package com.Blog2.Repository;

import com.Blog2.Entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String Email);
     Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);
Optional<User>   findByUsername(String Username);


     boolean existsByEmail(String Email);
     boolean existsByUsername(String username);


}
