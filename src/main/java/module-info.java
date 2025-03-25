module com.example.thementaltheraphyhelthfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    /*
    * requires static lombok;
    * ---->>>>	Lombok is needed at compile-time but
    *  not required at runtime.
    *
    * requires lombok;
    * ---->>>>	Lombok is needed both at compile-time
    * and runtime (which is unnecessary and can cause
    * issues).
    * */
    requires static lombok;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens com.example.thementaltheraphyhelthfinal.entities to org.hibernate.orm.core;

    opens com.example.thementaltheraphyhelthfinal to javafx.fxml;
    exports com.example.thementaltheraphyhelthfinal;
    exports com.example.thementaltheraphyhelthfinal.controller;
    opens com.example.thementaltheraphyhelthfinal.controller to javafx.fxml;
}