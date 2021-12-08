package com.nsfdb.gui.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.gui.dashboard.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class DBMainWindow extends JFrame {
    public DBMainWindow() throws ExecutionException, IOException, InterruptedException, TimeoutException {
        FamilyTreePanel treePanel = new FamilyTreePanel();
        this.add(treePanel, BorderLayout.CENTER);

        ArrayList<JPanel> panels = new ArrayList<>();
        panels.add(treePanel);

        NavigationPanel nav = new NavigationPanel(panels);
        this.add(nav, BorderLayout.WEST);

        //nav.addPanel(treePanel);
    }

    public static void main(String[] args) throws ExecutionException, IOException, InterruptedException, TimeoutException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }

                JFrame window = null;
                try {
                    window = new DBMainWindow();

                    window.setTitle("Monkey DB");

                    //MonkeyDetailsPanel detailsPanel = new MonkeyDetailsPanel();
                    //window.add(detailsPanel, BorderLayout.EAST);

                    window.pack();
                    window.setVisible(true);
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
