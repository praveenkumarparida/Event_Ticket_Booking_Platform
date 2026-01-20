package com.ey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ey.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByAttendeeId(Long attendeeId);
    Optional<Ticket> findByIdAndAttendeeId(Long ticketId, Long attendeeId);
    
    @Query("""
            select t from Ticket t
            join t.ticketType tt
            join tt.event e
            where e.id = :eventId
        """)
        List<Ticket> findAllByEventId(@Param("eventId") Long eventId);
}

