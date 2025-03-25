package com.example.thementaltheraphyhelthfinal;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import com.example.thementaltheraphyhelthfinal.entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 650);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();

        FactoryConfig factoryConfig = FactoryConfig.getInstance();
        Session session = factoryConfig.getSession();
        Transaction transaction = session.beginTransaction();

        Query<TherapyProgram> query = session.createQuery("FROM TherapyProgram", TherapyProgram.class);
        List<TherapyProgram> resultList = query.getResultList();

        for (TherapyProgram therapyProgram : resultList){
            System.out.println(therapyProgram.getProgram_Id());
            System.out.println(therapyProgram.getName());
            System.out.println(therapyProgram.getDuration());
            System.out.println(therapyProgram.getFee());
            System.out.println("=====");
            System.out.println();
        }

        transaction.commit();
        session.close();


        Session session2 = factoryConfig.getSession();
        Transaction transaction2 = session2.beginTransaction();

        Query<TherapyProgram> query1 = session2.createQuery("FROM TherapyProgram", TherapyProgram.class);
        List<TherapyProgram> resultList2 = query1.getResultList();

        for (TherapyProgram therapyProgram1 : resultList){
            System.out.println(therapyProgram1.getProgram_Id());
            System.out.println(therapyProgram1.getName());
            System.out.println(therapyProgram1.getDuration());
            System.out.println(therapyProgram1.getFee());
            System.out.println("=====");
            System.out.println();
        }

        transaction2.commit();
        session2.close();


//        Session session = factoryConfig.getSession();
//        Transaction transaction = session.beginTransaction();
//        /*
//        * MT1001 Cognitive Behavioral Therapy 12 weeks 80,000.00
//        * MT1002 Mindfulness-Based Stress Reduction 8 weeks 50,000.00
//        * MT1003 Dialectical Behavior Therapy 16 weeks 100,000.00
//        * MT1004 Group Therapy Sessions 6 months 120,000.00
//        * MT1005 Family Counseling 3 months 40,000.00
//        * */
//
//        TherapyProgram therapyProgram1 = new TherapyProgram();
//        therapyProgram1.setProgram_Id("MT1001");
//        therapyProgram1.setName("Cognitive Behavioral Therapy");
//        therapyProgram1.setDuration("12 weeks");
//        therapyProgram1.setFee(80000.00);
//
//        TherapyProgram therapyProgram2 = new TherapyProgram();
//        therapyProgram2.setProgram_Id("MT1002");
//        therapyProgram2.setName("Mindfulness-Based Stress Reduction");
//        therapyProgram2.setDuration("8 weeks");
//        therapyProgram2.setFee(50000.00);
//
//        TherapyProgram therapyProgram3 = new TherapyProgram();
//        therapyProgram3.setProgram_Id("MT1003");
//        therapyProgram3.setName("Dialectical Behavior Therapy");
//        therapyProgram3.setDuration("16 weeks");
//        therapyProgram3.setFee(100000.00);
//
//        TherapyProgram therapyProgram4 = new TherapyProgram();
//        therapyProgram4.setProgram_Id("MT1004");
//        therapyProgram4.setName("Group Therapy Sessions");
//        therapyProgram4.setDuration("6 months");
//        therapyProgram4.setFee(120000.00);
//
//        TherapyProgram therapyProgram5 = new TherapyProgram();
//        therapyProgram5.setProgram_Id("MT1005");
//        therapyProgram5.setName("Family Counseling");
//        therapyProgram5.setDuration("3 months");
//        therapyProgram5.setFee(40000.00);
//
//        session.persist(therapyProgram1);
//        session.persist(therapyProgram2);
//        session.persist(therapyProgram3);
//        session.persist(therapyProgram4);
//        session.persist(therapyProgram5);
//
//        User user = new User();
//        user.setName("Dinuka");
//        user.setUser_Id("U001");
//        user.setAddress("96 D/1 Namaluwa Kothalawala Bandaragama");
//        user.setContact("0787135526");
//        user.setEmail("Dinuka0512@gmail.com");
//        user.setJobRole("admin");
//
//        session.persist(user);
//
//
//
//        transaction.commit();
//        session.close();
    }
}