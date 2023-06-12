package com.Blog2.Repository;

import com.Blog2.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
Optional<Role> findByName(String Name);
}
