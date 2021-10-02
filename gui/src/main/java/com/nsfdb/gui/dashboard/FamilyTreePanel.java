package com.nsfdb.gui.dashboard;

import javax.swing.*;
import java.awt.*;

public class FamilyTreePanel extends JPanel {
    public FamilyTreePanel() {
        Dimension windowSize = new Dimension(300, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);
        this.setBackground(Color.GRAY);

        JLabel treeLabel = new JLabel("Tree Place Holder Panel");
        add(treeLabel);
    }
}
