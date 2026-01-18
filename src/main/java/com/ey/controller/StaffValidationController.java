package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.service.TicketValidationService;

@RestController
@RequestMapping("/api/staff/validate")
//@PreAuthorize("hasRole('STAFF')")
public class StaffValidationController {

	@Autowired
    private TicketValidationService ticketValidationService;

    @PostMapping("/{qrCode}")
    public ResponseEntity<String> validate(@PathVariable String qrCode) {
        return ticketValidationService.validate(qrCode);
    }
}

