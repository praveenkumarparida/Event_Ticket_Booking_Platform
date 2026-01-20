package com.ey.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.entity.Event;
import com.ey.entity.Ticket;
import com.ey.entity.TicketQr;
import com.ey.entity.User;
import com.ey.enums.QrStatus;
import com.ey.enums.TicketStatus;
import com.ey.maper.TicketValidationMapper;
import com.ey.repository.StaffEventAssignmentRepository;
import com.ey.repository.TicketQrRepository;
import com.ey.repository.TicketValidationLogRepository;
import com.ey.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketValidationService {

	@Autowired
	private TicketQrRepository ticketQrRepository;
	
	@Autowired
    private StaffEventAssignmentRepository assignmentRepository;
	
	@Autowired
    private TicketValidationLogRepository logRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	private Logger logger=LoggerFactory.getLogger(TicketValidationService.class);

	@Transactional
    public ResponseEntity<String> validateTicket(
            String qrCode,
            String staffEmail) {

        User staff = userRepository.findByEmail(staffEmail)
                .orElseThrow(() -> {
                	logger.error("Staff not found");
                	return new RuntimeException("Staff not found");});

        TicketQr qr = ticketQrRepository.findByCode(qrCode)
                .orElseThrow(() -> {
                	logger.error("Invalid QR code");
                	return new RuntimeException("Invalid QR code");});

        if (qr.getStatus() == QrStatus.USED) {
            return ResponseEntity.badRequest()
                    .body("Ticket already used");
        }

        Event event = qr.getTicket()
                .getTicketType()
                .getEvent();

        boolean assigned = assignmentRepository
                .existsByStaffIdAndEventId(staff.getId(), event.getId());

        if (!assigned) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Staff not assigned to this event");
        }

        qr.setStatus(QrStatus.USED);
        qr.getTicket().setStatus(TicketStatus.USED);

        return ResponseEntity.ok("Ticket validated successfully");
    }
	
	
//    @Transactional
//    public ResponseEntity<String> validateTicket(String qrCode,String staffEmail) {
//
//        User staff = userRepository.findByEmail(staffEmail)
//                .orElseThrow(() -> {
//                	logger.error("Staff not found");
//                	return new RuntimeException("Staff not found");});
//
//        TicketQr qr = ticketQrRepository.findByCode(qrCode)
//                .orElseThrow(() -> {
//                	logger.error("Invalid QR");
//                	return new RuntimeException("Invalid QR");});
//
//        Ticket ticket = qr.getTicket();
//        Event event = ticket.getTicketType().getEvent();
//
//        boolean allowed = assignmentRepository
//                .existsByStaffIdAndEventId(staff.getId(), event.getId());
//
//        if (!allowed) {
//        	logger.warn("Unauthorized to validate");
//            logRepository.save(TicketValidationMapper.buildLog(ticket, staff, "UNAUTHORIZED"));
//            return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                    .body("Staff not assigned to this event");
//        }
//
//        if (qr.getStatus() == QrStatus.USED) {
//            logRepository.save(TicketValidationMapper.buildLog(ticket, staff, "ALREADY_USED"));
//            return ResponseEntity.badRequest()
//                    .body("Ticket already used");
//        }
//
//        qr.setStatus(QrStatus.USED);
//        ticket.setStatus(TicketStatus.USED);
//
//        logRepository.save(TicketValidationMapper.buildLog(ticket, staff, "SUCCESS"));
//        logger.info("Ticket validated successfully");
//        return ResponseEntity.ok("Ticket validated successfully");
//    }
}
