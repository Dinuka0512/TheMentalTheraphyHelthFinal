package com.example.thementaltheraphyhelthfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgramRegistrationContro implements Initializable {
    @FXML
    private TableColumn<?, ?> colProFee;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> comboProgram;

    @FXML
    private TableColumn<?, ?> colPatientId;

    @FXML
    private Label lblId;

    @FXML
    private TableColumn<?, ?> colPatientName;

    @FXML
    private Label lblFee;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<?> comboPatient;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<?, ?> colProName;

    @FXML
    private Label lblProgramName;

    @FXML
    private TableColumn<?, ?> colProId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private Label lblPatientName;

    @FXML
    private Button btnReset;

    @FXML
    private TableView<?> tblRegistration;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
