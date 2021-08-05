package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginFormController{
    public AnchorPane root;
    public JFXButton btnLogin;
    public JFXTextField txtName;
    public JFXPasswordField txtPassword;

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        setUI("MainForm.fxml");
    }


    public void setUI(String location) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/" + location))));
    }
    public void btnLoginClickOnAction(ActionEvent actionEvent) throws IOException {
        if (Pattern.compile("^[a-z]{5}$").matcher(txtName.getText()).matches()) {
            if (Pattern.compile("^[0-9]{4}$").matcher(txtPassword.getText()).matches()) {
                 String name = txtName.getText().trim();
                 String password = txtPassword.getText().trim();
            if (name.length()>0 && password.length()>0){

                 if (name.equalsIgnoreCase("ushan")
                         && password.equalsIgnoreCase("1234")){
                         setUI("DashBoardForm.fxml");

                 }else{
                     new Alert(Alert.AlertType.WARNING,"Try Again !!!!",
                        ButtonType.OK,ButtonType.NO).show();
                       }
              }else{
                   new Alert(Alert.AlertType.WARNING,"Empty !!!!",
                    ButtonType.OK,ButtonType.NO).show();
              }
        }else{
            txtPassword.setFocusColor(Paint.valueOf("red"));
            txtPassword.requestFocus();
        }
        }else{
            txtName.setFocusColor(Paint.valueOf("red"));
            txtName.requestFocus();

        }

    }
}

