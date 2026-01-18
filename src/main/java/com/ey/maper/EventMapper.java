package com.ey.maper;

import com.ey.dto.request.EventRequest;
import com.ey.dto.request.UpdateEventRequest;
import com.ey.dto.response.EventResponse;
import com.ey.entity.Event;
import com.ey.entity.User;
import com.ey.enums.EventStatus;

public class EventMapper {

	public static Event toEntity(EventRequest req, User organizer) {
        Event e = new Event();
        e.setName(req.getName());
        e.setStartTime(req.getStartTime());
        e.setEndTime(req.getEndTime());
        e.setVenue(req.getVenue());
        e.setOrganizer(organizer);
        if (req.getStatus() != null) {
            e.setStatus(EventStatus.valueOf(req.getStatus().toUpperCase()));
        }
        return e;
    }

	public static void updateEntity(UpdateEventRequest req, Event e, User organizer) {
        if (req.getName() != null) e.setName(req.getName());
        if (req.getStartTime() != null) e.setStartTime(req.getStartTime());
        if (req.getEndTime() != null) e.setEndTime(req.getEndTime());
        if (req.getVenue() != null) e.setVenue(req.getVenue());
        if (organizer != null) e.setOrganizer(organizer);
    }

	public static EventResponse toResponse(Event e) {
        EventResponse r = new EventResponse();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setStartTime(e.getStartTime());
        r.setEndTime(e.getEndTime());
        r.setVenue(e.getVenue());
        r.setStatus(e.getStatus() != null ? e.getStatus().name() : null);
        if (e.getOrganizer() != null) {
            r.setOrganizerId(e.getOrganizer().getId());
            r.setOrganizerName(e.getOrganizer().getName());
        }
        return r;
    }

}
