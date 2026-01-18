package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.entity.TicketQr;
import com.ey.repository.TicketQrRepository;

@RestController
@RequestMapping("/api/attendee/qr")
//@PreAuthorize("hasRole('ATTENDEE')")
public class AttendeeQrController {

	@Autowired
    private TicketQrRepository ticketQrRepository;

    @GetMapping("/{code}")
    public ResponseEntity<TicketQr> getQr(@PathVariable String code) {

        return ResponseEntity.ok(
                ticketQrRepository.findByCode(code)
                        .orElseThrow(() -> new RuntimeException("QR not found"))
        );
    }
}
