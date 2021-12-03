package com.nsfdb.gui.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NavigationPanel extends JPanel {
    ArrayList<JPanel> panelList;
    public NavigationPanel(ArrayList<JPanel> panelList) {
        this.panelList = panelList;
        Dimension windowSize = new Dimension(100, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

        setLayout(new GridLayout(10,1,10,5));

        Button treeBut = new Button("Family Tree");
        treeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelList.get(0).setVisible(true);
            }
        });
        this.add(treeBut);

        Button tablesBut = new Button("Table Views");
        tablesBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelList.get(0).setVisible(false);
            }
        });
        this.add(tablesBut);

        Button graphsBut = new Button("Graph Views");
        graphsBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(graphsBut);

    }

    public void addPanel(JPanel newPanel) {
        panelList.add(newPanel);
    }
}
