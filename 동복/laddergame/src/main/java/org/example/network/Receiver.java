package org.example.network;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
    Socket socket;
    DataInputStream in;
    JTextArea console;

    public Receiver(Socket socket, JTextArea console) {
        this.console = console;
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (in!=null) {
            try {
                console.append(in.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
