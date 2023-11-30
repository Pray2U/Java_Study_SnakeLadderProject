package org.example.network;

import java.net.Socket;

public class Client {
    public void connect(String serverIp) {
        try {
            Socket socket = new Socket(serverIp, 7777);
            System.out.println("서버에 연결되었습니다.");
            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
