package com.nsfdb.gui.dashboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HealingPanel extends JPanel {
    public HealingPanel() throws IOException {
        super();
        //img.setBackground(Color.WHITE);
        this.setOpaque(false);
        //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
        BufferedImage bImg = ImageIO.read(getClass().getClassLoader().getResource("bone.PNG"));
        Image img = bImg.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(img));
        this.add(iconLabel);
    }
}
