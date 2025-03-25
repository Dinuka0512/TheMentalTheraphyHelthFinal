package com.example.thementaltheraphyhelthfinal.dao.custom;

import com.example.thementaltheraphyhelthfinal.dao.SuperDAO;
import com.example.thementaltheraphyhelthfinal.entities.User;

public interface UserDAO extends SuperDAO {
    User getUserDetails(String email);
}
