package com.epam.jap.tcp;

import java.io.IOException;
import java.net.Socket;

class TCPClient {

    public static void main(String[] args) {
        new TCPClient().connectToServer("localhost", 9000);
    }

    void connectToServer(String server, int port) {
        try {
            Socket localhost = new Socket(server, port);

            System.out.println("You have successfully connected to the localhost server!");
            localhost.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
