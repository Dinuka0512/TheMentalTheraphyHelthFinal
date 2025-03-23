package com.example.thementaltheraphyhelthfinal;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        launch();
//        FactoryConfig factoryConfig = FactoryConfig.getInstance();
    }
}