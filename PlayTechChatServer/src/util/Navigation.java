package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;


    public static void navigate(String route,AnchorPane pane){
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();

        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route){
            case  "LOGIN" :
                window.setTitle("LoginForm");
                initUI("Login.fxml");
                break;
            case  "CHAT" :
                window.setTitle("Server");
                initUI("ServerForm.fxml");
                break;
            default:
                window.setTitle("LoginForm");
                initUI("ServerForm.fxml");
                break;
        }
    }

    public static void initUI(String location) {
        try {
            Navigation.pane.getChildren().add(FXMLLoader
                    .load(Navigation.class.getResource("/view/" + location)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
