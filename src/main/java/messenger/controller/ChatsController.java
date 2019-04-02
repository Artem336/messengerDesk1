package messenger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button chatsBackButton;

    @FXML
    void initialize() {
        assert chatsBackButton != null : "fx:id=\"chatsBackButton\" was not injected: check your FXML file 'chats.fxml'.";

    }
}

