package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.PatientBO;
import com.example.thementaltheraphyhelthfinal.bo.custom.RegistrationBO;
import com.example.thementaltheraphyhelthfinal.bo.custom.TheraphyProgramBO;
import com.example.thementaltheraphyhelthfinal.dto.PatientDto;
import com.example.thementaltheraphyhelthfinal.dto.RegistrationDto;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.RegistrationTm;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import com.example.thementaltheraphyhelthfinal.util.AlertsPack.CustomAlerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
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

    private PatientDto patientDto;
    private TherapyProgramDto therapyProgramDto;

    private RegistrationTm registrationTm;

    //===========
    private RegistrationBO registrationBO = (RegistrationBO) BOFactory.getInstance().getBo(BOFactory.getBoType.REGISTRATION);
    private TheraphyProgramBO theraphyProgramBO = (TheraphyProgramBO) BOFactory.getInstance().getBo(BOFactory.getBoType.PROGRAM);
    private PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBo(BOFactory.getBoType.PATIENT);
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
        //LOAD PROGRAM DATA 
        loadProgramDetails();

        //LOAD PATIENT DATA
        loadPatientDetails();
    }

    private void loadProgramDetails() {
        ArrayList<String> programIds = theraphyProgramBO.getAllIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for(String s : programIds){
            observableList.add(s);
        }
        comboProgram.setItems(observableList);
    }

    private void loadPatientDetails() {
        ArrayList<String> patientIds = patientBO.getAllIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for(String s : patientIds){
            observableList.add(s);
        }
        comboPatient.setItems(observableList);
    }

    private void loadTable() {
        //HERE LOAD THE TABLE
        ArrayList<RegistrationDto> all = registrationBO.getAll();
        ObservableList<RegistrationTm> observableList = FXCollections.observableArrayList();

        for(RegistrationDto registrationDto : all){
            RegistrationTm registrationTm = new RegistrationTm(
                    registrationDto.getRegistration_Id(),
                    registrationDto.getPatient().getPatient_Id(),
                    registrationDto.getPatient().getName(),
                    registrationDto.getTherapyProgram().getProgram_Id(),
                    registrationDto.getTherapyProgram().getName(),
                    registrationDto.getTherapyProgram().getFee()
            );

            observableList.add(registrationTm);
        }

        tblRegistration.setItems(observableList);
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
    void selectPatient(ActionEvent event) {
        if(comboPatient!= null){
            patientDto = patientBO.getDetails(comboPatient.getSelectionModel().getSelectedItem());
            if(patientDto != null){
                lblPatientName.setText(patientDto.getName());
            }
        }
    }

    @FXML
    void selectProgram(ActionEvent event) {
        if(comboProgram!=null){
            therapyProgramDto = theraphyProgramBO.getDetails(comboProgram.getSelectionModel().getSelectedItem());
            if(therapyProgramDto != null){
                lblProgramName.setText(therapyProgramDto.getName());
                lblFee.setText("Rs "+ therapyProgramDto.getFee() + "/=");
            }
        }
    }

    @FXML
    void gettableDetails(MouseEvent event) {
        registrationTm = tblRegistration.getSelectionModel().getSelectedItem();

        if(registrationTm != null){
            //HERE SET THE VALES FOR FIELDS
            lblId.setText(registrationTm.getRegistration_Id());
            comboProgram.setValue(registrationTm.getProgram_Id());
            comboPatient.setValue(registrationTm.getPatient_Id());
        }
    }

    @FXML
    void save(ActionEvent event) {
        if(comboProgram.getValue()!=null){
            if(comboPatient.getValue() != null){
                saveRegistration();
            }else{
                CustomAlerts.comboboxValueNotSelected();
            }
        }else{
            CustomAlerts.comboboxValueNotSelected();
        }
    }

    private void saveRegistration() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setRegistration_Id(lblId.getText());
        registrationDto.setPatient(patientDto);
        registrationDto.setTherapyProgram(therapyProgramDto);

        //HERE NEED TO SAVE
        if(registrationBO.save(registrationDto)){
            CustomAlerts.saved();
            pageReload();
        }
    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {
        pageReload();
    }

}
