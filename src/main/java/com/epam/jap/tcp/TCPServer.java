package com.epam.jap.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer {

    public static void main(String[] args) {
        new TCPServer().createTCPServer(9000);
    }

    void createTCPServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("I'm waiting for some client");

                if (clientSocket.isConnected()) {
                    System.out.println("Someone connected to the server!\nNow I go away...");
                    clientSocket.close();
                    break;
                }
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
