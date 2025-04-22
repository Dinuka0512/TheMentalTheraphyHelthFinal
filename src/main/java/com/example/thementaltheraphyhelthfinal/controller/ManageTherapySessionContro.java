package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.*;
import com.example.thementaltheraphyhelthfinal.dto.*;
import com.example.thementaltheraphyhelthfinal.dto.tm.RegistrationTm;
import com.example.thementaltheraphyhelthfinal.dto.tm.TherapySessionTm;
import com.example.thementaltheraphyhelthfinal.entities.TheraphySession;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ManageTherapySessionContro implements Initializable {
    @FXML
    private TableColumn<TherapySessionTm, Double> colProFee;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TherapySessionTm, String> colDate;

    @FXML
    private TableColumn<TherapySessionTm, String> colTherapistId;

    @FXML
    private Label lblId;

    @FXML
    private TableColumn<TherapySessionTm, String> colPatientName;

    @FXML
    private Label lblFee;

    @FXML
    private ComboBox<String> comboPatient;

    @FXML
    private Button btnDelete;

    @FXML
    private Label lblProgramName;

    @FXML
    private TableColumn<TherapySessionTm, String> colProId;

    @FXML
    private Label lblTherapistName;

    @FXML
    private TableColumn<TherapySessionTm, String> colId;

    @FXML
    private Label lblAvailable;

    @FXML
    private TableColumn<TherapySessionTm, String> colTherapistName;

    @FXML
    private ComboBox<String> comboProgram;

    @FXML
    private DatePicker txtdateSelect;

    @FXML
    private TableColumn<TherapySessionTm, String> colPatientId;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<TherapySessionTm, String> colProName;

    @FXML
    private ComboBox<String> comboTherapist;

    @FXML
    private TableView<TherapySessionTm> tblSession;

    @FXML
    private Label lblPatientName;

    @FXML
    private Button btnReset;

    @FXML
    private Label lblBooked;

    private PatientDto patientDto;

    private TherapyProgramDto therapyProgramDto;
    private TherapySessionTm therapySessionTm;
    private TherapistDto therapistDto;

    //===========
    private SessionBO sessionBO = (SessionBO) BOFactory.getInstance().getBo(BOFactory.getBoType.SESSION);
    private TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBo(BOFactory.getBoType.THERAPHIST);
    private RegistrationBO registrationBO = (RegistrationBO) BOFactory.getInstance().getBo(BOFactory.getBoType.REGISTRATION);
    private TheraphyProgramBO theraphyProgramBO = (TheraphyProgramBO) BOFactory.getInstance().getBo(BOFactory.getBoType.PROGRAM);
    private PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBo(BOFactory.getBoType.PATIENT);
    //===========
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patient_Id"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colProId.setCellValueFactory(new PropertyValueFactory<>("program_Id"));
        colProName.setCellValueFactory(new PropertyValueFactory<>("program_Name"));
        colProFee.setCellValueFactory(new PropertyValueFactory<>("program_Fee"));
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapist_Id"));
        colTherapistName.setCellValueFactory(new PropertyValueFactory<>("therapist_Name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        pageReload();
    }

    private void pageReload() {
        loadTable();
        ClearText();
        genarateNewId();
        loadComboBoxIds();
        setupButttons();
        datePikerReset();
    }

    private void datePikerReset() {
        txtdateSelect.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #dddddd;"); // Optional: grey-out style
                }
            }
        });

    }

    private void setupButttons() {
        btnDelete.setDisable(true);
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
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
        ArrayList<TheraphySessionDto> allSessions = sessionBO.getAllSessions();
        ObservableList<TherapySessionTm> observableList = FXCollections.observableArrayList();

        for(TheraphySessionDto theraphySessionDto : allSessions){
            //HERE LOAD THERAPIST DETAILS
            TherapistDto therapistDetails = therapistBO.getTherapistDetails(theraphySessionDto.getTherapist_Id());

            if(therapistDetails!=null){
                TherapySessionTm therapySession = new TherapySessionTm(
                        theraphySessionDto.getSession_Id(),
                        theraphySessionDto.getPatient().getPatient_Id(),
                        theraphySessionDto.getPatient().getName(),
                        theraphySessionDto.getProgram().getProgram_Id(),
                        theraphySessionDto.getProgram().getName(),
                        theraphySessionDto.getAmount(),
                        therapistDetails.getTherapist_Id(),
                        therapistDetails.getName(),
                        theraphySessionDto.getDate().toString()
                );

                observableList.add(therapySession);
            }
        }

        tblSession.setItems(observableList);
    }

    @FXML
    void selectTheDate(ActionEvent event) {

    }

    private void ClearText() {
        comboPatient.setValue("Select Patient");
        comboProgram.setValue("Select Program");
        comboTherapist.setValue("Select Therapist");

        lblFee.setText("0.0/=");

        lblTherapistName.setText("Therapist name");
        lblPatientName.setText("Patient Name");
        lblProgramName.setText("Program Name");

        txtdateSelect.setValue(null);
    }

    private void genarateNewId() {
        String newId = sessionBO.genarateNewId();
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
        //HERE IS IF THERE SELECT THE PROGRAM FIRSTLY RESET THE THAT SELECTED
        comboTherapist.setValue("Select Therapist");
        lblTherapistName.setText("Therapist Name");

        if(comboProgram!=null){
            therapyProgramDto = theraphyProgramBO.getDetails(comboProgram.getSelectionModel().getSelectedItem());
            if(therapyProgramDto != null){
                lblProgramName.setText(therapyProgramDto.getName());
                lblFee.setText("Rs "+ therapyProgramDto.getFee() + "/=");

                //HERE SELECTED THE PROGRAM NOW NEED TO LOAD THAT PROGRAM SPECIALISTS THERAPISTS
                //HERE LOAD THE THERAPIST TO COMBO
                ArrayList<String> therapistsIds = therapistBO.loadtherapist(therapyProgramDto.getProgram_Id());
                if(therapistsIds!=null){
                    ObservableList<String> observableList = FXCollections.observableArrayList();
                    for(String id : therapistsIds){
                        observableList.add(id);
                    }

                    comboTherapist.setItems(observableList);
                }else{
                    comboTherapist.getItems().clear();
                }
            }
        }
    }


    @FXML
    void selectTherapist(ActionEvent event) {
        //THERE BEFORE SELECT THE THERAPIST THERE NEED TO CLEAR THE DATE SELECTED
        txtdateSelect.setValue(null);

        //HERE SELECT THE THERAPIST
        therapistDto = therapistBO.getTherapistDetails(comboTherapist.getSelectionModel().getSelectedItem());
        if(therapistDto!=null){
            lblTherapistName.setText(therapistDto.getName());

            //AFTER SET THAT THERE WE NEED TO CHECK DATA AVAILABILITY
            ArrayList<String> ids = sessionBO.sessionBookdedDates(therapistDto.getTherapist_Id());

            if(ids != null){
                // Convert to LocalDate
                List<LocalDate> bookedDates = ids.stream()
                        .map(LocalDate::parse)
                        .collect(Collectors.toList());

                // Set custom cell factory
                txtdateSelect.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);

                        if (empty || date == null) return;

                        if (date.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #dddddd;");
                        } else if (bookedDates.contains(date)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white;");
                        }
                    }
                });
            }
        }
    }

    @FXML
    void gettableDetails(MouseEvent event) {
        therapySessionTm = tblSession.getSelectionModel().getSelectedItem();

        if(therapySessionTm != null){
            //HERE SET THE VALES FOR FIELDS
            lblId.setText(therapySessionTm.getSessionId());
            comboPatient.setValue(therapySessionTm.getPatient_Id());
            comboProgram.setValue(therapySessionTm.getProgram_Id());
            comboTherapist.setValue(therapySessionTm.getTherapist_Id());
            txtdateSelect.setValue(LocalDate.parse(therapySessionTm.getDate()));

            btnDelete.setDisable(false);
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void save(ActionEvent event) {
        if(comboProgram.getValue()!=null){
            if(comboPatient.getValue() != null){
                if(comboTherapist.getValue() != null){
                    if(txtdateSelect.getValue()!=null){
                        saveSession();
                    }else {
                        CustomAlerts.notSelectedTheDate();
                    }
                }else{
                    CustomAlerts.comboboxValueNotSelected();
                }
            }else{
                CustomAlerts.comboboxValueNotSelected();
            }
        }else{
            CustomAlerts.comboboxValueNotSelected();
        }
    }

    private void saveSession() {
        TheraphySessionDto theraphySessionDto = new TheraphySessionDto(
                lblId.getText(),
                txtdateSelect.getValue(),
                therapyProgramDto.getFee(),
                therapyProgramDto,
                patientDto,
                comboTherapist.getSelectionModel().getSelectedItem()
        );

        //HERE NEED TO SAVE
        if(sessionBO.save(theraphySessionDto)){
            CustomAlerts.saved();
            pageReload();
        }
    }

    @FXML
    void update(ActionEvent event) {
        if(comboProgram.getValue()!=null){
            if(comboPatient.getValue() != null){
                if(comboTherapist.getValue() != null){
                    if(txtdateSelect.getValue()!=null){
                        updateSession();
                    }else {
                        CustomAlerts.notSelectedTheDate();
                    }
                }else{
                    CustomAlerts.comboboxValueNotSelected();
                }
            }else{
                CustomAlerts.comboboxValueNotSelected();
            }
        }else{
            CustomAlerts.comboboxValueNotSelected();
        }
    }

    private void updateSession() {
        //CREATING THE OBJ TO UPDATE
        TheraphySessionDto theraphySessionDto = new TheraphySessionDto(
                lblId.getText(),
                txtdateSelect.getValue(),
                therapyProgramDto.getFee(),
                therapyProgramDto,
                patientDto,
                therapistDto.getTherapist_Id()
        );

        if(sessionBO.update(theraphySessionDto)){
            CustomAlerts.update();
            pageReload();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        if(CustomAlerts.doYouWantToDelete()){
            if(sessionBO.delete(lblId.getText())){
                CustomAlerts.delete();
                pageReload();
            }
        }
    }

    @FXML
    void reset(ActionEvent event) {
        pageReload();
    }

}
