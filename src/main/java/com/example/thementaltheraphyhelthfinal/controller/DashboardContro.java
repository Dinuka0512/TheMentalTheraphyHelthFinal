package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardContro implements Initializable {
    @Getter
    @Setter
    private static UserDto userDto;

    @FXML
    private AnchorPane mainBody;

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
        changeViewAsJobRole();
    }

    private void changeViewAsJobRole() {
        if(userDto.getJobRole().equals("admin")){
            btnUserManage.setVisible(true);
            btnProgramManage.setVisible(true);
            btnTheraphistManage.setVisible(true);
        }else{
            btnUserManage.setVisible(false);
            btnProgramManage.setVisible(false);
            btnTheraphistManage.setVisible(false);
        }
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

    @FXML
    void manageTheraphyPrograms(ActionEvent event) {
        navigateTo("/view/ManageTheraphyProgramsContro.fxml");
    }

    @FXML
    void manageTheraphist(ActionEvent event) {
        navigateTo("/view/ManageTheraphist.fxml");
    }

    @FXML
    void manageUsers(ActionEvent event) {
        navigateTo("/view/ManageUsers.fxml");
    }

    @FXML
    void managePatient(ActionEvent event) {

    }

    @FXML
    void managePayments(ActionEvent event) {

    }

    @FXML
    void managePrograms(ActionEvent event) {
        navigateTo("/view/TheraphyPrograms.fxml");
    }

    @FXML
    void manageSessions(ActionEvent event) {

    }

    @FXML
    void manageTherephist(ActionEvent event) {
        ManageTherapistContro.setUserDto(getUserDto());
        navigateTo("/view/ManageTheraphist.fxml");
    }

    @FXML
    void homeNavigate(MouseEvent event) {
        navigateToFull("/view/dashboard.fxml");
    }

    @FXML
    void logOut(MouseEvent event) {
        navigateToFull("/view/Login.fxml");
    }

    public void navigateTo(String path){
        try{
            Body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(path));
            Body.getChildren().add(load);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void navigateToFull(String path){
        try{
            mainBody.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(path));
            mainBody.getChildren().add(load);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
