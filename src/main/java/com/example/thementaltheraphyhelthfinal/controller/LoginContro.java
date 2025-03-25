package com.example.thementaltheraphyhelthfinal.controller;


import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.UserBO;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.util.exceptionsPack.CustomEXception;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginContro {
    //====
    private UserBO userBO = (UserBO) BOFactory.getInstance().getBo(BOFactory.getBoType.USER);
    //====

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPw;

    @FXML
    void LogIn(ActionEvent event) {
        //HERE CHECK THE PASSWORD AND EMAIL LOG INTO LOG IN TO THE DASHBOARD
        try{
            UserDto dto = userBO.getUserDetails(txtEmail.getText());
            CustomEXception.IsNull(dto);
            System.out.println(dto.getJobRole());
        }catch (CustomEXception e){
            e.printStackTrace();
        }

    }
}