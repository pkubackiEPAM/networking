package com.epam.jap.tcp_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ChatServer {
    public static int port = 13000;
    public static BufferedReader con_br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server: Waiting for client to connect");
        Socket socket = serverSocket.accept();
        System.out.println("server: Connection established");

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        Thread chat_server_writer = new ChatWriter("simple_man", writer, con_br);
        chat_server_writer.start();

        String s;
        while ((s = reader.readLine()) != null) {
            System.out.println("\rclient: " + s);
            System.out.print("> ");
        }

        System.out.println("\rserver: Client has disconnected");
        socket.close();
        serverSocket.close();
    }
}
