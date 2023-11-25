package org.example.view.frame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("뱀 사다리 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(700, 700);
        setLayout(null);
        JLabel imgLabel = new JLabel();

        ImageIcon icon = new ImageIcon("images/snakeLadder.png");

        // ImageIcon 객체에서 Image 추출
        Image img = icon.getImage();

        // 추출된 Image의 크기 조절하여 새로운 Image 객체 생성
        Image updateImg = img.getScaledInstance(500, 200, Image.SCALE_SMOOTH);

        // 새로운 Image 객체로 ImageIcon 객체 생성
        ImageIcon updateIcon = new ImageIcon(updateImg);
        imgLabel.setBounds(100, 0, 500, 200);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setIcon(updateIcon);
        getContentPane().add(imgLabel);

        JButton btnNewButton = new JButton("방 만들기");
        btnNewButton.setBounds(200, 500, 300, 100);
        add(btnNewButton);

        JButton btnNewButton2 = new JButton("게임 참가하기");
        btnNewButton2.setBounds(200, 300, 300, 100);
        add(btnNewButton2);

        // 프레임 보이기 지정
        setVisible(true);
    }
}
