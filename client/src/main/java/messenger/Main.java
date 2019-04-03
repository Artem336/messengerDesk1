package main.java.messenger;

import com.sun.corba.se.spi.legacy.connection.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import messenger.connection.ConnectionChat;
import messenger.connection.ConnectionListener;

public class Main extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception{
        String fxmlFile= "/main/resources/fxml/chats.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        primaryStage.setTitle("MyMessenger");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
