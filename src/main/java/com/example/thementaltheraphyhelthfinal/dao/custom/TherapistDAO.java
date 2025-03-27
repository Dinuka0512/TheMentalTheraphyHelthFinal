package com.example.thementaltheraphyhelthfinal.dao.custom;

import com.example.thementaltheraphyhelthfinal.dao.CrudDAO;
import com.example.thementaltheraphyhelthfinal.dao.SuperDAO;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;

public interface TherapistDAO extends SuperDAO, CrudDAO<Therapist> {
    boolean isValidToSave(String email);
    boolean isValidToUpdate(String email,String id);
    boolean delete(Therapist therapist);
}
