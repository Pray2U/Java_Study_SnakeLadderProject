package org.example.view.Panel;

import org.example.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel{

    Image backgroundImg;
    Board board;
    JLabel player1 = new JLabel();
    JLabel player2 = new JLabel();


    public BoardPanel(String url, Board board) {
        setLayout(null);
        this.board = board;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(url);
        this.backgroundImg = img.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        setPlayer();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //배경이미지 그리기
        g.drawImage(backgroundImg, 0, 0, this);
    }

    public void setLadder(Board board) {
        int[] boardInfo = board.getBoardInfo();
        int ladderNum = 0;
        for(int i = 1; i < boardInfo.length; i++) {
            if(boardInfo[i] != i) {
                ladderNum++;
                int from = i;
                int to = boardInfo[i];

                drawLadder(from, to, "images/hole" + ladderNum + ".png");
            }
        }
    }

    private void drawLadder(int from, int to, String url) {
        JLabel fromImgLabel = new JLabel();
        JLabel toImgLabel = new JLabel();

        ImageIcon icon = new ImageIcon(url);

        Image ladder = icon.getImage();

        Image fromImg = ladder.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image toImg = ladder.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        ImageIcon updateFromIcon = new ImageIcon(fromImg);
        ImageIcon updateToIcon = new ImageIcon(toImg);


        fromImgLabel.setBounds((100 * (from % 10)) + 35, 500 - ((500 / 6) * (from / 10)) + 25, 30, 30);
        toImgLabel.setBounds((100 * (to % 10)) + 35, 500 - ((500 / 6) * (to / 10)) + 35, 20, 20);

        fromImgLabel.setIcon(updateFromIcon);
        toImgLabel.setIcon(updateToIcon);

        add(fromImgLabel);
        add(toImgLabel);
    }

    public void movePlayer(boolean isPlayer1) {
        JLabel player;
        if(isPlayer1) {
            player = this.player1;
        }else {
            player = player2;
        }
    }

    public void setPlayer() {
        ImageIcon icon = new ImageIcon("images/horse1.png");
        ImageIcon icon2 = new ImageIcon("images/horse2.png");

        Image p1 = icon.getImage();
        Image p2 = icon2.getImage();

        Image p1Image = p1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image p2Image = p2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        ImageIcon updateP1Icon = new ImageIcon(p1Image);
        ImageIcon updateP2Icon = new ImageIcon(p2Image);

        player1.setBounds(10, 440, 50, 50);
        player2.setBounds(40, 440, 50, 50);

        player1.setIcon(updateP1Icon);
        player2.setIcon(updateP2Icon);

        add(player1);
        add(player2);
    }
}
