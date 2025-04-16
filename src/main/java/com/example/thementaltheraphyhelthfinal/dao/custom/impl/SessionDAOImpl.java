package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.dao.custom.SessionDAO;
import com.example.thementaltheraphyhelthfinal.entities.TheraphySession;

import java.util.ArrayList;

public class SessionDAOImpl implements SessionDAO {
    @Override
    public ArrayList<TheraphySession> getAll() {
        return null;
    }

    @Override
    public boolean save(TheraphySession dto) {
        return false;
    }

    @Override
    public boolean update(TheraphySession dto) {
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
    public TheraphySession search(String id) {
        return null;
    }
}
