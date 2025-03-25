package com.example.thementaltheraphyhelthfinal.controller;


import com.example.thementaltheraphyhelthfinal.bo.BOFactory;
import com.example.thementaltheraphyhelthfinal.bo.custom.UserBO;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
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
//        UserDto dto = userBO.getUserDetails(txtEmail.getText());
//        System.out.println(dto);
//        if(dto != null){
//            System.out.println(dto.getUser_Id());
//            System.out.println(dto.getName());
//            System.out.println(dto.getContact());
//            System.out.println(dto.getJobRole());
//        }else{
//            System.out.println("Null");
//        }
    }
}