package com.example.thementaltheraphyhelthfinal.dao.custom;

import com.example.thementaltheraphyhelthfinal.dao.CrudDAO;
import com.example.thementaltheraphyhelthfinal.dao.SuperDAO;
import com.example.thementaltheraphyhelthfinal.dto.PaymentDto;
import com.example.thementaltheraphyhelthfinal.entities.Payment;

public interface PaymentDAO extends SuperDAO, CrudDAO<Payment> {
    Payment getPaymenDto(String session_Id);
}
