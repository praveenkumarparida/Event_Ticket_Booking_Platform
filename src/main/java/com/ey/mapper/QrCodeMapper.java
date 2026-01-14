package com.ey.mapper;

import com.ey.dto.request.CreateQrCodeRequest;
import com.ey.dto.response.CreateQrCodeResponse;
import com.ey.entity.QrCode;
import com.ey.entity.Ticket;
import com.ey.enums.QrCodeStatusEnum;

public class QrCodeMapper {

	public static QrCode toQrCode(CreateQrCodeRequest request, Ticket ticket, String qrValue) {
        QrCode qrCode = new QrCode();
        qrCode.setTicket(ticket);
        qrCode.setValue(qrValue);
        qrCode.setStatus(QrCodeStatusEnum.ACTIVE);
        return qrCode;
    }

	public static CreateQrCodeResponse toCreateQrCodeResponse(QrCode qrCode) {
        CreateQrCodeResponse response = new CreateQrCodeResponse();
        response.setId(qrCode.getId());
        response.setStatus(qrCode.getStatus());
        response.setValue(qrCode.getValue());
        response.setTicketId(qrCode.getTicket() != null ? qrCode.getTicket().getId() : null);
        response.setCreatedAt(qrCode.getCreatedAt());
        response.setUpdatedAt(qrCode.getUpdatedAt());
        return response;
    }

}
