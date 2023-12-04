package org.example.network;

import javax.swing.*;
import java.net.Socket;


public class Client {
    public Client(JTextArea console) {
        this.console = console;
    }

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private JTextArea console;
    public void connect(String serverIp) {
        try {
            socket = new Socket(serverIp, 7777);
            System.out.println("서버에 연결되었습니다.");
            sender = new Sender(socket, console);
            receiver = new Receiver(socket, console);

            receiver.start();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String str) {
        sender.send(str);
    }

    public String getLocalAddress() {
        return socket.getLocalAddress() + ":" + socket.getLocalPort();
    }
}
