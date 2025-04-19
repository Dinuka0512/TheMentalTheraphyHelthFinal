package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.RegistrationBO;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.RegistrationTm;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgramRegistrationContro implements Initializable {
    @FXML
    private TableColumn<RegistrationTm, Double> colProFee;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> comboProgram;

    @FXML
    private TableColumn<RegistrationTm, String> colPatientId;

    @FXML
    private Label lblId;

    @FXML
    private TableColumn<RegistrationTm, String> colPatientName;

    @FXML
    private Label lblFee;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> comboPatient;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<RegistrationTm, String> colProName;

    @FXML
    private Label lblProgramName;

    @FXML
    private TableColumn<RegistrationTm, String> colProId;

    @FXML
    private TableColumn<RegistrationTm, String> colId;

    @FXML
    private Label lblPatientName;

    @FXML
    private Button btnReset;

    @FXML
    private TableView<RegistrationTm> tblRegistration;

    //===========
    private RegistrationBO registrationBO = (RegistrationBO) BOFactory.getInstance().getBo(BOFactory.getBoType.REGISTRATION);
    //===========

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("registration_Id"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patient_Id"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("PatientName"));
        colProId.setCellValueFactory(new PropertyValueFactory<>("program_Id"));
        colProName.setCellValueFactory(new PropertyValueFactory<>("program_Name"));
        colProFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    
        pageReload();
    }

    private void pageReload() {
        loadTable();
        ClearText();
        genarateNewId();
        loadComboBoxIds();
    }

    private void loadComboBoxIds() {

    }

    private void loadTable() {
    }

    private void ClearText() {
        comboPatient.setValue("Select Patient");
        comboProgram.setValue("Select Program");
        lblFee.setText("0.0/=");

        lblPatientName.setText("Patient Name");
        lblProgramName.setText("Program Name");
    }

    private void genarateNewId() {
        String newId = registrationBO.genaratenewId();
        lblId.setText(newId);
    }

    @FXML
    void gettableDetails(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {

    }

}
