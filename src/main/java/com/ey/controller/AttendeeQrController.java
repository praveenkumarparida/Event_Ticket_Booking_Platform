package com.ey.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.entity.Ticket;
import com.ey.entity.TicketQr;
import com.ey.entity.User;
import com.ey.exception.UserNotFoundException;
import com.ey.repository.TicketQrRepository;
import com.ey.repository.TicketRepository;
import com.ey.repository.UserRepository;

@RestController
@RequestMapping("/api/attendee/qr")
//@PreAuthorize("hasRole('ATTENDEE')")
public class AttendeeQrController {

	@Autowired
    private TicketQrRepository ticketQrRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping
	public ResponseEntity<?> getAllQr(Authentication authentication){
		User attendee=userRepository.findByEmail(authentication.getName())
				.orElseThrow(()->new UserNotFoundException("User not found"));
		
		List<Ticket>tickets=ticketRepository.findByAttendeeId(attendee.getId());
		
		List<?>qrs=tickets.stream()
				.map(t->t.getTicketQr())
				.map(q->{
					Map<Long,String>qr=new HashMap<>();
					qr.put(q.getId(),q.getCode());
					return qr;
				}).toList();
		
		return new ResponseEntity<>(qrs,HttpStatus.OK);
	}

    @GetMapping("/{code}")
    public ResponseEntity<TicketQr> getQr(@PathVariable String code) {

        return ResponseEntity.ok(
                ticketQrRepository.findByCode(code)
                        .orElseThrow(() -> new RuntimeException("QR not found"))
        );
    }
}
