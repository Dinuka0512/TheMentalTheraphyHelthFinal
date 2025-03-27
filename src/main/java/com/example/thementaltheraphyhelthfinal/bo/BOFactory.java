package com.example.thementaltheraphyhelthfinal.bo;

import com.example.thementaltheraphyhelthfinal.bo.custom.impl.TherapistBOimpl;
import com.example.thementaltheraphyhelthfinal.bo.custom.impl.TheraphyProgramBOImpl;
import com.example.thementaltheraphyhelthfinal.bo.custom.impl.UserBoImpl;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    public enum getBoType{
        USER, PROGRAM, THERAPHIST
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
            default -> {
                return null;
            }
        }
    }
}
