package main.java.messenger.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button logInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {
        logInButton.setOnAction(event -> {
            //метод trim удаляет пробелы в строке
            String loginText=login_field.getText().trim();
            String passwordText=password_field.getText().trim();
            if(!loginText.equals("")&&!passwordText.equals("")){
                loginUser(loginText,passwordText);
            }else
                System.out.println("Login or Password is Empty");

        });


        loginSignUpButton.setOnAction(event -> {
        loginSignUpButton.getScene().getWindow().hide();

            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/main/resources/fxml/registration.fxml"));

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root=fxmlLoader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });
}

    private void loginUser(String loginText, String passwordText) {
    }
}
