package com.cdp.epam.enterpise.patterns.twophasecommit.data;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import com.cdp.epam.enterpise.patterns.twophasecommit.Server;

public class ClientThread extends Thread {
    ClientThreadData data = new ClientThreadData(null, null, null);

	public ClientThread(Server ser, Socket clientSocket) {
        this.data.clientSocket = clientSocket;
        this.data.ser = ser;
    }

    @SuppressWarnings("deprecation")
	public void run() {
        try {
            data.is = new DataInputStream(data.clientSocket.getInputStream());
            data.os = new PrintStream(data.clientSocket.getOutputStream());
            data.os.println("Enter your name.");
            data.name = data.is.readLine();
            data.clientIdentity = data.name;
            data.os.println("Welcome " + data.name + " to this 2 Phase Application.\nYou will receive a vote Request now...");
            data.os.println("VOTE_REQUEST\nPlease enter COMMIT or ABORT to proceed : ");
            for (int i = 0; i < (data.ser.data.t).size(); i++) {
                if ((data.ser.data.t).get(i) != this) {
                    ((data.ser.data.t).get(i)).data.os.println("---A new user " + data.name + " entered the  Appilcation---");
                }
            }
            while (true) {
                data.line = data.is.readLine();
                if (data.line.equalsIgnoreCase("abort")) {
                    System.out.println("\nFrom '" + data.clientIdentity + "' : ABORT\n\nSince aborted we will not wait for inputs from other clients.");
                    System.out.println("\nAborted....");
                    for (int i = 0; i < (data.ser.data.t).size(); i++) {
                        ((data.ser.data.t).get(i)).data.os.println("GLOBAL_ABORT");
                        ((data.ser.data.t).get(i)).data.os.close();
                        ((data.ser.data.t).get(i)).data.is.close();
                    }
                    break;
                }
                if (data.line.equalsIgnoreCase("commit")) {
                    System.out.println("\nFrom '" + data.clientIdentity + "' : commit");
                    if ((data.ser.data.t).contains(this)) {
                        (data.ser.data.data).set((data.ser.data.t).indexOf(this), "commit");
                        for (int j = 0; j < (data.ser.data.data).size(); j++) {
                            if (!(((data.ser.data.data).get(j)).equalsIgnoreCase("NOT_SENT"))) {
                                data.ser.data.inputFromAll = true;
                                continue;
                            } else {
                                data.ser.data.inputFromAll = false;
                                System.out.println("\nWaiting for inputs from other clients.");
                                break;
                            }
                        }
                        if (data.ser.data.inputFromAll) {
                            System.out.println("\n\nCommited....");
                            for (int i = 0; i < (data.ser.data.t).size(); i++) {
                                ((data.ser.data.t).get(i)).data.os.println("global commit");
                                ((data.ser.data.t).get(i)).data.os.close();
                                ((data.ser.data.t).get(i)).data.is.close();
                            }
                            break;
                        }
                    } // if t.contains
                } // commit
            } // while
            data.ser.data.closed = true;
            data.clientSocket.close();
        } catch (IOException e) {}
    }
}