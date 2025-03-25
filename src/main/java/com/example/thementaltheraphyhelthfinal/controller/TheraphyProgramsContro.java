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
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class TheraphyProgramsContro implements Initializable {
    //======
    private TheraphyProgramBO theraphyProgramBO = (TheraphyProgramBO) BOFactory.getInstance().getBo(BOFactory.getBoType.PROGRAM);
    //======

    @FXML
    private AnchorPane anchSearchView;

    @FXML
    private Button btnSearchClose;

    @FXML
    private TableColumn<TheraphyProgramTm, Double> colDefaultFee;

    @FXML
    private TableColumn<TheraphyProgramTm, String> colDefaultId;

    @FXML
    private TableColumn<TheraphyProgramTm, String> colDefaultName;

    @FXML
    private TableColumn<TheraphyProgramTm, String> colDurationSearch;

    @FXML
    private TableColumn<TheraphyProgramTm, Double> colFeeSearch;

    @FXML
    private TableColumn<TheraphyProgramTm, String> colIdSerarch;

    @FXML
    private TableColumn<TheraphyProgramTm, String> colNameSearch;

    @FXML
    private TableColumn<TheraphyProgramTm, String> coldefaultDuration;

    @FXML
    private TableView<TheraphyProgramTm> tblDefault;

    @FXML
    private TableView<TheraphyProgramTm> tblSearch;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnSearch(ActionEvent event) {
        anchSearchView.setVisible(true);
        btnSearchClose.setVisible(true);

        //HERE SEARCH

    }

    @FXML
    void closeSearchAnchor(ActionEvent event) {
        anchSearchView.setVisible(false);
        btnSearchClose.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //DEFAULT TABLE
        colDefaultId.setCellValueFactory(new PropertyValueFactory<>("program_Id"));
        colDefaultName.setCellValueFactory(new PropertyValueFactory<>("name"));
        coldefaultDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDefaultFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        //SEARCH TABLE
        colIdSerarch.setCellValueFactory(new PropertyValueFactory<>("program_Id"));
        colNameSearch.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDurationSearch.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFeeSearch.setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadTable();
    }

    private void loadTable() {
        ArrayList<TheraphyProgramTm> programList = theraphyProgramBO.loadTable();
        ObservableList<TheraphyProgramTm> observableList = FXCollections.observableArrayList();

        for(TheraphyProgramTm theraphyProgramTm : programList){
            observableList.add(theraphyProgramTm);
        }
        tblDefault.setItems(observableList);
    }
}
