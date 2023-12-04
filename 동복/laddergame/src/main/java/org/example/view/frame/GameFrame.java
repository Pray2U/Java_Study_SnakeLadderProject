package org.example.view.frame;

import org.example.model.Dice;
import org.example.network.Client;
import org.example.network.Server;
import org.example.view.Panel.BoardPanel;
import org.example.view.component.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    JTextArea console = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(console);
    BoardPanel boardPanel = new BoardPanel("images/board.png");
    CustomButton rollButton;
    JLabel dicePanel;

    public GameFrame(String serverIp){
        initializeFrame();
        getContentPane().setBackground(Color.BLACK);
        setBoardPanel();
        setConsole();
        setDicePanel(Dice.ONE);
        setButton("Roll!", 850, 550, null);
        System.out.println("접속 시도 IP : " + serverIp);
        Client client = new Client(console);
        client.connect(serverIp);
        client.send(client.getLocalAddress() + "님이 접속 했습니다. \n");
        console.append("당신은 Player2 입니다.\n");
        client.send("게임을 시작합니다.");
    }

    public GameFrame(){
        Server server = new Server();
        server.setConsole(console);
        server.start();
        initializeFrame();
        getContentPane().setBackground(Color.BLACK);
        setBoardPanel();
        setConsole();
        setDicePanel(Dice.ONE);
        setButton("Roll!", 850, 550, null);
        console.append("당신은 Player1 입니다.\n");
        console.append("다른 유저가 접속하면 게임을 시작 합니다.\n");
    }

    private void initializeFrame() {
        setTitle("뱀 사다리 게임");
        setSize(1015, 700);
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
        dicePanel = new JLabel();

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

    private String readConsole() {
        String text = console.getText();
        String[] lines = text.split("\n");
        return lines[lines.length - 1];
    }
}
