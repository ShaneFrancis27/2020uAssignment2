package sample;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Client extends Frame {
    private Socket socket = null;
    private BufferedReader in = null;
    public PrintWriter networkOut = null;
    public BufferedReader networkIn = null;

    //we can read this from the user too
    public static String SERVER_ADDRESS = "localhost";
    public static int SERVER_PORT = 16789;


    public void SetClient(Socket socket){
        try {
            networkOut = new PrintWriter(socket.getOutputStream(), true);
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOEXception while opening a read/write connection");
        }
    }

    public  void ServerConnect(){

        try {
            SetClient(new Socket(SERVER_ADDRESS, SERVER_PORT));
        } catch (Exception e) {
            System.out.println("Cannot connect to " + SERVER_ADDRESS + ":" + SERVER_PORT);
            System.exit(0);
        }

    }

}