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

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField registrationLogin;

    @FXML
    private PasswordField registrationPassword;

    @FXML
    private Button RegistrationSignUpButton;

    @FXML
    private Button registrationBackButton;

    @FXML
    private TextField registrationName;

    @FXML
    private TextField registrationTel;

    @FXML
    void initialize() {
        registrationBackButton.setOnAction(event -> {
            registrationBackButton.getScene().getWindow().hide();

            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/main/resources/fxml/login.fxml"));
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
}
