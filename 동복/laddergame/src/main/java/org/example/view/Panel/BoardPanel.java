package org.example.view.Panel;

import org.example.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel{

    Image backgroundImg;
    Board board;

    public BoardPanel(String url, Board board) {
        setLayout(null);
        this.board = board;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(url);
        this.backgroundImg = img.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
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
}
