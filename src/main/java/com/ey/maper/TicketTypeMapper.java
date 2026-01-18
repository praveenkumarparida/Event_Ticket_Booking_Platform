package com.ey.maper;

import com.ey.dto.request.TicketTypeRequest;
import com.ey.dto.response.TicketTypeResponse;
import com.ey.entity.Event;
import com.ey.entity.TicketType;

public class TicketTypeMapper {

	public static TicketType toEntity(TicketTypeRequest req, Event event) {
        TicketType t = new TicketType();
        t.setName(req.getName());
        t.setPrice(req.getPrice());
        t.setTotalAvailable(req.getTotalAvailable());
        t.setSold(req.getSold() == null ? 0 : req.getSold());
        t.setEvent(event);
        return t;
    }

	public static void updateEntity(TicketTypeRequest req, TicketType t, Event event) {
        if (req.getName() != null) t.setName(req.getName());
        if (req.getPrice() != null) t.setPrice(req.getPrice());
        if (req.getTotalAvailable() != null) t.setTotalAvailable(req.getTotalAvailable());
        if (req.getSold() != null) t.setSold(req.getSold());
        if (event != null) t.setEvent(event);
    }

	public static TicketTypeResponse toResponse(TicketType t) {
        TicketTypeResponse r = new TicketTypeResponse();
        r.setId(t.getId());
        r.setName(t.getName());
        r.setPrice(t.getPrice());
        r.setTotalAvailable(t.getTotalAvailable());
        r.setSold(t.getSold());
        if(t.getEvent()!=null) {
        	r.setEventId(t.getEvent().getId());
        	r.setEventName(t.getEvent().getName());
        }
        Integer total = t.getTotalAvailable();
        Integer sold = t.getSold() == null ? 0 : t.getSold();
        r.setRemaining(total == null ? null : Math.max(0, total - sold));
        return r;
    }

}
