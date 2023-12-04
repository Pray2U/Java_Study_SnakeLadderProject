package org.example.view.Panel;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel{

    Image backgroundImg;

    public BoardPanel(String url) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(url);
        this.backgroundImg = img.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
    }


    @Override
    protected void paintComponent(Graphics g) {
        //배경이미지 그리기
        g.drawImage(backgroundImg, 0, 0, this);
    }
}
