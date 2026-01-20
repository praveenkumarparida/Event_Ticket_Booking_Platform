package com.ey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Ticket;
import com.ey.entity.TicketQr;

@Repository
public interface TicketQrRepository extends JpaRepository<TicketQr, Long> {

	Optional<TicketQr> findByCode(String code);
	Optional<TicketQr> findByTicket(Ticket ticket);
}

