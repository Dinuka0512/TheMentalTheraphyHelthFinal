package com.example.thementaltheraphyhelthfinal.bo;

import com.example.thementaltheraphyhelthfinal.bo.impl.UserBoImpl;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    private enum getBoType{
        USER
    }

    private SuperBo getBo(getBoType type){
        switch (type){
            case USER -> {
                return new UserBoImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
