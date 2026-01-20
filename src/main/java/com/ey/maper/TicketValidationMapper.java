package com.ey.maper;

import com.ey.entity.Ticket;
import com.ey.entity.TicketValidationLog;
import com.ey.entity.User;

public class TicketValidationMapper {

	public static TicketValidationLog buildLog(Ticket ticket,User staff,String result) {

		TicketValidationLog validation=new TicketValidationLog();
		validation.setTicket(ticket);
		validation.setStaff(staff);
		validation.setResult(result);
        return validation;
                
    }
}
