package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.response.TicketResponse;
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

@Service
public class TicketPurchaseService {

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

    public ResponseEntity<?> purchaseTicket(Long ticketTypeId,String email) {

        User attendee = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TicketType ticketType =
                ticketTypeRepository.findById(ticketTypeId)
                        .orElseThrow(() -> new RuntimeException("Ticket type not found"));

        if (ticketType.getSold() >= ticketType.getTotalAvailable()) {
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

        return ResponseEntity.ok(qr);
    }

    public ResponseEntity<List<TicketResponse>> myTickets(String email) {

        User attendee = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<Ticket>tickets=ticketRepository.findByAttendeeId(attendee.getId());
        
        List<TicketResponse>ticketResponses=tickets.stream()
        									.map(TicketMapper::toResponse).toList();

        return ResponseEntity.ok(ticketResponses);
    }
}

