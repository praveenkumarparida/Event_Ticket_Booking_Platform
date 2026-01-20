package com.ey.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.response.AttendeeTicketResponse;
import com.ey.entity.Event;
import com.ey.entity.Ticket;
import com.ey.entity.TicketQr;
import com.ey.entity.TicketType;
import com.ey.entity.User;
import com.ey.enums.QrStatus;
import com.ey.enums.TicketStatus;
import com.ey.maper.TicketMapper;
import com.ey.repository.TicketQrRepository;
import com.ey.repository.TicketRepository;
import com.ey.repository.TicketTypeRepository;
import com.ey.repository.UserRepository;
import com.ey.util.QrCodeGenerator;

import jakarta.transaction.Transactional;

@Service
public class TicketService {

	@Autowired
    private TicketRepository ticketRepository;
	
	@Autowired
    private TicketTypeRepository ticketTypeRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private TicketQrRepository ticketQrRepository;
	
	@Autowired
    private QrCodeGenerator qrCodeGenerator;
	
	private Logger logger=LoggerFactory.getLogger(TicketService.class);

	@Transactional
    public ResponseEntity<?> purchaseTicket(Long ticketTypeId,String email) {

        User attendee = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                	logger.error("User not found");
                	return new RuntimeException("User not found");});

        TicketType ticketType =
                ticketTypeRepository.findById(ticketTypeId)
                        .orElseThrow(() -> {
                        	logger.error("Ticket type not found");
                        	return new RuntimeException("Ticket type not found");});

        if (ticketType.getSold() >= ticketType.getTotalAvailable()) {
        	logger.error("All tickets sold");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        ticketType.setSold(ticketType.getSold() + 1);

        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatus.PURCHASED);
        ticket.setTicketType(ticketType);
        ticket.setAttendee(attendee);

        ticketRepository.save(ticket);

        TicketQr qr = new TicketQr();
                qr.setCode(qrCodeGenerator.generate());
                qr.setStatus(QrStatus.ACTIVE);
                qr.setTicket(ticket);

        ticketQrRepository.save(qr);

        logger.info("Ticket Purchased Successfully");
        return ResponseEntity.ok(qr.getCode());
    }

    public ResponseEntity<List<AttendeeTicketResponse>> myTickets(String email) {

        User attendee = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                	logger.error("User not found");
                	return new RuntimeException("User not found");});

        List<Ticket> tickets =
                ticketRepository.findByAttendeeId(attendee.getId());

        List<AttendeeTicketResponse> response = tickets.stream()
                .map(t->TicketMapper.ToAttendeeTicketResponse(t))
                .toList();

        logger.info("Gett all Attendee's Tickets");
        return ResponseEntity.ok(response);
    }
    
    @Transactional
    public ResponseEntity<String> cancelTicket(Long ticketId,String email) {

        User attendee = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                	logger.error("User not found");
                	return new RuntimeException("User not found");});

        Ticket ticket = ticketRepository
                .findByIdAndAttendeeId(ticketId, attendee.getId())
                .orElseThrow(() -> {
                	logger.error("Ticket not found");
                	return new RuntimeException("Ticket not found");});

        Event event = ticket.getTicketType().getEvent();

        if (event.getStartTime().isBefore(LocalDateTime.now())) {
            
        	logger.warn("Event already started");
        	return ResponseEntity.badRequest()
                    .body("Event already started");
        }

        ticket.setStatus(TicketStatus.CANCELLED);

        TicketType type = ticket.getTicketType();
        type.setSold(type.getSold() - 1);

        ticketQrRepository.findByTicket(ticket)
                .ifPresent(qr -> qr.setStatus(QrStatus.USED));

        logger.info("Ticket cancelled successfully");
        return ResponseEntity.ok("Ticket cancelled successfully");
    }
}

