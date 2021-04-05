package sample;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

public class Server {
    protected Socket clientSocket           = null;
    protected ServerSocket serverSocket     = null;
    protected ArrayList <Thread> threads    = new ArrayList<>();
    protected int numClients                = 0;
    protected Vector messages               = new Vector();

    public static int SERVER_PORT = 9090;
    public static int MAX_CLIENTS = 25;

    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);

            while(true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client #"+(numClients+1)+" connected.");
                Thread clientThreads = new ClientThread(clientSocket, messages);
                threads.add(clientThreads);
                clientThreads.start();
                numClients++;
            }
        } catch (IOException e) {
            System.err.println("IOException while creating server connection");
        }
    }

    public static void main(String[] args) {
        Server app = new Server();
    }
}
