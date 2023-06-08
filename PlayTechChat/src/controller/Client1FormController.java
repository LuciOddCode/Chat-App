package controller;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client1FormController {
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
    public AnchorPane paneClientOne;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Socket socket;
    String message;
    Byte byteObject;
    Object receivedObject;
    ObjectInputStream objectInputStream;
    InputStream inputStream;
    ImageView view;

    HBox newHBoxClientTwo=new HBox();

    public void initialize() {
        new Thread(()->{
            try {
                socket=new Socket("localhost",3000);
               inputStream=socket.getInputStream();

               objectInputStream=new ObjectInputStream(inputStream);
               Image userImage= new Image("assets/img/icons8-user-100.png");
                ImageView user=new ImageView(userImage);
                while (true){


                     receivedObject = objectInputStream.readObject();

                    if (receivedObject instanceof ImageView){
                        byteObject = (Byte) receivedObject;

                        byte [] imageData=new byte[]{byteObject} ;
                        Image image=new Image(new ByteArrayInputStream(imageData));
                        view=new ImageView(image);

                        view.fitHeightProperty();
                        hBoxClientTwo.setAlignment(Pos.CENTER_LEFT);
                        hBoxClientTwo.getChildren().add(user);
                        hBoxClientTwo.getChildren().add(view);

                        vBoxClientTwo.getChildren().add(hBoxClientTwo);
                    }else {
                        message= objectInputStream.readUTF();
                        lblIncomingMessage.setText("Client : "+message);

                        vBoxClientTwo.getChildren().clear();
                        vBoxClientTwo.setAlignment(Pos.CENTER_LEFT);
                        vBoxClientTwo.getChildren().add(user);
                        vBoxClientTwo.getChildren().add(lblClient1);
                    }


                    if (byteObject==0){
                        break;
                    }

                }

                objectInputStream.close();
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessageOnAction(ActionEvent actionEvent) {

    }

    public void sendImageOnAction(ActionEvent actionEvent) {

    }

    public void showEmojiOnClick(MouseEvent mouseEvent) {
    }
}