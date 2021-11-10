package com.nsfdb.gui.main;

import com.nsfdb.gui.dashboard.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DBMainWindow extends JFrame {
    public DBMainWindow() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();

        // Build the file menu
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_1);
        menuItem = new JMenuItem("Logout");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK
        ));
        menu.add(menuItem);
        menuBar.add(menu);

        // Build the edit menu
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_2);
        menuItem = new JMenuItem("Temp");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.ALT_MASK
        ));
        menu.add(menuItem);
        menuBar.add(menu);

        // Build the view menu
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_3);
        menuItem = new JMenuItem("Temp");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_B, ActionEvent.ALT_MASK
        ));
        menu.add(menuItem);
        menuBar.add(menu);

        this.setJMenuBar(menuBar);
    }
    public static void main(String[] args) {
        JFrame window = new DBMainWindow();
        window.setTitle("Monkey DB");

        FamilyTreePanel treePanel = new FamilyTreePanel();
        window.add(treePanel, BorderLayout.CENTER);

        MonkeyDetailsPanel detailsPanel = new MonkeyDetailsPanel();
        window.add(detailsPanel, BorderLayout.EAST);

        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
