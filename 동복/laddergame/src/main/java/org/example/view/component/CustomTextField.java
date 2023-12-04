package org.example.view.component;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {

    public CustomTextField(int cols) { super(cols); decorate(); }
    protected void decorate() { setOpaque(false); }
    @Override
    protected void paintComponent(Graphics g) {
        Color o= Color.BLACK; //글자색 결정
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.fillRoundRect(0, 0, width, height, 30, 30);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
        int textX = (width - stringBounds.width) / 2;
        int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
        graphics.setColor(o);
        graphics.setFont(getFont());
        graphics.drawString(getText(), textX, textY);
        graphics.dispose();
        super.paintComponent(g);
    }
}
