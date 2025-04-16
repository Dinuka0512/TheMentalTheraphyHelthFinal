package com.example.thementaltheraphyhelthfinal.controller;

import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.UserBO;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.UserTm;
import com.example.thementaltheraphyhelthfinal.entities.User;
import com.example.thementaltheraphyhelthfinal.util.AlertsPack.CustomAlerts;
import com.example.thementaltheraphyhelthfinal.util.PasswordEncript.EncryptionUtil;
import com.example.thementaltheraphyhelthfinal.util.validationsPack.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.mindrot.jbcrypt.BCrypt;

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
    private Button btnUpdate;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    private UserDto user;

    @FXML
    void save(ActionEvent event) {
        if(Validation.isValidName(txtName.getText())){
            if(Validation.isValidName(txtAddress.getText())){
                if(Validation.isValidEmail(txtEmail.getText())){
                    //HERE CHECK IS EMAIL UNIQUE
                    if(isUniqueEmail()){
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
                        new Alert(Alert.AlertType.ERROR, "This Email has already exists").show();
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

    private boolean isUniqueEmail() {
        if(!userBO.isUniqueEmail(txtEmail.getText())){
            return false;
        }
        return true;
    }

    private void saveUser() {
        UserDto user = new UserDto();
        user.setName(txtName.getText());
        user.setEmail(txtEmail.getText());
        user.setContact(txtContact.getText());
        user.setAddress(txtAddress.getText());
        user.setJobRole(JobRolleCombo.getValue());
        //HERE ENCRYPT THE PASSWORD
        user.setPassword(BCrypt.hashpw(txtPw.getText(), BCrypt.gensalt()));


        if(userBO.saveUser(user)){
            loadTable();
            pageReset();
        }
    }

    @FXML
    void update(ActionEvent event) {
        if(Validation.isValidName(txtName.getText())){
            if(Validation.isValidName(txtAddress.getText())){
                if(Validation.isValidEmail(txtEmail.getText())){
                    //HERE CHECK IS EMAIL UNIQUE
                    if(isUniqueEmailForUpdate()){
                        if(Validation.isValidMobileNumber(txtContact.getText())){
                            if(Validation.isValidPassword(txtPw.getText())){
                                if(!JobRolleCombo.getSelectionModel().isEmpty()){
                                    updateUser();
                                }else{
                                    new Alert(Alert.AlertType.WARNING, "please Select The User Job role").show();
                                }
                            }else{
                                CustomAlerts.InvalidPassword();
                            }
                        }else{
                            CustomAlerts.isNotValidMobileNumber();
                        }
                    }else{
                        new Alert(Alert.AlertType.WARNING, "This Email is Already exists").show();
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

    private void updateUser() {
        UserDto user1 = new UserDto();
        user1.setName(txtName.getText());

        //HERE ENCRIPT THE PASSWORD
        user1.setPassword(BCrypt.hashpw(txtPw.getText(), BCrypt.gensalt()));

        user1.setAddress(txtAddress.getText());
        user1.setEmail(txtEmail.getText());
        user1.setContact(txtContact.getText());
        user1.setUser_Id(user.getUser_Id());

        user1.setJobRole(JobRolleCombo.getSelectionModel().getSelectedItem());

        if(userBO.update(user1)){
            loadTable();
            pageReset();
            CustomAlerts.update();
        }

    }

    @FXML
    void gettableDetails(MouseEvent event) {
        pageReset();
        UserTm selectUser = tblUsers.getSelectionModel().getSelectedItem();
        if(selectUser != null){
            //HERE SAVE THE USER DATA
            user = userBO.getUserDetails(selectUser.getEmail());

            txtName.setText(user.getName());
            txtAddress.setText(user.getAddress());

            try {
                String decryptedPassword = EncryptionUtil.decrypt(user.getPassword());
                txtPw.setText(decryptedPassword);
            } catch (Exception e) {
                e.printStackTrace();
                txtPw.setPromptText("Password decrypt failed");
            }

            txtContact.setText(user.getContact());
            txtEmail.setText(user.getEmail());
            JobRolleCombo.setValue(user.getJobRole());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    private boolean isUniqueEmailForUpdate() {
        if(user != null){
            if(userBO.isUniqueEmailForUpdate(user.getEmail(), user.getUser_Id())){
                //UNIQUE
                return true;
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "First Select & load data to text fields by selecting table rows").show();
        }
        return false;
    }


    @FXML
    void delete(ActionEvent event) {
        if(user != null){
            if(CustomAlerts.doYouWantToDelete()){
                //NEED TO DELETE
                if(userBO.delete(user.getUser_Id())){
                    loadTable();
                    CustomAlerts.delete();
                    pageReset();
                }
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "First Select & load data to text fields by selecting table rows").show();
        }
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

        JobRolleCombo.setValue("Select The User Role");

        user = null;

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
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

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

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
