package org.example.network;

import org.example.Board;
import org.example.SnakeLadderGame;
import org.example.view.panel.BoardPanel;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread implements MessageSendable{
    private Sender sender;
    private Receiver receiver;
    private JTextArea console;
    private SnakeLadderGame snakeLadderGame;
    private BoardPanel boardPanel;

    public Server(JTextArea console, SnakeLadderGame snakeLadderGame, BoardPanel boardPanel) {
        this.console = console;
        this.snakeLadderGame = snakeLadderGame;
        this.boardPanel = boardPanel;
    }

    @Override
    public void run() {

        try {
            ServerSocket serverSocket = null;
            Socket socket = null;
            serverSocket = new ServerSocket(7777);
            console.append("서버가 준비되었습니다.\n");
            socket = serverSocket.accept();

            sender = new Sender(socket, console);
            receiver = new Receiver(socket, console, snakeLadderGame, boardPanel);
            sendBoardInfo();
            receiver.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String str) {
        sender.send(str);
    }

    public void sendBoardInfo() {
        StringBuilder str = new StringBuilder();
        str.append("board ");

        Board board = snakeLadderGame.getBoard();
        int[] boardInfo = board.getBoardInfo();
        for (int j : boardInfo) {
            str.append(j)
                    .append(" ");
        }

        send(str.toString());
    }

}
