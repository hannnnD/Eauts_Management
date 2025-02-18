package com.eauts.ems.Eauts_management.repository;

import com.eauts.ems.Eauts_management.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
