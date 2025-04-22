package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.PaymentDto;
import com.example.thementaltheraphyhelthfinal.entities.Payment;

import java.util.ArrayList;

public interface PaymentBO extends SuperBo {
    boolean save(PaymentDto paymentDto);
    String generateNewId();
    PaymentDto getPaymenDto(String session_Id);

    void update(PaymentDto paymentDto);

    void delete(String paymentId);
    ArrayList<PaymentDto> getAll();
    double getTodayIncome();
}
