package com.epam.jap.ip_address;

import java.net.InetAddress;
import java.net.UnknownHostException;

class InetAddressExample {

    public static void main(String[] args) {
        InetAddressExample inetAddressExample = new InetAddressExample();

//        inetAddressExample.printLocalhostAddress();
        inetAddressExample.printWPPLIpAddress();
    }

    void printLocalhostAddress() {
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    void printWPPLIpAddress() {
        try {
            System.out.println(InetAddress.getByName("wp.pl"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
