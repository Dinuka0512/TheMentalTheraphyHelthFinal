package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.dao.custom.PaymentDAO;
import com.example.thementaltheraphyhelthfinal.entities.Payment;

import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() {
        return null;
    }

    @Override
    public boolean save(Payment dto) {
        return false;
    }

    @Override
    public boolean update(Payment dto) {
        return false;
    }

    @Override
    public boolean exist(String id) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String generateNewId() {
        return null;
    }

    @Override
    public Payment search(String id) {
        return null;
    }
}
