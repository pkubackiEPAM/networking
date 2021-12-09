package com.epam.jap.url_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class URLConnectionExample {

    public static void main(String[] args) {
        URLConnectionExample connectionExample = new URLConnectionExample();
//        connectionExample.getURL("http://example.com:80/docs/books/tutorial/index.html?name=networking#DOWNLOADING");
//        connectionExample.getURLConnection("https://www.google.com");
//        connectionExample.createURLToLocalFiles("file:/c:/Users/Przemyslaw_Kubacki/Downloads/file.txt");
    }

    void getURL(String address) {
        try {
            URL url = new URL(address);

            System.out.println("protocol = " + url.getProtocol());
            System.out.println("authority = " + url.getAuthority());
            System.out.println("host = " + url.getHost());
            System.out.println("port = " + url.getPort());
            System.out.println("path = " + url.getPath());
            System.out.println("query = " + url.getQuery());
            System.out.println("filename = " + url.getFile());
        } catch (MalformedURLException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    void getURLConnection(String address) {
        try {
            URL oracle = new URL(address);
            URLConnection urlConnection = oracle.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
            }

            bufferedReader.close();
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    void createURLToLocalFiles(String fileAddress) {
        try {
            URL url = new URL(fileAddress);

            URLConnection urlConnection = url.openConnection();
            InputStream input = urlConnection.getInputStream();

            int data = input.read();
            while(data != -1){
                System.out.print((char) data);
                data = input.read();
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
