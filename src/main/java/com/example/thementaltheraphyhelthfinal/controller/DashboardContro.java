package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.SessionBO;
import com.example.thementaltheraphyhelthfinal.dto.TheraphySessionDto;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardContro implements Initializable {
    //=========
    private SessionBO sessionBO = (SessionBO) BOFactory.getInstance().getBo(BOFactory.getBoType.SESSION);
    //=========
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


    @FXML
    private LineChart<String, Number> barchart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setpage();
        changeViewAsJobRole();
        loadBarchart();
    }

    private void loadBarchart() {
        ArrayList<TheraphySessionDto> allSessions = sessionBO.getAllSessions();

        //VALUES ADDING TO BAR CHART
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("INCOME BARCHART");

        for(TheraphySessionDto theraphySessionDto : allSessions){
            String date = String.valueOf(theraphySessionDto.getDate());
            double amount = theraphySessionDto.getAmount();

            series.getData().add(new XYChart.Data<>(date, amount));
        }

        barchart.getData().add(series);
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
        navigateTo("/view/ManageTheraphyPrograms.fxml");
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
        navigateTo("/view/Patient.fxml");
    }

    @FXML
    void managePayments(ActionEvent event) {

    }

    @FXML
    void manageRgistration(ActionEvent event) {
        navigateTo("/view/TheraphySession.fxml");
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
