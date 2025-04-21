package com.example.thementaltheraphyhelthfinal.controller;


import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.UserBO;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.util.AlertsPack.CustomAlerts;
import com.example.thementaltheraphyhelthfinal.util.PasswordEncript.EncryptionUtil;
import com.example.thementaltheraphyhelthfinal.util.exceptionsPack.CustomEXception;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginContro {
    //====
    private UserBO userBO = (UserBO) BOFactory.getInstance().getBo(BOFactory.getBoType.USER);
    //====


    @FXML
    private AnchorPane mainAnch;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPw;

    @FXML
    void LogIn(ActionEvent event) {
        //HERE CHECK THE PASSWORD AND EMAIL LOG INTO LOG IN TO THE DASHBOARD
        UserDto dto = userBO.getUserDetails(txtEmail.getText());
        checkUser(dto);
    }

    private void checkUser(UserDto dto) {
        if(dto != null){
            if(txtEmail.getText().equals(dto.getEmail())){
                //EMAIL IS OK

                if (txtPw.getText().equals(dto.getPassword()) || txtPw.getText().equals(EncryptionUtil.decrypt(dto.getPassword()))){
                    //PASSWORD IS OK
                    DashboardContro.setUserDto(dto);
                    navigateToDashbord();
                }else {
                    CustomAlerts.InvalidPassword();
                }
            }else{
                CustomAlerts.EmailNotFound();
            }
        }else{
            CustomAlerts.EmailNotFound();
        }
    }

    private void navigateToDashbord() {
        try {
            mainAnch.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
            mainAnch.getChildren().add(load);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}