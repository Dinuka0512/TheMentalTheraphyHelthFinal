package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.TheraphyProgramBO;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.TheraphyProgramTm;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageTheraphyProgramsContro implements Initializable {
    //======
    private TheraphyProgramBO theraphyProgramBO = (TheraphyProgramBO) BOFactory.getInstance().getBo(BOFactory.getBoType.PROGRAM);
    //======
    @FXML
    private TableView<TheraphyProgramTm> tblPrograms;

    @FXML
    private TableColumn<TheraphyProgramTm, String> colName;

    @FXML
    private TableColumn<TheraphyProgramTm, String> colDuration;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblProID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtFee;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TheraphyProgramTm, Double> colFee;

    @FXML
    private TextField txtDuration;

    private TheraphyProgramTm program;

    @FXML
    private TableColumn<TheraphyProgramTm, String> colId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("program_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        pageReLoad();
    }

    private void pageReLoad() {
        //TABLE LOAD
        loadTable();

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);

        genarateIDS();
        clearText();
    }

    private void clearText() {
        txtName.setText("");
        txtDuration.setText("");
        txtFee.setText("");

        txtName.setPromptText("Name");
        txtDuration.setPromptText("Duration");
        txtFee.setPromptText("Fee");
    }

    private void genarateIDS() {
        lblProID.setText(theraphyProgramBO.generateNewId());
    }

    private void loadTable() {
        ArrayList<TheraphyProgramTm> programList = theraphyProgramBO.loadTable();
        ObservableList<TheraphyProgramTm> observableList = FXCollections.observableArrayList();

        for(TheraphyProgramTm theraphyProgramTm : programList){
            observableList.add(theraphyProgramTm);
        }
        tblPrograms.setItems(observableList);
    }

    @FXML
    private Button btnReset;

    @FXML
    void save(ActionEvent event) {
        if(Validation.isValidName(txtName.getText())){
            if(Validation.isValidDouble(txtFee.getText())){
                saveHere();
            }else{
                CustomAlerts.isNotValidDouble();
            }
        }else{
            CustomAlerts.isNotValidName();
        }
    }

    private void saveHere() {
        TherapyProgramDto therapyProgramDto = new TherapyProgramDto();
        therapyProgramDto.setDuration(txtDuration.getText());
        therapyProgramDto.setFee(Double.parseDouble(txtFee.getText()));
        therapyProgramDto.setName(txtName.getText());
        therapyProgramDto.setProgram_Id(lblProID.getText());

        if(theraphyProgramBO.save(therapyProgramDto)){
            CustomAlerts.saved();
            pageReLoad();
        }
    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to delete Are you suer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get()== ButtonType.YES){
            //DELETE HERE
            if(program!=null){
                if(theraphyProgramBO.delete(program.getProgram_Id())){
                    CustomAlerts.delete();
                    pageReLoad();
                }
            }
        }
    }

    @FXML
    void reset(ActionEvent event) {
        pageReLoad();
    }

    @FXML
    void gettableDetails(MouseEvent event) {
        if(tblPrograms.getSelectionModel().getSelectedItem()!=null){
            program = tblPrograms.getSelectionModel().getSelectedItem();
            if(program!=null){
                txtName.setText(program.getName());
                txtDuration.setText(program.getDuration());
                txtFee.setText(String.valueOf(program.getFee()));
                lblProID.setText(program.getProgram_Id());

                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
                btnSave.setDisable(true);
            }
        }
    }
}
