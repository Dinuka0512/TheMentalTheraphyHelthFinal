package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardContro implements Initializable {
    @Getter
    @Setter
    private static UserDto userDto;

    @FXML
    private Label txtUEmail;

    @FXML
    private Label txtUJob;

    @FXML
    private Label txtUName;

    @FXML
    private AnchorPane Body;

    @FXML
    private AnchorPane navigationsList;

    @FXML
    private Button btnProgramManage;

    @FXML
    private Button btnTheraphistManage;

    @FXML
    private Button btnUserManage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setpage();
    }

    private void setpage() {
        txtUName.setText(userDto.getName());
        txtUEmail.setText(userDto.getEmail());
        txtUJob.setText(userDto.getJobRole());

        if(userDto.getJobRole().equals("admin")){
            setAdminPanel();
        }
    }

    private void setAdminPanel() {
        btnProgramManage.setVisible(true);
        btnTheraphistManage.setVisible(true);
        btnUserManage.setVisible(true);
    }

    @FXML
    void openNav(MouseEvent event) {
        navigationsList.setVisible(true);
    }

    @FXML
    void bodyClick(MouseEvent event) {
        navigationsList.setVisible(false);
    }


}
