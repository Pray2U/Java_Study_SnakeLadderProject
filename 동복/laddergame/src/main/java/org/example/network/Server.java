package org.example.network;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread implements MessageSendable{
    private Sender sender;
    private Receiver receiver;
    private JTextArea console;

    @Override
    public void run() {

        try {
            ServerSocket serverSocket = null;
            Socket socket = null;
            serverSocket = new ServerSocket(7777);
            console.append("서버가 준비되었습니다.\n");

            socket = serverSocket.accept();

            sender = new Sender(socket, console);
            receiver = new Receiver(socket, console);

            receiver.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setConsole(JTextArea jTextArea){
        console = jTextArea;
    }

    public void send(String str) {
        sender.send(str);
    }
}
