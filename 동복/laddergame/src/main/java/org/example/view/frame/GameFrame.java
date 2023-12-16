package org.example.view.frame;

import org.example.IPlayer;
import org.example.Player;
import org.example.SnakeLadderGame;
import org.example.model.Dice;
import org.example.network.Client;
import org.example.network.MessageSendable;
import org.example.network.Server;
import org.example.view.panel.BoardPanel;
import org.example.view.component.CustomButton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    private JTextArea console = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(console);
    private CustomButton rollButton;
    private JLabel dicePanel = new JLabel();
    private SnakeLadderGame snakeLadderGame = new SnakeLadderGame();
    private BoardPanel boardPanel = new BoardPanel("images/board.png", snakeLadderGame.getBoard());
    private MessageSendable sender;
    Document doc = console.getDocument();


    private ActionListener diceAction = e -> {
        if(snakeLadderGame.isCurrentPlayerTurn()){
            rollDice();
        }else {
            console.append("아직 차례가 아닙니다. 차례가 돌아올 때까지 기다려주세요.\n");
        }
    };

    {
        addDocumentListener();
        initializeFrame();
        getContentPane().setBackground(Color.BLACK);
        setBoardPanel();
        setConsole();
        setDicePanel(Dice.ONE);
        setButton("Roll!", 850, 550, diceAction);
    }

    public GameFrame(String serverIp){
        Client client = new Client(console, snakeLadderGame, boardPanel);
        sender = client;
        client.connect(serverIp);
        client.send(client.getLocalAddress() + "님이 접속 했습니다. \n");
        console.append("당신은 Player2 입니다.\n");
        client.send("게임을 시작합니다.\n");
        snakeLadderGame.setCurrentPlayer(new Player("Player2", false));
        snakeLadderGame.setOpponentPlayer(new Player("Player1", true));
        snakeLadderGame.startGame();
        sender.send("Player1의 턴입니다.\n");
    }

    public GameFrame(){
        Server server = new Server(console, snakeLadderGame, boardPanel);
        sender = server;
        server.start();
        console.append("당신은 Player1 입니다.\n");
        console.append("다른 유저가 접속하면 게임을 시작 합니다.\n");
        snakeLadderGame.setCurrentPlayer(new Player("Player1", true));
        snakeLadderGame.setOpponentPlayer(new Player("Player2", false));
        snakeLadderGame.startGame();
        boardPanel.setLadder(snakeLadderGame.getBoard());
    }

    private void initializeFrame() {
        setTitle("뱀 사다리 게임");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void setButton(String text, int x, int y, ActionListener e) {
        rollButton = new CustomButton(text);
        rollButton.addActionListener(e);
        rollButton.setBounds(x, y, 100, 50);
        rollButton.setFont(new Font("Default", Font.BOLD, 15));
        getContentPane().add(rollButton);
    }

    private void setBoardPanel() {
        boardPanel.setSize(1000, 500);
        getContentPane().add(boardPanel);
    }

    private void setConsole() {
        scrollPane.setBounds(0, 500, 600, 160);
        console.setEditable(false);
        getContentPane().add(scrollPane);
    }

    private void setDicePanel(Dice number) {

        ImageIcon icon = new ImageIcon(number.getUrl());

        // ImageIcon 객체에서 Image 추출
        Image dice = icon.getImage();

        // 추출된 Image의 크기 조절하여 새로운 Image 객체 생성
        Image updateImg = dice.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        // 새로운 Image 객체로 ImageIcon 객체 생성
        ImageIcon updateIcon = new ImageIcon(updateImg);
        dicePanel.setBounds(650, 525, 100, 100);
        dicePanel.setHorizontalAlignment(SwingConstants.CENTER);
        dicePanel.setIcon(updateIcon);
        add(dicePanel);
    }

    private void rollDice() {
        Dice dice = Dice.roll();
        snakeLadderGame.startTurn(dice.getNumber());
        setDicePanel(dice);

        IPlayer currentPlayer = snakeLadderGame.getCurrentPlayer();
        boardPanel.movePlayer(currentPlayer);
        boardPanel.repaint();

        IPlayer opponentPlayer = snakeLadderGame.getOpponentPlayer();
        sender.send("roll:" + dice.getNumber() + "\n");
        sender.send(opponentPlayer.getNickname() + "의 턴입니다.\n");
        if (currentPlayer.isFinished()) {
           console.append(currentPlayer.getNickname() + "이 승리하였습니다!!\n");
        }
    }

    private void addDocumentListener() {
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            }
        });
    }

}
