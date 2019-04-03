package main.java.messenger.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import messenger.connection.ConnectionChat;
import messenger.connection.ConnectionListener;

import javax.script.Bindings;

public class ChatsController  implements ConnectionListener {
    private ConnectionChat connectionChat;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea log;

    @FXML
    private TextField nickNameField;

    @FXML
    private TextField messageField;

    @FXML
    private Button send;

    @FXML
    private Button chatsBackButton;

    @FXML
    void initialize() {
        try {
            connectionChat=new ConnectionChat(this,"127.0.0.1",8080);
        } catch (IOException e) {
            printMsg("Connection exception: "+e);
        }
        send.setOnAction(event -> {
            if (messageField.getText().isEmpty()) return;  //если поле пустое то ничего не делаем
            String message=messageField.getText();
            messageField.clear();
            connectionChat.sendMessage(nickNameField.getText()+": "+message);
        });

    }

    @Override
    public void onConnectionReady(ConnectionChat connection) {
        printMsg("Connection ready...");

    }

    @Override
    public void onReceiveString(ConnectionChat connection, String value) {
        printMsg(value);

    }

    @Override
    public void onDisconnect(ConnectionChat connection) {
        printMsg("Disconnect");
    }

    @Override
    public void onException(ConnectionChat connection, Exception e) {
        printMsg("Connection exception: "+e);

    }

    private synchronized void printMsg(String mesage){
        log.appendText(mesage+"\n");
//        log.setScrollTop(log.getLength());
    }
}
