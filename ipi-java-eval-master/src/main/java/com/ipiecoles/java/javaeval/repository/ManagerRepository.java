package com.ipiecoles.java.javaeval.repository;

import com.ipiecoles.java.javaeval.model.Manager;
import org.springframework.data.jpa.repository.EntityGraph;

public interface ManagerRepository extends BaseEmployeRepository<Manager> {
    @EntityGraph(attributePaths = "equipe")
    Manager findOneWithEquipeById(Long id);
}
