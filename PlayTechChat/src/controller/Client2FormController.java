package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client2FormController {
    public Label lblServer;
    public Circle serverActiveDot;
    public Label lblClient1;
    public Circle clientActiveDot;
    public Label lblClient2;
    public Circle clientTwoActiveDot;
    public ImageView imgEmoji;
    public TextField txtTextTyper;
    public Button btnImageChooser;
    public Button btnSendMessage;
    public VBox vBoxClientTwo;
    public HBox hBoxClientTwo;
    public Label lblIncomingMessage;
    public AnchorPane paneClientTwo;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Socket socket;
    String message;

    HBox newHBoxClientTwo = new HBox();


    public void initialize() {
        try {
            socket = new Socket("localhost", 3000);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());

            while (socket.isClosed()) {
                message = dataInputStream.readUTF();
                newHBoxClientTwo.getChildren().add(new Label(message));
                vBoxClientTwo.getChildren().add(newHBoxClientTwo);

            }


            dataOutputStream.writeUTF("Client 1 Joined Chat");
            dataOutputStream.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessageOnAction(ActionEvent actionEvent) {
        String reply = "";


        reply = txtTextTyper.getText();
        try {
            dataOutputStream.writeUTF(reply);


            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendImgOnAction(ActionEvent actionEvent) {
    }

    public void showEmojiOnClick(MouseEvent mouseEvent) {
    }
}
