package org.example.network;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender{
    private Socket socket;
    private DataOutputStream out;
    private JTextArea console;

    public Sender(Socket socket, JTextArea console) {
        this.console = console;
        this.socket = socket;

        try {
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String str) {
        try {
            if(!str.startsWith("board")) {
                console.append(str);
            }
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

