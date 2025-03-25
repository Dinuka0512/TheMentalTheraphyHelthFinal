package com.example.thementaltheraphyhelthfinal.config;


import com.example.thementaltheraphyhelthfinal.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfig {
    public static FactoryConfig factoryCongig;
    public SessionFactory sessionFactory;

    private FactoryConfig(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        //Annotated Classes
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(TherapyProgram.class);
        configuration.addAnnotatedClass(Therapist.class);
        configuration.addAnnotatedClass(TheraphySession.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfig getInstance(){
        return (factoryCongig == null)? factoryCongig = new FactoryConfig(): factoryCongig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
