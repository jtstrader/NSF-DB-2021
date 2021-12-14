package com.nsfdb.gui.dashboard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayPanel extends JPanel {
    ArrayList<JPanel> panels;

    public DisplayPanel() {
        super();
        // Container panel to hold the panels to show based on the buttons pressed on the navigation
        // Ex. ClosurePanel, FamilyTreePanel, HealingPanel, LifeTablePanel, MonkeyTablePanel
        this.panels = panels;
        this.setLayout(new OverlayLayout(this));
        Dimension windowSize = new Dimension(600, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

    }
}
