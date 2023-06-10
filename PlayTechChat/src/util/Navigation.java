package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;


    public static void navigate(String route,AnchorPane pane){
        Navigation.pane=pane;
//        Navigation.pane.getChildren().clear();

        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route){
            case  "LOGIN" :
                window.setTitle("LoginForm");
                initUI("Login.fxml");
                break;
            case  "CLIENTONE" :
                window.setTitle("Client One");
                initUI("Client1Form.fxml");
                break;
            case  "CLIENTTWO" :
                window.setTitle("Client Two");
                initUI("Client2Form.fxml");
                break;
            case  "CLIENTTHREE" :
                window.setTitle("Client Three");
                initUI("Client3Form.fxml");
                break;
            default:
                window.setTitle("LoginForm");
                initUI("Login.fxml");
                break;
        }
    }

    private static void initUI(String location) {
        try {
            Navigation.pane.getChildren().add(FXMLLoader
                    .load(Navigation.class.getResource("/view/" + location)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
