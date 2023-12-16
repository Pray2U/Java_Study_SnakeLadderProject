package org.example.network;

import org.example.Board;
import org.example.SnakeLadderGame;
import org.example.view.panel.BoardPanel;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class Receiver extends Thread {
    private SnakeLadderGame snakeLadderGame;
    private Socket socket;
    private DataInputStream in;
    private JTextArea console;
    private BoardPanel boardPanel;

    public Receiver(Socket socket, JTextArea console, SnakeLadderGame snakeLadderGame, BoardPanel boardPanel) {
        this.console = console;
        this.socket = socket;
        this.snakeLadderGame = snakeLadderGame;
        this.boardPanel = boardPanel;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (in!=null) {
            try {
                String str = in.readUTF();
                receiveBoardInfo(str);
                receiveRollSign(str);
                if(!str.startsWith("board")) {
                    console.append(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void receiveRollSign(String sign) {
        if (sign.startsWith("roll")) {
            String numStr = sign.substring(5,6);
            int distance = Integer.parseInt(numStr);
            System.out.println(distance);
            snakeLadderGame.startTurn(distance);
            boardPanel.movePlayer(snakeLadderGame.getOpponentPlayer());
            if(snakeLadderGame.getOpponentPlayer().isFinished()) {
                console.append(snakeLadderGame.getOpponentPlayer().getNickname() + "이 승리하였습니다!!!\n");
            }
            boardPanel.repaint();
        }
    }

    public void receiveBoardInfo(String sign) {
        if (sign.startsWith("board")) {
            String[] tokens = sign.split(" ");
            int[] numbers = Arrays.stream(tokens)
                    .skip(1)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Board board = new Board(numbers);
            boardPanel.setLadder(board);
            snakeLadderGame.setBoard(board);
        }
    }
}
