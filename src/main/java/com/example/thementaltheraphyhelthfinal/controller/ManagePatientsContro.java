package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.PatientBO;
import com.example.thementaltheraphyhelthfinal.dto.PatientDto;
import com.example.thementaltheraphyhelthfinal.dto.TherapistDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.PatientTm;
import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.util.AlertsPack.CustomAlerts;
import com.example.thementaltheraphyhelthfinal.util.validationsPack.Validation;
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

public class ManagePatientsContro implements Initializable {

    @FXML
    private TableColumn<PatientTm, String> colContact;

    @FXML
    private TableColumn<PatientTm, String> colName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TableColumn<PatientTm, String> colPatientsId;

    @FXML
    private TableView<PatientTm> tblPatients;

    @FXML
    private TableColumn<PatientTm, String> colEmail;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Label lblPatientId;

    @FXML
    private TableColumn<PatientTm, String> colAddress;

    @FXML
    private TextField txtAddress;

    @FXML
    private Button btnReset;

    //=========
    private PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBo(BOFactory.getBoType.PATIENT);
    //=========

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPatientsId.setCellValueFactory(new PropertyValueFactory<>("patient_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        pageReset();
    }

    private void pageReset() {
        loadTable();
        clearTexts();
        loadNextId();

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }

    private void loadNextId() {
        lblPatientId.setText(patientBO.generateNewId());
    }

    private void clearTexts() {
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtName.setText("");

        txtEmail.setPromptText("email");
        txtAddress.setPromptText("address");
        txtContact.setPromptText("contact");
        txtName.setPromptText("name");
    }

    private void loadTable() {
        ArrayList<PatientDto> result = patientBO.getAll();
        ObservableList<PatientTm> observableList = FXCollections.observableArrayList();
        for (PatientDto patientDto : result){
            PatientTm patientTm = new PatientTm(patientDto.getPatient_Id(), patientDto.getName(), patientDto.getEmail(), patientDto.getAddress(),patientDto.getContact());
            observableList.add(patientTm);
        }

        tblPatients.setItems(observableList);
    }

    @FXML
    void save(ActionEvent event) {
        IsValidSave();
    }

    public void IsValidSave(){
        if(Validation.isValidName(txtName.getText())){
            if(Validation.isValidName(txtAddress.getText())){
                if(Validation.isValidEmail(txtEmail.getText())){
                    if(patientBO.isValidToSave(txtEmail.getText())){
                        if(Validation.isValidMobileNumber(txtContact.getText())){
                            savePatient();
                        }else{
                            CustomAlerts.isNotValidMobileNumber();
                        }
                    }else {
                        new Alert(Alert.AlertType.ERROR, "This Email is already exist").show();
                    }
                }else{
                    CustomAlerts.isNotValidEmail();
                }
            }else{
                CustomAlerts.isNotValidName();
            }
        }else {
            CustomAlerts.isNotValidName();
        }
    }

    private void savePatient() {
        PatientDto patientDto = new PatientDto(lblPatientId.getText(), txtName.getText(), txtEmail.getText(), txtAddress.getText(), txtContact.getText());

        if(patientBO.save(patientDto)){
            CustomAlerts.saved();
            pageReset();
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
        pageReset();
    }

    @FXML
    void gettableDetails(MouseEvent event) {
        PatientTm patientTm = tblPatients.getSelectionModel().getSelectedItem();
        if(patientTm!= null){
            txtName.setText(patientTm.getPatient_Id());
            txtContact.setText(patientTm.getContact());
            txtAddress.setText(patientTm.getAddress());
            txtEmail.setText(patientTm.getEmail());
            lblPatientId.setText(patientTm.getPatient_Id());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
}
