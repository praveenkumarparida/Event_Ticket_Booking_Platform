package com.ey.maper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ey.dto.request.TicketRequest;
import com.ey.dto.response.AttendeeTicketResponse;
import com.ey.dto.response.TicketResponse;
import com.ey.entity.Event;
import com.ey.entity.Ticket;
import com.ey.entity.TicketType;
import com.ey.entity.User;
import com.ey.enums.TicketStatus;

public class TicketMapper {

	public static Ticket toEntity(TicketRequest req, TicketType ticketType, User attendee) {
        Ticket t = new Ticket();
        if (req.getStatus() != null) {
            t.setStatus(TicketStatus.valueOf(req.getStatus().toUpperCase()));
        }
        t.setTicketType(ticketType);
        t.setAttendee(attendee);
        return t;
    }

	public static void updateEntity(TicketRequest req, Ticket t, TicketType ticketType, User attendee) {
        if (req.getStatus() != null) {
            t.setStatus(TicketStatus.valueOf(req.getStatus().toUpperCase()));
        }
        if (ticketType != null) t.setTicketType(ticketType);
        if (attendee != null) t.setAttendee(attendee);
    }

	public static TicketResponse toResponse(Ticket t) {
        TicketResponse r = new TicketResponse();
        r.setId(t.getId());
        r.setStatus(t.getStatus() != null ? t.getStatus().name() : null);

        if (t.getTicketType() != null) {
            r.setTicketTypeId(t.getTicketType().getId());
            r.setTicketTypeName(t.getTicketType().getName());
            if (t.getTicketType().getEvent() != null) {
                r.setEventId(t.getTicketType().getEvent().getId());
                r.setEventName(t.getTicketType().getEvent().getName());
            }
        }

        if (t.getAttendee() != null) {
            r.setAttendeeId(t.getAttendee().getId());
            r.setAttendeeName(t.getAttendee().getName());
        }

        return r;
	}
	
	public static AttendeeTicketResponse ToAttendeeTicketResponse(Ticket ticket) {

        Event event = ticket.getTicketType().getEvent();

        TicketStatus status = ticket.getStatus();

        if (status == TicketStatus.PURCHASED && event.getEndTime().isBefore(LocalDateTime.now())) {
            status = TicketStatus.EXPIRED;
        }

        return new AttendeeTicketResponse(
                ticket.getId(),
                event.getName(),
                ticket.getTicketType().getName(),
                ticket.getTicketType().getPrice(),
                status,
                event.getStartTime()
        );
    }
}
