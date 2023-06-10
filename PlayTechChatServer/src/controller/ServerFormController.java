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

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

    public Label newMessage=new Label("");
    public String sender;
    public AnchorPane paneServer;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    public static List <ClientHandler> clients;


    public void initialize(){
        clients=new ArrayList<>();

            try {

                /*serverSocket = new ServerSocket(3000);
                socket = serverSocket.accept();*/

                start(3000);

               /* InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);*/
              /* while (true){
                   Object receivedObject = objectInputStream.readObject();
                   byte byteObject = 0;

                   if (receivedObject instanceof ImageView) {
                       byteObject = (Byte) receivedObject;

                       byte[] imageData = new byte[]{byteObject};
                       Image image = new Image(new ByteArrayInputStream(imageData));
                       ImageView view = new ImageView(image);

                       HBox newHBox = new HBox();
                       view.setFitHeight(300);
                       view.setFitWidth(600);
                       newHBox.setAlignment(Pos.CENTER_LEFT);

                       newHBox.getChildren().add(view);

                       vBoxClientTwo.getChildren().add(newHBox);
                   }

                   if (byteObject==0){
                       String message = objectInputStream.readUTF();
                       lblIncomingMessage=new Label("");
                       lblIncomingMessage.setText("Client : " + message);
                       HBox newHBox = new HBox();
                       newHBox.getChildren().clear();
                       newHBox.setAlignment(Pos.CENTER_LEFT);

                       newHBox.getChildren().add(lblClient1);
                       vBoxClientTwo.getChildren().add(newHBox);
                       break;
                   }

               }
*/
                /*objectInputStream.close();
                socket.close();
                serverSocket.close();*/

            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void sendMessageOnAction(ActionEvent actionEvent) {

        sendAMessage(txtTextTyper.getText());
    }

    private void sendAMessage(String message){


        newMessage.setText(message);
        hBoxClientTwo=new HBox();
        hBoxClientTwo.setAlignment(Pos.CENTER_RIGHT);
        hBoxClientTwo.getChildren().add(newMessage);
        vBoxClientTwo.getChildren().add(hBoxClientTwo);

    }

    private void receivedAMessage(String message){

        try {

            newMessage.setText(message);
           /* .write(message);*/
            dataOutputStream.flush();
            txtTextTyper.clear();
            hBoxClientTwo=new HBox();
            hBoxClientTwo.setAlignment(Pos.CENTER_LEFT);
            hBoxClientTwo.getChildren().add(newMessage);
            vBoxClientTwo.getChildren().add(hBoxClientTwo);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    void start(int port){
        try {
            ServerSocket newServerSocket = new ServerSocket(port);
            while (true){
                Socket clientSocket =newServerSocket.accept();
                ClientHandler clientHandler=new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
