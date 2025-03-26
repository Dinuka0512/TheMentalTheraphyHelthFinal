package com.example.thementaltheraphyhelthfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ManageTheraphistContro {
    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableView<?> tblTheraphist;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TextField txtAddress;

    @FXML
    private Button btnReset;

    @FXML
    private TableColumn<?, ?> colTheraphistId;

    @FXML
    void gettableDetails(MouseEvent event) {

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
