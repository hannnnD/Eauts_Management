package com.eauts.ems.Eauts_management.repository;

import com.eauts.ems.Eauts_management.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
}
