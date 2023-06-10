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

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Client3FormController {
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
    String message="";
    Byte byteObject;
    Object receivedObject;
    ObjectInputStream objectInputStream;
    InputStream inputStream;
    ImageView view;


    public void initialize() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 3000);
                inputStream = socket.getInputStream();

                objectInputStream = new ObjectInputStream(inputStream);
                Image userImage = new Image("assets/img/icons8-user-100.png");
                ImageView user = new ImageView(userImage);
                while (true) {


                    receivedObject = objectInputStream.readObject();

                    if (receivedObject instanceof ImageView) {
                        byteObject = (Byte) receivedObject;

                        byte[] imageData = new byte[]{byteObject};
                        Image image = new Image(new ByteArrayInputStream(imageData));
                        view = new ImageView(image);

                        view.fitHeightProperty();
                        HBox newHBox = new HBox();
                        newHBox.setAlignment(Pos.CENTER_LEFT);
                        newHBox.getChildren().add(user);
                        newHBox.getChildren().add(view);

                        vBoxClientTwo.getChildren().add(newHBox);
                    } else {
                        message = objectInputStream.readUTF();
                        lblIncomingMessage.setText("Client : " + message);
                        HBox newHBox = new HBox();
                        newHBox.getChildren().clear();
                        newHBox.setAlignment(Pos.CENTER_LEFT);
                        newHBox.getChildren().add(user);
                        newHBox.getChildren().add(lblClient1);
                        vBoxClientTwo.getChildren().add(newHBox);

                    }


                    if (byteObject == 0) {
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
        message="";
        message = txtTextTyper.getText();
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendImageOnAction(ActionEvent actionEvent) {

        try {
            JFileChooser fileChooser = new JFileChooser();

            int isFileChoose = fileChooser.showOpenDialog(null);

            if (isFileChoose == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                /*BufferedImage bufferedImage = ImageIO.read(selectedFile);*/

                FileInputStream fileInputStream=new FileInputStream(selectedFile);

                byte[] imageData =new byte[(int) selectedFile.length()];

                fileInputStream.read(imageData);

                dataOutputStream.writeInt(imageData.length);
                dataOutputStream.write(imageData);
                dataOutputStream.flush();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEmojiOnClick(MouseEvent mouseEvent) {
        try {
            Robot robot = new Robot();
            int sleep = 100;
            robot.keyPress(524);
            Thread.sleep(sleep);
            robot.keyPress(46);
            Thread.sleep(sleep);
            robot.keyRelease(46);
            Thread.sleep(sleep);
            robot.keyRelease(524);

        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
