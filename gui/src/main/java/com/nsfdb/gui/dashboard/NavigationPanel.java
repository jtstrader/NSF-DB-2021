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
    // Change colors main buttons
    final Color NORM_BACK_COLOR = Color.WHITE;
    final Color NORM_TEXT_COLOR = Color.BLACK;
    final Color HOVER_BACK_COLOR = new Color(253,238,229);
    final Color HOVER_TEXT_COLOR = new Color(214, 79, 1);
    //Change colors of secondary buttons
    final Color NORM_BACK_COLOR_SECONDARY = new Color(253,238,229);
    final Color NORM_TEXT_COLOR_SECONDARY = new Color(214, 79, 1);
    final Color HOVER_BACK_COLOR_SECONDARY = Color.WHITE;
    final Color HOVER_TEXT_COLOR_SECONDARY = Color.BLACK;

    public NavigationPanel(ArrayList<JPanel> panelList) throws IOException {
        this.panelList = panelList;
        //this.setLayout(null);
        // Top level panel is 1 pixel less in width to hide white line.
        Dimension windowSize = new Dimension(PANEL_WIDTH - 1, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);
        this.setBackground(NORM_BACK_COLOR);
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
        //windowSize = new Dimension(120, 300);
        mainNav.setLayout(new OverlayLayout(mainNav));
        mainNav.setPreferredSize(windowSize);
        mainNav.setMaximumSize(windowSize);
        mainNav.setMinimumSize(windowSize);
        mainNav.setBackground(NORM_BACK_COLOR);
        mainNav.setOpaque(false);

        // Creates the buttons that will show when clicking Monkeys
        JPanel monkPopupNav = createMonkeyPopup(butPanel);
        mainNav.add(monkPopupNav);
        // Creates the buttons that will be shown when clicking the top level Analytics button
        JPanel analPopupNav = createAnalyticsPopup(butPanel);
        mainNav.add(analPopupNav);

        // Gets the images and resizes it so that it fits in the image Panel
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
            Image image = icon.getImage();
            image = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            JLabel iconLabel = new JLabel(icon);
            img.add(iconLabel);
        }
        catch (NullPointerException e) {
            System.out.println("Icon Resources are missing");
        }

