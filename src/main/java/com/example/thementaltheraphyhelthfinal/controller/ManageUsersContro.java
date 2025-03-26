package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.UserBO;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.UserTm;
import com.example.thementaltheraphyhelthfinal.entities.User;
import com.example.thementaltheraphyhelthfinal.util.AlertsPack.CustomAlerts;
import com.example.thementaltheraphyhelthfinal.util.validationsPack.Validation;
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

public class ManageUsersContro implements Initializable {
    //=======
    private UserBO userBO = (UserBO) BOFactory.getInstance().getBo(BOFactory.getBoType.USER);
    //=======

    @FXML
    private ComboBox<String> JobRolleCombo;

    @FXML
    private TableColumn<UserTm, String> colContact;

    @FXML
    private TableColumn<UserTm, String> colName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private TableColumn<UserTm, String> colJobRole;

    @FXML
    private TextField txtEmail;

    @FXML
    private TableColumn<UserTm, String> colUserId;

    @FXML
    private TableColumn<UserTm, String> colEmail;

    @FXML
    private TableView<UserTm> tblUsers;

    @FXML
    private TableColumn<UserTm, String> colAddress;

    @FXML
    private TextField txtAddress;

    @FXML
    private Button btnReset;

    @FXML
    private PasswordField txtPw;

    @FXML
    void save(ActionEvent event) {
        if(Validation.isValidName(txtName.getText())){
            if(Validation.isValidName(txtAddress.getText())){
                if(Validation.isValidEmail(txtEmail.getText())){
                    //HERE CHECK IS EMAIL UNIQUE
                    isUniqueEmail();
                    if(Validation.isValidMobileNumber(txtContact.getText())){
                        if(Validation.isValidPassword(txtPw.getText())){
                            if(!JobRolleCombo.getSelectionModel().isEmpty()){
                                saveUser();
                            }else{
                                new Alert(Alert.AlertType.WARNING, "please Select The User Job role").show();
                            }
                        }else{
                            CustomAlerts.InvalidPassword();
                        }
                    }else{
                        CustomAlerts.isNotValidMobileNumber();
                    }
                }else {
                    CustomAlerts.isNotValidEmail();
                }
            }else {
                CustomAlerts.isNotValidName();
            }
        }else{
            CustomAlerts.isNotValidName();
        }
    }

    private void isUniqueEmail() {
        if(!userBO.isUniqueEmail(txtEmail.getText())){
            //THE EMAIL IS NOT UNIQUE
            new Alert(Alert.AlertType.ERROR, "This Email has already exists").show();
        }

    }

    private void saveUser() {
        UserDto user = new UserDto();
        user.setName(txtName.getText());
        user.setEmail(txtEmail.getText());
        user.setContact(txtContact.getText());
        user.setAddress(txtAddress.getText());
        user.setJobRole(JobRolleCombo.getValue());
        user.setPassword(txtPw.getText());

        if(userBO.saveUser(user)){
            loadTable();
            pageReset();
        }
    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {
        pageReset();
    }

    private void pageReset() {
        txtAddress.setText("");
        txtEmail.setText("");
        txtName.setText("");
        txtContact.setText("");
        txtPw.setText("");

        txtName.setPromptText("Name");
        txtAddress.setPromptText("Address");
        txtContact.setPromptText("Contact");
        txtEmail.setPromptText("Email");
        txtPw.setPromptText("Password");

        JobRolleCombo.setValue("");
        JobRolleCombo.setPromptText("Select The User Rolle");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //COMBO BOX
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("admin");
        observableList.add("Receptionist");
        JobRolleCombo.setItems(observableList);

        colUserId.setCellValueFactory(new PropertyValueFactory<>("user_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));

        loadTable();
    }

    private void loadTable() {
        ArrayList<UserDto> userDtos = userBO.loadTable();
        ObservableList<UserTm> observableList = FXCollections.observableArrayList();
        for(UserDto userDto : userDtos){
            UserTm userTm = new UserTm(
                    userDto.getUser_Id(),
                    userDto.getName(),
                    userDto.getAddress(),
                    userDto.getJobRole(),
                    userDto.getPassword(),
                    userDto.getContact(),
                    userDto.getEmail()
                    );

            observableList.add(userTm);
        }

        tblUsers.setItems(observableList);
    }
}
