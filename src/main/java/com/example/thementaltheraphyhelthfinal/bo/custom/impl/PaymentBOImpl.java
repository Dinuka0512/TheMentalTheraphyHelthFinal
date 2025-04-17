package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.PaymentBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.PaymentDAO;
import com.example.thementaltheraphyhelthfinal.dto.PaymentDto;
import com.example.thementaltheraphyhelthfinal.entities.Payment;

public class PaymentBOImpl implements PaymentBO{

    //===========
    private PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.PAYMENT);
    //===========

    @Override
    public boolean save(PaymentDto paymentDto) {
        return paymentDAO.save(new Payment(paymentDto.getPayment_Id(),paymentDto.getAmount(), paymentDto.getDate()));
    }
}
