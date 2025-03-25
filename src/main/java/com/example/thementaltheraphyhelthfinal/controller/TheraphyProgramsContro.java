package com.example.thementaltheraphyhelthfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TheraphyProgramsContro implements Initializable {
    @FXML
    private AnchorPane anchSearchView;

    @FXML
    private Button btnSearchClose;

    @FXML
    private TableColumn<?, ?> colDefaultFee;

    @FXML
    private TableColumn<?, ?> colDefaultId;

    @FXML
    private TableColumn<?, ?> colDefaultName;

    @FXML
    private TableColumn<?, ?> colDurationSearch;

    @FXML
    private TableColumn<?, ?> colFeeSearch;

    @FXML
    private TableColumn<?, ?> colIdSerarch;

    @FXML
    private TableColumn<?, ?> colNameSearch;

    @FXML
    private TableColumn<?, ?> coldefaultDuration;

    @FXML
    private TableView<?> tblDefault;

    @FXML
    private TableView<?> tblSearch;

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

    }
}
