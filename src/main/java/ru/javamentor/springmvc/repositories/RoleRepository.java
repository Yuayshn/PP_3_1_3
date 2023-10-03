package ru.javamentor.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.springmvc.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
