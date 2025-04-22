package com.example.thementaltheraphyhelthfinal.bo;

import com.example.thementaltheraphyhelthfinal.bo.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    public enum getBoType{
        USER, PROGRAM, THERAPHIST, PATIENT, SESSION, PAYMENT,
    }

    public SuperBo getBo(getBoType type){
        switch (type){
            case USER -> {
                return new UserBoImpl();
            }
            case PROGRAM -> {
                return new TheraphyProgramBOImpl();
            }
            case THERAPHIST -> {
                return new TherapistBOimpl();
            }
            case PATIENT -> {
                return new PatienBOImpl();
            }
            case SESSION -> {
                return new SessionBOImpl();
            }
            case PAYMENT -> {
                return new PaymentBOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
