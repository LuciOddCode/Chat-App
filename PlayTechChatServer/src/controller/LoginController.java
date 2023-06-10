package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Navigation;


public class LoginController {
    public TextField txtUserName;
    public TextField txtPassword;
    public Button btnLogin;
    public AnchorPane paneLogin;

    public void goToPasswordSection(ActionEvent actionEvent) {
        String username = checkUsername(txtUserName.getText());
        if (username.equals(null)){
            txtPassword.requestFocus();
        }else {
            //make it red
        }

    }

    public void straightToLogin(ActionEvent actionEvent) {
        String checkPassword = checkPassword(txtUserName.getText(), txtPassword.getText());
        if (checkPassword!=null){
            Navigation.navigate("CHAT",paneLogin);
        }
    }

    public void LoginOnAction(ActionEvent actionEvent) {
        String checkPassword = checkPassword(txtUserName.getText(), txtPassword.getText());
        if (checkPassword!=null){
            Navigation.navigate("CHAT",paneLogin);
        }

    }

    public void ChangeColosOnMouseEnter(MouseEvent mouseEvent) {
    }

    private String checkUsername(String username) {
        if (username.equalsIgnoreCase("Server")) {
            return username;
        }
        return null;
    }

    private String checkPassword(String username, String password) {
        if (username.equals("Server")) {
            if (password.equals("admin")) {
                    return username;
            }
        }
        return null;
    }

}
