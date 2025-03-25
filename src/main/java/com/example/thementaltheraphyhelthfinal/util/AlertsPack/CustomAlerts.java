package com.example.thementaltheraphyhelthfinal.util.AlertsPack;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CustomAlerts extends Alert {
    public CustomAlerts(AlertType alertType) {
        super(alertType);
    }

    public CustomAlerts(AlertType alertType, String s, ButtonType... buttonTypes) {
        super(alertType, s, buttonTypes);
    }

    public static void InvalidPassword(){
        new CustomAlerts(AlertType.WARNING,"Invalid Password").show();
    }

    public static void EmailNotFound(){
        new CustomAlerts(AlertType.WARNING,"The Acount has not found \n Something Went Wrong").show();
    }
}
