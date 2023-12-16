package org.example.view.panel;

import org.example.view.frame.FindRoomFrame;
import org.example.view.component.CustomButton;
import org.example.view.frame.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    Image backgroundImg;

    public MainPanel() {
        setLayout(null);
        setLogo();
        setButton("방 만들기", 200, 500, popUpCreateRoomFrame());
        setButton("게임 참가하기", 200, 300, popUpFindRoomFrame());
    }

    public void setBackgroundImage(String url) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(url);
        this.backgroundImg = img.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //배경이미지 그리기
        g.drawImage(backgroundImg, 0, 0, this);

    }

    private void setLogo() {
        JLabel imgLabel = new JLabel();

        ImageIcon icon = new ImageIcon("images/snakeLadder.png");

        // ImageIcon 객체에서 Image 추출
        Image mainLogo = icon.getImage();

        // 추출된 Image의 크기 조절하여 새로운 Image 객체 생성
        Image updateImg = mainLogo.getScaledInstance(500, 250, Image.SCALE_SMOOTH);

        // 새로운 Image 객체로 ImageIcon 객체 생성
        ImageIcon updateIcon = new ImageIcon(updateImg);
        imgLabel.setBounds(100, 50, 500, 200);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setIcon(updateIcon);
        add(imgLabel);
    }

    private void setButton(String text, int x, int y, ActionListener e) {
        CustomButton mainButton = new CustomButton(text);
        mainButton.addActionListener(e);
        mainButton.setBounds(x, y, 300, 100);
        mainButton.setFont(new Font("Default", Font.BOLD, 20));
        add(mainButton);
    }

    public ActionListener popUpCreateRoomFrame() {
        return e -> new GameFrame();
    }

    public ActionListener popUpFindRoomFrame() {
        return e -> new FindRoomFrame();
    }
}
