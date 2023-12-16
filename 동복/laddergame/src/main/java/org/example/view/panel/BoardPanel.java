package org.example.view.panel;

import org.example.Board;
import org.example.IPlayer;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel{

    private Image backgroundImg;
    private Board board;
    private JLabel player1 = new JLabel();
    private JLabel player2 = new JLabel();


    public BoardPanel(String url, Board board) {
        setLayout(null);
        this.board = board;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(url);
        this.backgroundImg = img.getScaledInstance(1000, 500, Image.SCALE_DEFAULT);
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

        fromImgLabel.setBounds((100 * (from % 10)) + 35, 440 - ((500 / 6) * (from / 10)), 30, 30);
        toImgLabel.setBounds((100 * (to % 10)) + 35, 440 - ((500 / 6) * (to / 10)), 20, 20);

        fromImgLabel.setIcon(updateFromIcon);
        toImgLabel.setIcon(updateToIcon);

        add(fromImgLabel);
        add(toImgLabel);
    }

    public void movePlayer(IPlayer player) {

        int position = player.getPosition();

        JLabel playerLabel;
        if(player.getNickname().equals("Player1")) {
            playerLabel = player1;
            playerLabel.setLocation( (100 * (position % 10)) + 10, 440 - ((500 / 6) * (position / 10)));
        }else {
            playerLabel = player2;
            playerLabel.setLocation((100 * (position % 10)) + 40, 440 - ((500 / 6) * (position / 10)));
        }
    }

    public void setPlayer() {
        ImageIcon icon = getHorseImageIcon("images/horse2.png");
        ImageIcon icon2 = getHorseImageIcon("images/horse1.png");

        player1.setBounds(10, 440, 50, 50);
        player2.setBounds(40, 440, 50, 50);

        player1.setIcon(icon);
        player2.setIcon(icon2);

        add(player1);
        add(player2);
    }

    public ImageIcon getHorseImageIcon(String imgUrl) {
        ImageIcon icon = new ImageIcon(imgUrl);
        Image i = icon.getImage();
        Image image = i.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
