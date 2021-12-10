package com.nsfdb.gui.dashboard;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class NavigationPanel extends JPanel {
    ArrayList<JPanel> panelList;
    int PANEL_WIDTH = 120;
    public NavigationPanel(ArrayList<JPanel> panelList) throws IOException {
        this.panelList = panelList;
        //this.setLayout(null);
        // Top level panel is 1 pixel less in width to hide white line.
        Dimension windowSize = new Dimension(PANEL_WIDTH - 1, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);
        this.setBackground(Color.WHITE);
        this.setOpaque(true);

        // Creates a Panel for the top level buttons
        JPanel butPanel = new JPanel();
        butPanel.setLayout(new GridLayout(6, 1, 0, 0));
        butPanel.setPreferredSize(new Dimension(PANEL_WIDTH, 300));
        //butPanel.setBackground(Color.WHITE);
        butPanel.setOpaque(false);
        // A panel to display the NSF Logo in the top left
        JPanel img = new JPanel();
        //img.setBackground(Color.WHITE);
        img.setOpaque(false);

        // Creates a panel to contain the top level buttons and submenu buttons
        JPanel mainNav = new JPanel();
        windowSize = new Dimension(120, 300);
        mainNav.setLayout(new OverlayLayout(mainNav));
        mainNav.setPreferredSize(windowSize);
        mainNav.setMaximumSize(windowSize);
        mainNav.setMinimumSize(windowSize);
        mainNav.setBackground(Color.WHITE);
        mainNav.setOpaque(false);

        // Creates the buttons that will show when clicking Monkeys
        JPanel monkPopupNav = createMonkeyPopup(butPanel);
        mainNav.add(monkPopupNav);
        // Creates the buttons that will be shown when clicking the top level Analytics button
        JPanel analPopupNav = createAnalyticsPopup(butPanel);
        mainNav.add(analPopupNav);

        // Gets the images and resizes it so that it fits in the image Panel
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
        Image image = icon.getImage();
        image = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JLabel iconLabel = new JLabel(icon);
        img.add(iconLabel);

// Start of creating and adding top level buttons and adding to button panel ===========================================
        JButton treeBut = new JButton("Monkeys");
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
                    //panelList.get(0).setVisible(true);
                    //setVisiblePanel(panelList.get(0));

                    butPanel.setVisible(false);
                    butPanel.setEnabled(false);

                    monkPopupNav.setVisible(true);
                    monkPopupNav.setEnabled(true);

                    setPanelColor(new Color(253, 238, 229));
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
        butPanel.add(treeBut);

        JButton analBut = new JButton("Analytics");
        analBut.setBorderPainted(false);
        analBut.setFocusPainted(false);
        analBut.setFont(new Font("Arial", Font.PLAIN, 12));
        analBut.setBackground(Color.WHITE);
        analBut.setForeground(Color.BLACK);
        analBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) analBut.getModel();
                if(model.isPressed()) {
                    butPanel.setVisible(false);
                    butPanel.setEnabled(false);

                    analPopupNav.setVisible(true);
                    analPopupNav.setEnabled(true);

                    setPanelColor(new Color(253, 238, 229));
                }
                else if(model.isRollover()) {
                    analBut.setBackground(new Color(253, 238, 229));
                    analBut.setForeground(new Color(214, 79, 1));
                }
                else {
                    analBut.setBackground(Color.WHITE);
                    analBut.setForeground(Color.BLACK);
                }
            }
        });
        butPanel.add(analBut);

        JButton searchBut = new JButton("Search");
        searchBut.setBorderPainted(false);
        searchBut.setFocusPainted(false);
        searchBut.setFont(new Font("Arial", Font.PLAIN, 12));
        searchBut.setBackground(Color.WHITE);
        searchBut.setForeground(Color.BLACK);
        searchBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) searchBut.getModel();
                if(model.isPressed()) {
                    setVisiblePanel(null);
                }
                else if(model.isRollover()) {
                    searchBut.setBackground(new Color(253,238,229));
                    searchBut.setForeground(new Color(214, 79, 1));
                }
                else {
                    searchBut.setBackground(Color.WHITE);
                    searchBut.setForeground(Color.BLACK);
                }
            }
        });
        butPanel.add(searchBut);
