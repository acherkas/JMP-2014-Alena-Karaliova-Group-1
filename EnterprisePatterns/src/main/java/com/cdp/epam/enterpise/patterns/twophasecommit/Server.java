package com.cdp.epam.enterpise.patterns.twophasecommit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.cdp.epam.enterpise.patterns.twophasecommit.data.ClientThread;
import com.cdp.epam.enterpise.patterns.twophasecommit.data.ServerData;

public class Server {

    public ServerData data = new ServerData(false, false);

	Server() {
        data.t = new ArrayList<>();
        data.data = new ArrayList<>();
    }

    public static void main(String args[]) {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        int port_number = 2222;
        Server ser = new Server();
        try {
            serverSocket = new ServerSocket(port_number);
        } catch (IOException e) {
            System.out.println(e);
        }
        while (!ser.data.closed) {
            try {
                clientSocket = serverSocket.accept();
                ClientThread th = new ClientThread(ser, clientSocket);
                (ser.data.t).add(th);
                System.out.println("\nNow Total clients are : " + (ser.data.t).size());
                (ser.data.data).add("NOT_SENT");
                th.start();
            } catch (IOException e) {
            }
        }
        try {
            serverSocket.close();
        } catch (Exception e1) {
        }
    }
}


