package com.example.thementaltheraphyhelthfinal.dao.custom;

import com.example.thementaltheraphyhelthfinal.dao.CrudDAO;
import com.example.thementaltheraphyhelthfinal.dao.SuperDAO;
import com.example.thementaltheraphyhelthfinal.entities.User;

public interface UserDAO extends SuperDAO, CrudDAO<User> {
    User getUserDetails(String email);
    boolean isUniqueEmail(String email);
}