// End of creating top level buttons ===================================================================================

        mainNav.add(butPanel);

        this.add(img, BorderLayout.NORTH);
        this.add(mainNav, BorderLayout.SOUTH);
    }

    void setPanelColor(Color color) {
        this.setBackground(color);
    }

    private JPanel createMonkeyPopup(JPanel mainNav) {
        // Creates the buttons for the Analytics submenu
        JPanel popupNav = new JPanel();
        popupNav.setVisible(false);
        popupNav.setLayout(new GridLayout(6, 1, 0, 0));
        popupNav.setPreferredSize(new Dimension(100, 300));
        //popupNav.setBackground(Color.WHITE);
        popupNav.setOpaque(false);

        JButton familyTreeBut = new JButton("Family Tree");
        familyTreeBut.setBorderPainted(false);
        familyTreeBut.setFocusPainted(false);
        familyTreeBut.setFont(new Font("Arial", Font.PLAIN, 12));
        familyTreeBut.setBackground(new Color(253,238,229));
        familyTreeBut.setForeground(new Color(214, 79, 1));
        familyTreeBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) familyTreeBut.getModel();
                if(model.isPressed()) {
                    setVisiblePanel(panelList.get(0));

//                    mainNav.setVisible(true);
//                    mainNav.setEnabled(true);
//
//                    popupNav.setEnabled(false);
//                    popupNav.setVisible(false);

//                    setPanelColor(Color.WHITE);
                }
                else if(model.isRollover()) {
                    familyTreeBut.setBackground(Color.WHITE);
                    familyTreeBut.setForeground(Color.BLACK);
                }
                else {
                    familyTreeBut.setBackground(new Color(253,238,229));
                    familyTreeBut.setForeground(new Color(214, 79, 1));
                }
            }
        });
        popupNav.add(familyTreeBut);

        JButton monkeyTable = new JButton("Monkey Table");
        monkeyTable.setBorderPainted(false);
        monkeyTable.setFocusPainted(false);
        monkeyTable.setFont(new Font("Arial", Font.PLAIN, 12));
        monkeyTable.setBackground(new Color(253, 238, 229));
        monkeyTable.setForeground(new Color(214, 79, 1));
        monkeyTable.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) monkeyTable.getModel();
                if(model.isPressed()) {
                    setVisiblePanel(panelList.get(4));

//                    mainNav.setVisible(true);
//                    mainNav.setEnabled(true);
//
//                    popupNav.setEnabled(false);
//                    popupNav.setVisible(false);

//                    setPanelColor(Color.WHITE);
                }
                else if(model.isRollover()) {
                    monkeyTable.setBackground(Color.WHITE);
                    monkeyTable.setForeground(Color.BLACK);
                }
                else {
                    monkeyTable.setBackground(new Color(253, 238, 229));
                    monkeyTable.setForeground(new Color(214, 79, 1));
                }
            }
        });
        popupNav.add(monkeyTable);

        JButton backBut = new JButton("Back");
        backBut.setBorderPainted(false);
        backBut.setFocusPainted(false);
        backBut.setFont(new Font("Arial", Font.PLAIN, 12));
        backBut.setBackground(new Color(253,238,229));
        backBut.setForeground(new Color(214, 79, 1));
        backBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) backBut.getModel();
                if(model.isPressed()) {
                    mainNav.setVisible(true);
                    mainNav.setEnabled(true);

                    popupNav.setEnabled(false);
                    popupNav.setVisible(false);

                    setPanelColor(Color.WHITE);
                }
                else if(model.isRollover()) {
                    backBut.setBackground(Color.WHITE);
                    backBut.setForeground(Color.BLACK);
                }
                else {
                    backBut.setBackground(new Color(253,238,229));
                    backBut.setForeground(new Color(214, 79, 1));
                }
            }
        });
        popupNav.add(backBut);

        return popupNav;
    }

    private JPanel createAnalyticsPopup(JPanel mainNav) {
        // Creates the buttons for the Analytics submenu
        JPanel popupNav = new JPanel();
        popupNav.setVisible(false);
        popupNav.setLayout(new GridLayout(6, 1, 0, 0));
        popupNav.setPreferredSize(new Dimension(100, 300));
        //popupNav.setBackground(Color.WHITE);
        popupNav.setOpaque(false);

        JButton lifeTableBut = new JButton("Life Table");
        lifeTableBut.setBorderPainted(false);
        lifeTableBut.setFocusPainted(false);
        lifeTableBut.setFont(new Font("Arial", Font.PLAIN, 12));
        lifeTableBut.setBackground(new Color(253,238,229));
        lifeTableBut.setForeground(new Color(214, 79, 1));
        lifeTableBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) lifeTableBut.getModel();
                if(model.isPressed()) {
                    setVisiblePanel(panelList.get(1));

//                    mainNav.setVisible(true);
//                    mainNav.setEnabled(true);
//
//                    popupNav.setEnabled(false);
//                    popupNav.setVisible(false);

//                    setPanelColor(Color.WHITE);
                }
                else if(model.isRollover()) {
                    lifeTableBut.setBackground(Color.WHITE);
                    lifeTableBut.setForeground(Color.BLACK);
                }
                else {
                    lifeTableBut.setBackground(new Color(253,238,229));
                    lifeTableBut.setForeground(new Color(214, 79, 1));
                }
            }
        });
        popupNav.add(lifeTableBut);

        JButton suturesGraph = new JButton("Suture Graph");
        suturesGraph.setBorderPainted(false);
        suturesGraph.setFocusPainted(false);
        suturesGraph.setFont(new Font("Arial", Font.PLAIN, 12));
        suturesGraph.setBackground(new Color(253, 238, 229));
        suturesGraph.setForeground(new Color(214, 79, 1));
        suturesGraph.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) suturesGraph.getModel();
                if(model.isPressed()) {
                    setVisiblePanel(panelList.get(2));

//                    mainNav.setVisible(true);
//                    mainNav.setEnabled(true);
//
//                    popupNav.setEnabled(false);
//                    popupNav.setVisible(false);

//                    setPanelColor(Color.WHITE);
                }
                else if(model.isRollover()) {
                    suturesGraph.setBackground(Color.WHITE);
                    suturesGraph.setForeground(Color.BLACK);
                }
                else {
                    suturesGraph.setBackground(new Color(253, 238, 229));
                    suturesGraph.setForeground(new Color(214, 79, 1));
                }
            }
        });
        popupNav.add(suturesGraph);

        JButton healingBut = new JButton("Healing Graph");
        healingBut.setBorderPainted(false);
        healingBut.setFocusPainted(false);
        healingBut.setFont(new Font("Arial", Font.PLAIN, 12));
        healingBut.setBackground(new Color(253,238,229));
        healingBut.setForeground(new Color(214, 79, 1));
        healingBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) healingBut.getModel();
                if(model.isPressed()) {
                    setVisiblePanel(panelList.get(3));

//                    mainNav.setVisible(true);
//                    mainNav.setEnabled(true);
//
//                    popupNav.setEnabled(false);
//                    popupNav.setVisible(false);

//                    setPanelColor(Color.WHITE);
                }
                else if(model.isRollover()) {
                    healingBut.setBackground(Color.WHITE);
                    healingBut.setForeground(Color.BLACK);
                }
                else {
                    healingBut.setBackground(new Color(253,238,229));
                    healingBut.setForeground(new Color(214, 79, 1));
                }
            }
        });
        popupNav.add(healingBut);

        JButton backBut = new JButton("Back");
        backBut.setBorderPainted(false);
        backBut.setFocusPainted(false);
        backBut.setFont(new Font("Arial", Font.PLAIN, 12));
        backBut.setBackground(new Color(253,238,229));
        backBut.setForeground(new Color(214, 79, 1));
        backBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) backBut.getModel();
                if(model.isPressed()) {
                    mainNav.setVisible(true);
                    mainNav.setEnabled(true);

                    popupNav.setEnabled(false);
                    popupNav.setVisible(false);

                    setPanelColor(Color.WHITE);
                }
                else if(model.isRollover()) {
                    backBut.setBackground(Color.WHITE);
                    backBut.setForeground(Color.BLACK);
                }
                else {
                    backBut.setBackground(new Color(253,238,229));
                    backBut.setForeground(new Color(214, 79, 1));
                }
            }
        });
        popupNav.add(backBut);

        return popupNav;
    }

    void setVisiblePanel(JPanel visiblePanel) {
        for(JPanel pan:panelList) {
            pan.setVisible(false);
            pan.setEnabled(false);
        }

        if(visiblePanel != null) {
            visiblePanel.setVisible(true);
            visiblePanel.setEnabled(true);
        }
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    /*public void addPanel(JPanel newPanel) {
        panelList.add(newPanel);
    }*/
}
