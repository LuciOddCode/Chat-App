package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket clientSocket;

    private ObjectOutputStream outputStream;

    public ClientHandler(Socket socket) {
        clientSocket = socket;
        try {
            outputStream.writeObject(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            while (true) {
                Object receivedObject = objectInputStream.readObject();
                byte byteObject = 0;

                if (receivedObject instanceof ImageView) {
                    byteObject = (Byte) receivedObject;

                    byte[] imageData = new byte[]{byteObject};
                    Image image = new Image(new ByteArrayInputStream(imageData));
                    ImageView view = new ImageView(image);

                    broadcast(view,this);
                }

                if (byteObject==0){
                    String message = objectInputStream.readUTF();
                    broadcast(message,this);
                    break;
                }

            }

            objectInputStream.close();
            clientSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void broadcast(Object o, ClientHandler clientHandler) {
        for (ClientHandler client : ServerFormController.clients){
            client.sendMessage(o);
        }
    }

    public void sendMessage(Object o) {
        try {
            outputStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
