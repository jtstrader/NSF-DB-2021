package com.nsfdb.gui.dashboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class NavigationPanel extends JPanel {
    ArrayList<JPanel> panelList;
    public NavigationPanel(ArrayList<JPanel> panelList) throws IOException {
        this.panelList = panelList;
        Dimension windowSize = new Dimension(100, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);
        this.setBackground(Color.WHITE);
        this.setOpaque(true);

        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(6, 1, 10, 0));
        pan.setPreferredSize(new Dimension(100, 300));
        pan.setBackground(Color.WHITE);
        pan.setOpaque(true);
        JPanel img = new JPanel();
        img.setBackground(Color.WHITE);
        img.setOpaque(true);

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
        Image image = icon.getImage();
        image = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JLabel iconLabel = new JLabel(icon);
        img.add(iconLabel);

        JButton treeBut = new JButton("Family Tree");
        treeBut.setBorderPainted(false);
        treeBut.setFocusPainted(false);
        treeBut.setFont(new Font("Arial", Font.PLAIN, 12));
        treeBut.setBackground(Color.WHITE);
        treeBut.setForeground(Color.BLACK);
        treeBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) treeBut.getModel();
                if(model.isPressed()) {
                    panelList.get(0).setVisible(true);
                }
                else if(model.isRollover()) {
                    treeBut.setBackground(new Color(253,238,229));
                    treeBut.setForeground(new Color(214, 79, 1));
                }
                else {
                    treeBut.setBackground(Color.WHITE);
                    treeBut.setForeground(Color.BLACK);
                }
            }
        });
        pan.add(treeBut);

        JButton tablesBut = new JButton("Table Views");
        tablesBut.setBorderPainted(false);
        tablesBut.setFocusPainted(false);
        tablesBut.setFont(new Font("Arial", Font.PLAIN, 12));
        tablesBut.setBackground(Color.WHITE);
        tablesBut.setForeground(Color.BLACK);
        tablesBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) tablesBut.getModel();
                if(model.isPressed()) {
                    panelList.get(0).setVisible(false);
                }
                else if(model.isRollover()) {
                    tablesBut.setBackground(new Color(253, 238, 229));
                    tablesBut.setForeground(new Color(214, 79, 1));
                }
                else {
                    tablesBut.setBackground(Color.WHITE);
                    tablesBut.setForeground(Color.BLACK);
                }
            }
        });
        pan.add(tablesBut);

        JButton graphsBut = new JButton("Graph Views");
        graphsBut.setBorderPainted(false);
        graphsBut.setFocusPainted(false);
        graphsBut.setFont(new Font("Arial", Font.PLAIN, 12));
        graphsBut.setBackground(Color.WHITE);
        graphsBut.setForeground(Color.BLACK);
        graphsBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) graphsBut.getModel();
                if(model.isPressed()) {
                    panelList.get(0).setVisible(false);
                }
                else if(model.isRollover()) {
                    graphsBut.setBackground(new Color(253,238,229));
                    graphsBut.setForeground(new Color(214, 79, 1));
                }
                else {
                    graphsBut.setBackground(Color.WHITE);
                    graphsBut.setForeground(Color.BLACK);
                }
            }
        });
        pan.add(graphsBut);

        this.add(img, BorderLayout.NORTH);
        this.add(pan, BorderLayout.SOUTH);
    }

    public void addPanel(JPanel newPanel) {
        panelList.add(newPanel);
    }
}
