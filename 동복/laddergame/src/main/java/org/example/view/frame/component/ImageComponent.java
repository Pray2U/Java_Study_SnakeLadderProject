package org.example.view.frame.component;

import javax.swing.*;
import java.awt.*;

public class ImageComponent extends JPanel {
    private Image snakeLadder;

    public ImageComponent(String fileDir) {
        this.snakeLadder = new ImageIcon(fileDir).getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(snakeLadder, 100, 100, Color.BLACK, this);
    }
}
