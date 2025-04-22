package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.PaymentBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.PaymentDAO;
import com.example.thementaltheraphyhelthfinal.dto.PaymentDto;
import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.Payment;

import java.util.List;

public class PaymentBOImpl implements PaymentBO{

    //===========
    private PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.PAYMENT);
    //===========

    @Override
    public boolean save(PaymentDto paymentDto) {
        Payment payment = new Payment(
                paymentDto.getPayment_Id(),
                paymentDto.getAmount(),
                paymentDto.getDate(),
                paymentDto.getPatient_id(),
                paymentDto.getSession_Id()
        );

        return paymentDAO.save(payment);
    }

    @Override
    public String generateNewId() {
        return paymentDAO.generateNewId();
    }

    @Override
    public PaymentDto getPaymenDto(String session_Id) {
        Payment payment = paymentDAO.getPaymenDto(session_Id);
        return (payment != null)? new PaymentDto(payment.getPayment_Id(), payment.getAmount(), payment.getDate(), payment.getPatient_id(), payment.getSession_Id()): null;
    }

    @Override
    public void update(PaymentDto paymentDto) {
        paymentDAO.update(new Payment(paymentDto.getPayment_Id(), paymentDto.getAmount(), paymentDto.getDate(), paymentDto.getPatient_id(), paymentDto.getSession_Id()));
    }
}
