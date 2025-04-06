package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.TherapistBO;
import com.example.thementaltheraphyhelthfinal.dto.TherapistDto;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.TherapistTm;
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

public class ManageTherapistContro implements Initializable {
    //=========
    private TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBo(BOFactory.getBoType.THERAPHIST);
    //=========

    @FXML
    private TableColumn<TherapistTm, String> colContact;

    @FXML
    private TableColumn<TherapistTm, String> colName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TableColumn<TherapistTm, String> colEmail;

    @FXML
    private TableView<TherapistTm> tblTheraphist;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<TherapistTm, String> colAddress;

    @FXML
    private TextField txtAddress;

    @FXML
    private Button btnReset;

    @FXML
    private TableColumn<TherapistTm, String> colTheraphistId;

    private TherapistTm therapist;

    @FXML
    private Label lblTherapistId;
    private static UserDto userDto;

    public static UserDto getUserDto() {
        return userDto;
    }

    public static void setUserDto(UserDto userDto) {
        ManageTherapistContro.userDto = userDto;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTheraphistId.setCellValueFactory(new PropertyValueFactory<>("therapist_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        pageReset();
    }

    private void loadTable() {
        ArrayList<TherapistDto> dtos = therapistBO.loadTable();
        if(dtos != null){

            ObservableList<TherapistTm> observableList = FXCollections.observableArrayList();
            for (TherapistDto therapistDto : dtos){
                TherapistTm therapistTm = new TherapistTm(therapistDto.getTherapist_Id(), therapistDto.getName(), therapistDto.getEmail(), therapistDto.getAddress(), therapistDto.getContact());
                observableList.add(therapistTm);
            }

            tblTheraphist.setItems(observableList);
        }
    }

    @FXML
    void gettableDetails(MouseEvent event) {
        TherapistTm dto = tblTheraphist.getSelectionModel().getSelectedItem();
        if(dto != null){
            //HERE SAVE THE SELECTED THERAPIST
            therapist = new TherapistTm(dto.getTherapist_Id(), dto.getName(), dto.getEmail(), dto.getAddress(), dto.getContact());

            lblTherapistId.setText(therapist.getTherapist_Id());
            txtName.setText(therapist.getName());
            txtAddress.setText(therapist.getAddress());
            txtEmail.setText(therapist.getEmail());
            txtContact.setText(therapist.getContact());

            if(userDto.getJobRole().equals("admin")){
                btnSave.setDisable(true);
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }
        }
    }

    @FXML
    void save(ActionEvent event) {
        IsValidSave();
    }

    @FXML
    void update(ActionEvent event) {
        isValidUpdate();
    }

    private void isValidUpdate() {
        if(Validation.isValidName(txtName.getText())){
            if(Validation.isValidName(txtAddress.getText())){
                if(Validation.isValidEmail(txtEmail.getText())){
                    if(therapistBO.isValidToUpdate(txtEmail.getText(), lblTherapistId.getText())){
                        if(Validation.isValidMobileNumber(txtContact.getText())){
                            updateTherapist();
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

    private void updateTherapist() {
        if(therapist!=null){
            TherapistDto dto= new TherapistDto();
            dto.setTherapist_Id(lblTherapistId.getText());
            dto.setContact(txtContact.getText());
            dto.setAddress(txtAddress.getText());
            dto.setEmail(txtEmail.getText());
            dto.setName(txtName.getText());

            if(therapistBO.update(dto)){
                CustomAlerts.update();
                pageReset();
            }
        }
    }

    @FXML
    void delete(ActionEvent event) {
        if(therapist!=null){
            if(CustomAlerts.doYouWantToDelete()){
                if(therapistBO.delete(new TherapistDto(therapist.getTherapist_Id(), therapist.getName(), therapist.getAddress(), therapist.getContact(), therapist.getEmail()))){
                    CustomAlerts.delete();
                    pageReset();
                }
            }
        }
    }

    public void IsValidSave(){
        if(Validation.isValidName(txtName.getText())){
            if(Validation.isValidName(txtAddress.getText())){
                if(Validation.isValidEmail(txtEmail.getText())){
                    if(therapistBO.isValidToSave(txtEmail.getText())){
                        if(Validation.isValidMobileNumber(txtContact.getText())){
                            saveTherapist();
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

    private void saveTherapist() {
        TherapistDto dto= new TherapistDto();
        dto.setTherapist_Id(lblTherapistId.getText());
        dto.setContact(txtContact.getText());
        dto.setAddress(txtAddress.getText());
        dto.setEmail(txtEmail.getText());
        dto.setName(txtName.getText());


        if(therapistBO.save(dto)){
            CustomAlerts.saved();
            pageReset();
        }
    }

    @FXML
    void reset(ActionEvent event) {
        pageReset();
    }

    private void pageReset() {
        if(getUserDto()!= null){
            if(userDto.getJobRole().equals("admin")){
                btnSave.setDisable(false);
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);
            }else{
                btnSave.setDisable(true);
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);
            }
        }
        txtName.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtAddress.setText("");

        txtName.setPromptText("Name");
        txtContact.setPromptText("Contact");
        txtEmail.setPromptText("Email");
        txtAddress.setPromptText("Address");

        lblTherapistId.setText(therapistBO.genarateID());
        loadTable();
    }
}
