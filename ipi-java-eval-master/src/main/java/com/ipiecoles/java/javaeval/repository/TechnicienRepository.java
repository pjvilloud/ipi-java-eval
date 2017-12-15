package com.ipiecoles.java.javaeval.repository;

import com.ipiecoles.java.javaeval.model.Technicien;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface TechnicienRepository extends BaseEmployeRepository<Technicien> {

    List<Technicien> findByGradeBetween(Integer gradeLower, Integer gradeUpper);

    Slice<Technicien> findTop5ByGrade(Integer grade);

}
