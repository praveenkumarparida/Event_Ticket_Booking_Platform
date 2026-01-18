package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.entity.TicketQr;
import com.ey.enums.QrStatus;
import com.ey.repository.TicketQrRepository;

@Service
public class TicketValidationService {

	@Autowired
    private TicketQrRepository ticketQrRepository;

    public ResponseEntity<String> validate(String qrCode) {

        TicketQr qr = ticketQrRepository.findByCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Invalid QR"));

        if (qr.getStatus() == QrStatus.USED) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ticket already used");
        }

        qr.setStatus(QrStatus.USED);
        
        ticketQrRepository.save(qr);

        return ResponseEntity.ok("Ticket validated successfully");
    }
}
