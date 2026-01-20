package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.TicketValidationLog;

@Repository
public interface TicketValidationLogRepository extends JpaRepository<TicketValidationLog, Long> {

}

