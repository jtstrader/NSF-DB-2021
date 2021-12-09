package com.nsfdb.gui.dashboard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayPanel extends JPanel {
    ArrayList<JPanel> panels;

    public DisplayPanel() {
        super();
        this.panels = panels;
        this.setLayout(new OverlayLayout(this));
        Dimension windowSize = new Dimension(600, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

    }
}
