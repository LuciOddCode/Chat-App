package controller;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
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

    public Label newMessage;
    public String sender;
    public AnchorPane paneServer;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public void initialize(){
        new Thread(()->{
            try {

                serverSocket = new ServerSocket(3000);
                socket = serverSocket.accept();
                sendAMessage("Client Accepted");

                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);
               while (true){
                   Object receivedObject = objectInputStream.readObject();
                   Byte byteObject = (Byte) receivedObject;


                   if (byteObject==0){
                       break;
                   }

               }

                objectInputStream.close();
                socket.close();
                serverSocket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessageOnAction(ActionEvent actionEvent) {
        sendAMessage(txtTextTyper.getText());
    }

    private void sendAMessage(String message){
        newMessage.setText(message);
        vBoxClientTwo.setAlignment(Pos.CENTER_RIGHT);
        vBoxClientTwo.getChildren().add(newMessage);

    }

    private void receivedAMessage(String message){
        newMessage.setText(message);
        vBoxClientTwo.setAlignment(Pos.CENTER_LEFT);
        vBoxClientTwo.getChildren().add(newMessage);
    }
}