// Start of creating and adding top level buttons and adding to button panel ===========================================
        JButton monkeyBut = new JButton("Monkeys");
        monkeyBut.setBorderPainted(false);
        monkeyBut.setFocusPainted(false);
        monkeyBut.setFont(new Font("Arial", Font.PLAIN, 12));
        monkeyBut.setBackground(NORM_BACK_COLOR);
        monkeyBut.setForeground(NORM_TEXT_COLOR);
        monkeyBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) monkeyBut.getModel();
                // Enables and Shows Monkey Buttons
                // and Disables and Hides Main Buttons
                if(model.isPressed()) {
                    //panelList.get(0).setVisible(true);
                    //setVisiblePanel(panelList.get(0));

                    butPanel.setVisible(false);
                    butPanel.setEnabled(false);

                    monkPopupNav.setVisible(true);
                    monkPopupNav.setEnabled(true);

                    setPanelColor(NORM_BACK_COLOR_SECONDARY);
                }
                else if(model.isRollover()) {
                    monkeyBut.setBackground(HOVER_BACK_COLOR);
                    monkeyBut.setForeground(HOVER_TEXT_COLOR);
                }
                else {
                    monkeyBut.setBackground(NORM_BACK_COLOR);
                    monkeyBut.setForeground(NORM_TEXT_COLOR);
                }
            }
        });
        butPanel.add(monkeyBut);

        JButton analBut = new JButton("Analytics");
        analBut.setBorderPainted(false);
        analBut.setFocusPainted(false);
        analBut.setFont(new Font("Arial", Font.PLAIN, 12));
        analBut.setBackground(NORM_BACK_COLOR);
        analBut.setForeground(NORM_TEXT_COLOR);
        analBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) analBut.getModel();
                // Enables and Shows Analytic Buttons
                // Disables and hides Main Buttons
                if(model.isPressed()) {
                    butPanel.setVisible(false);
                    butPanel.setEnabled(false);

                    analPopupNav.setVisible(true);
                    analPopupNav.setEnabled(true);

                    setPanelColor(NORM_BACK_COLOR_SECONDARY);
                }
                else if(model.isRollover()) {
                    analBut.setBackground(HOVER_BACK_COLOR);
                    analBut.setForeground(HOVER_TEXT_COLOR);
                }
                else {
                    analBut.setBackground(NORM_BACK_COLOR);
                    analBut.setForeground(NORM_TEXT_COLOR);
                }
            }
        });
        butPanel.add(analBut);

        JButton searchBut = new JButton("Search");
        searchBut.setBorderPainted(false);
        searchBut.setFocusPainted(false);
        searchBut.setFont(new Font("Arial", Font.PLAIN, 12));
        searchBut.setBackground(NORM_BACK_COLOR);
        searchBut.setForeground(NORM_TEXT_COLOR);
        searchBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) searchBut.getModel();
                // Currently Not implemented
                // Will be used to show Search Panel, which will have to be added to DisplayPanel
                // so that it will be shown
                // and the the array that is sent to NavigationPanel so it can be accessed here

                // Example of enabling panel
                // setVisiblePanel(panelList.get(index)); // index should be replaced by the index in the array

                if(model.isPressed()) {
                    setVisiblePanel(null); // Replace null with panelList.get(I) where I is index of search panel
                    // UNCOMMENT when button is implemented
                    //butPanel.setVisible(false);
                    //butPanel.setEnabled(false);

                }
                else if(model.isRollover()) {
                    searchBut.setBackground(HOVER_BACK_COLOR);
                    searchBut.setForeground(HOVER_TEXT_COLOR);
                }
                else {
                    searchBut.setBackground(NORM_BACK_COLOR);
                    searchBut.setForeground(NORM_TEXT_COLOR);
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
        // Creates the buttons for the Monkey submenu
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
        familyTreeBut.setBackground(NORM_BACK_COLOR_SECONDARY);
        familyTreeBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
        familyTreeBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) familyTreeBut.getModel();
                // Enables and Shows the FamilyTreePanel
                // Disable and Hides all the other display panels
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
                    familyTreeBut.setBackground(HOVER_BACK_COLOR_SECONDARY);
                    familyTreeBut.setForeground(HOVER_TEXT_COLOR_SECONDARY);
                }
                else {
                    familyTreeBut.setBackground(NORM_BACK_COLOR_SECONDARY);
                    familyTreeBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
                }
            }
        });
        popupNav.add(familyTreeBut);

        JButton monkeyTable = new JButton("Monkey Table");
        monkeyTable.setBorderPainted(false);
        monkeyTable.setFocusPainted(false);
        monkeyTable.setFont(new Font("Arial", Font.PLAIN, 12));
        monkeyTable.setBackground(NORM_BACK_COLOR_SECONDARY);
        monkeyTable.setForeground(NORM_TEXT_COLOR_SECONDARY);
        monkeyTable.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) monkeyTable.getModel();
                /// Enables and Shows the MonkeyTablePanel
                // Disable and Hides all the other display panels
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
                    monkeyTable.setBackground(HOVER_BACK_COLOR_SECONDARY);
                    monkeyTable.setForeground(HOVER_TEXT_COLOR_SECONDARY);
                }
                else {
                    monkeyTable.setBackground(NORM_BACK_COLOR_SECONDARY);
                    monkeyTable.setForeground(NORM_TEXT_COLOR_SECONDARY);
                }
            }
        });
        popupNav.add(monkeyTable);

        JButton backBut = new JButton("Back");
        backBut.setBorderPainted(false);
        backBut.setFocusPainted(false);
        backBut.setFont(new Font("Arial", Font.PLAIN, 12));
        backBut.setBackground(NORM_BACK_COLOR_SECONDARY);
        backBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
        backBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) backBut.getModel();
                // Enables and Shows Main Navigation Buttons
                // Disable and Hides the Monkey Buttons
                if(model.isPressed()) {
                    setVisiblePanel(panelList.get(0));

                    mainNav.setVisible(true);
                    mainNav.setEnabled(true);

                    popupNav.setEnabled(false);
                    popupNav.setVisible(false);

                    setPanelColor(NORM_BACK_COLOR);
                }
                else if(model.isRollover()) {
                    backBut.setBackground(HOVER_BACK_COLOR_SECONDARY);
                    backBut.setForeground(HOVER_TEXT_COLOR_SECONDARY);
                }
                else {
                    backBut.setBackground(NORM_BACK_COLOR_SECONDARY);
                    backBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
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
        lifeTableBut.setBackground(NORM_BACK_COLOR_SECONDARY);
        lifeTableBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
        lifeTableBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) lifeTableBut.getModel();
                // Enables and Shows the Life Table Panel
                // Disable and Hides all the other display panels
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
                    lifeTableBut.setBackground(HOVER_BACK_COLOR_SECONDARY);
                    lifeTableBut.setForeground(HOVER_TEXT_COLOR_SECONDARY);
                }
                else {
                    lifeTableBut.setBackground(NORM_BACK_COLOR_SECONDARY);
                    lifeTableBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
                }
            }
        });
        popupNav.add(lifeTableBut);

        JButton suturesGraph = new JButton("Suture Graph");
        suturesGraph.setBorderPainted(false);
        suturesGraph.setFocusPainted(false);
        suturesGraph.setFont(new Font("Arial", Font.PLAIN, 12));
        suturesGraph.setBackground(NORM_BACK_COLOR_SECONDARY);
        suturesGraph.setForeground(NORM_TEXT_COLOR_SECONDARY);
        suturesGraph.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) suturesGraph.getModel();
                // Enables and Shows the Suture Graph Panel(ClosurePanel)
                // Disable and Hides all the other display panels
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
                    suturesGraph.setBackground(HOVER_BACK_COLOR_SECONDARY);
                    suturesGraph.setForeground(HOVER_TEXT_COLOR_SECONDARY);
                }
                else {
                    suturesGraph.setBackground(NORM_BACK_COLOR_SECONDARY);
                    suturesGraph.setForeground(NORM_TEXT_COLOR_SECONDARY);
                }
            }
        });
        popupNav.add(suturesGraph);

        JButton healingBut = new JButton("Healing Graph");
        healingBut.setBorderPainted(false);
        healingBut.setFocusPainted(false);
        healingBut.setFont(new Font("Arial", Font.PLAIN, 12));
        healingBut.setBackground(NORM_BACK_COLOR_SECONDARY);
        healingBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
        healingBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) healingBut.getModel();
                // Enables and Shows the Healing Graph Panel(HealingPanel)
                // Disable and Hides all the other display panels
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
                    healingBut.setBackground(HOVER_BACK_COLOR_SECONDARY);
                    healingBut.setForeground(HOVER_TEXT_COLOR_SECONDARY);
                }
                else {
                    healingBut.setBackground(NORM_BACK_COLOR_SECONDARY);
                    healingBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
                }
            }
        });
        popupNav.add(healingBut);

        JButton backBut = new JButton("Back");
        backBut.setBorderPainted(false);
        backBut.setFocusPainted(false);
        backBut.setFont(new Font("Arial", Font.PLAIN, 12));
        backBut.setBackground(NORM_BACK_COLOR_SECONDARY);
        backBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
        backBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) backBut.getModel();
                // Enables and Shows Main Navigation Buttons
                // Disable and Hides the Analytic Buttons
                if(model.isPressed()) {
                    setVisiblePanel(panelList.get(0));

                    mainNav.setVisible(true);
                    mainNav.setEnabled(true);

                    popupNav.setEnabled(false);
                    popupNav.setVisible(false);

                    setPanelColor(NORM_BACK_COLOR);
                }
                else if(model.isRollover()) {
                    backBut.setBackground(HOVER_BACK_COLOR_SECONDARY);
                    backBut.setForeground(HOVER_TEXT_COLOR_SECONDARY);
                }
                else {
                    backBut.setBackground(NORM_BACK_COLOR_SECONDARY);
                    backBut.setForeground(NORM_TEXT_COLOR_SECONDARY);
                }
            }
        });
        popupNav.add(backBut);

        return popupNav;
    }

    void setVisiblePanel(JPanel visiblePanel) {
        // Enables and Shows the JPanel passed in
        // and Disables and hides the other Display Panels
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
