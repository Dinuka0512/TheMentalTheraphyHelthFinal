package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.dao.custom.RegistrationDAO;
import com.example.thementaltheraphyhelthfinal.entities.Registration;

import java.util.ArrayList;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public ArrayList<Registration> getAll() {
        return null;
    }

    @Override
    public boolean save(Registration dto) {
        return false;
    }

    @Override
    public boolean update(Registration dto) {
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
    public Registration search(String id) {
        return null;
    }
}
