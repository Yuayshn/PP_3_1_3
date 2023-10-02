package ru.javamentor.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.javamentor.springmvc.model.Role;

import java.util.List;

@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll(List<Long> rolesId);
}
