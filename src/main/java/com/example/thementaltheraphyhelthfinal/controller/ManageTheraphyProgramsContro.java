package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.TheraphyProgramBO;
import com.example.thementaltheraphyhelthfinal.dto.tm.TheraphyProgramTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    private TableColumn<TheraphyProgramTm, String> colId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("program_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadTable();
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

    @FXML
    void gettableDetails(MouseEvent event) {

    }
}
