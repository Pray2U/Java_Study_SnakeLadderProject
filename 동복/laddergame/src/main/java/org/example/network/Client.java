package org.example.network;

import org.example.SnakeLadderGame;
import org.example.view.panel.BoardPanel;

import javax.swing.*;
import java.net.Socket;


public class Client implements MessageSendable{
    public Client(JTextArea console, SnakeLadderGame snakeLadderGame, BoardPanel boardPanel) {
        this.console = console;
        this.snakeLadderGame = snakeLadderGame;
        this.boardPanel = boardPanel;
    }

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private JTextArea console;
    private SnakeLadderGame snakeLadderGame;
    private BoardPanel boardPanel;

    public void connect(String serverIp) {
        try {
            socket = new Socket(serverIp, 7777);
            System.out.println("서버에 연결되었습니다.");
            sender = new Sender(socket, console);
            receiver = new Receiver(socket, console, snakeLadderGame, boardPanel);

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
