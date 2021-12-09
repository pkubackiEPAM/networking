package com.epam.jap.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

class GroupChat {
    private static final String TERMINATE = "Exit";
    static String name;
    static volatile boolean finished = false;

    /**
     * In UDP’s terms, data transferred is encapsulated in a unit called datagram. A datagram is an independent,
     * self-contained message sent over the network whose arrival, arrival time, and content are not guaranteed.
     *
     */
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("239.0.0.0");
            int port = 8989;
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your name: ");
            name = sc.nextLine();
            MulticastSocket socket = new MulticastSocket(port);

            // Since we are deploying
            socket.setTimeToLive(0);
            //this on localhost only (For a subnet set it as 1)

            socket.joinGroup(group);
            Thread chatThread = new Thread(new ReadThread(socket, group, port));

            // Spawn a thread for reading messages
            chatThread.start();

            // sent to the current group
            System.out.println("Start typing messages...\n");
            while (true) {
                String message;
                message = sc.nextLine();
                if (message.equalsIgnoreCase(GroupChat.TERMINATE)) {
                    finished = true;
                    socket.leaveGroup(group);
                    socket.close();
                    break;
                }
                message = name + ": " + message;
                byte[] buffer = message.getBytes();
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
                socket.send(datagram);
            }
        } catch (SocketException se) {
            System.out.println("Error creating socket");
            se.printStackTrace();
        } catch (IOException ie) {
            System.out.println("Error reading/writing from/to socket");
            ie.printStackTrace();
        }
//        }
    }
}
