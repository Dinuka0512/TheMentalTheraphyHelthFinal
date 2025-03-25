package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.dto.tm.TheraphyProgramTm;
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
import java.util.ResourceBundle;

public class TheraphyProgramsContro implements Initializable {
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

    }

    @FXML
    void closeSearchAnchor(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //DEFAULT TABLE
        colDefaultId.setCellValueFactory(new PropertyValueFactory<>("Program ID"));
        colDefaultName.setCellValueFactory(new PropertyValueFactory<>("Program Name"));
        coldefaultDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colDefaultFee.setCellValueFactory(new PropertyValueFactory<>("Fee (Rs.)"));

        //SEARCH TABLE
        colIdSerarch.setCellValueFactory(new PropertyValueFactory<>("Program ID"));
        colNameSearch.setCellValueFactory(new PropertyValueFactory<>("Program Name"));
        colDurationSearch.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colFeeSearch.setCellValueFactory(new PropertyValueFactory<>("Fee (Rs.)"));
    }
}
