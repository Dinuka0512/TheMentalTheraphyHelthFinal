package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.TherapistBO;
import com.example.thementaltheraphyhelthfinal.dto.TherapistDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.TherapistTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTheraphistId.setCellValueFactory(new PropertyValueFactory<>("therapist_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadTable();
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
    void gettableDetails(ActionEvent event) {
        TherapistTm therapistSelected = tblTheraphist.getSelectionModel().getSelectedItem();
        if(therapistSelected != null){
            //HERE SAVE THE SELECTED THERAPIST
            therapist = new TherapistTm(therapistSelected.getTherapist_Id(),therapistSelected.getName(), therapistSelected.getEmail(),therapistSelected.getAddress(), therapistSelected.getContact());

            txtAddress.setText(therapist.getAddress());
            txtEmail.setText(therapist.getEmail());
            txtContact.setText(therapist.getContact());
            txtName.setText(therapist.getName());
            lblTherapistId.setText(therapist.getTherapist_Id());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
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
