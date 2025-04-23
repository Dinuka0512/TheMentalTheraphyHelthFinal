module com.example.thementaltheraphyhelthfinal {
    requires static lombok;
//    requires lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;


    opens com.example.thementaltheraphyhelthfinal.entities to org.hibernate.orm.core;

    opens com.example.thementaltheraphyhelthfinal to javafx.fxml;
    exports com.example.thementaltheraphyhelthfinal;

    exports com.example.thementaltheraphyhelthfinal.controller;
    opens com.example.thementaltheraphyhelthfinal.controller to javafx.fxml;

    opens com.example.thementaltheraphyhelthfinal.dto.tm to javafx.base;
}