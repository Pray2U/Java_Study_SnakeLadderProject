package org.example.view.frame;

import org.example.view.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int MAIN_WIDTH = 700;
    private static final int MAIN_HEIGHT = 700;

    public MainFrame() {
        setTitle("뱀 사다리 게임");
        setSize(MAIN_WIDTH, MAIN_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setMainPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 프레임 보이기 지정
        setVisible(true);
    }

    private void setMainPanel() {
        MainPanel mainPanel = new MainPanel();
        mainPanel.setSize(MAIN_WIDTH, MAIN_HEIGHT);
        mainPanel.setBackgroundImage("images/mainBackground.jpg");
        add(mainPanel, BorderLayout.CENTER);
    }
}
