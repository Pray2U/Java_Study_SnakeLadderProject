package org.example.view.frame;

import org.example.view.component.CustomButton;
import org.example.view.component.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FindRoomFrame extends JFrame {

    CustomTextField textField = new CustomTextField(15);
    ActionListener enterRoomAction = e -> {
        String serverIp = textField.getText();
        new GameFrame(serverIp);
        dispose();
    };

    public FindRoomFrame(){
        initializeFrame();
        setButton("방 입장하기", 150, 170, enterRoomAction);
        setInputField();
        getContentPane().setBackground(Color.BLACK);
    }

    private void initializeFrame() {
        setTitle("방 입장하기");
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


    private void setButton(String text, int x, int y, ActionListener e) {
        CustomButton enterButton = new CustomButton(text);
        enterButton.addActionListener(e);
        enterButton.setBounds(x, y, 100, 50);
        enterButton.setFont(new Font("Default", Font.BOLD, 15));
        getContentPane().add(enterButton);
    }

    private void setInputField() {
        JLabel ipLabel = new JLabel("접속 IP 주소");

        setText(ipLabel);

        ipLabel.setBounds(100, 60, 100, 30);
        textField.setBounds(125,100, 150, 30);
        textField.setForeground(Color.WHITE);

        getContentPane().add(ipLabel, BorderLayout.CENTER);
        getContentPane().add(textField, BorderLayout.CENTER);
    }

    private void setText(JLabel label) {
        label.setFont(new Font("Default", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
    }


}
