package com.example.thementaltheraphyhelthfinal.dao.custom;

import com.example.thementaltheraphyhelthfinal.dao.CrudDAO;
import com.example.thementaltheraphyhelthfinal.dao.SuperDAO;
import com.example.thementaltheraphyhelthfinal.entities.Patient;

public interface PatienDAO extends CrudDAO<Patient> , SuperDAO {
    boolean isValidToSave(String email);
}
