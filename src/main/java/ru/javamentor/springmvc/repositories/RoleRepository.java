package ru.javamentor.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.javamentor.springmvc.model.Role;


@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Long> {

}
