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
        boolean isAUsername = checkUsername(txtUserName.getText());
        if (isAUsername){
            txtPassword.requestFocus();
        }else {
            txtUserName.setStyle("-fx-border-color: red;");
        }

    }

    public void straightToLogin(ActionEvent actionEvent) {
        boolean checkPassword = checkPassword(txtUserName.getText(), txtPassword.getText());
        if (checkPassword){
            Navigation.navigate
        }
    }

    public void LoginOnAction(ActionEvent actionEvent) {


    }

    public void ChangeColosOnMouseEnter(MouseEvent mouseEvent) {
    }

    private boolean checkUsername(String username) {

        if (username.equalsIgnoreCase("client1")) {
            return true;
        }else if (username.equalsIgnoreCase("client2")){
            return true;
        }else if (username.equalsIgnoreCase("client3")){
            return true;
        }

        return false;
    }

    private boolean checkPassword(String username, String password) {
        if (username.equalsIgnoreCase("client1")) {
            if (password.equals("passC1")){
                return true;
            }

        }else if (username.equalsIgnoreCase("client2")){
            if (password.equals("passC2")){
                return true;
            }
        }else if (username.equalsIgnoreCase("client3")){
            if (password.equals("passC3")){
                return true;
            }
        }
        return false;
    }

}
