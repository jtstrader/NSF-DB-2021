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

        // Build the Family Tree Panel
        FamilyTreePanel treePanel = new FamilyTreePanel();
        treePanel.setVisible(true);
        treePanel.setEnabled(true);
        //this.add(treePanel, BorderLayout.CENTER);

        // Build the life Panel
        LifeTablePanel lifePanel = new LifeTablePanel();
        lifePanel.setVisible(false);
        lifePanel.setEnabled(false);

        // Build the Closure Panel
        ClosurePanel closurePanel = new ClosurePanel();
        closurePanel.setVisible(false);
        closurePanel.setEnabled(false);

        //Build the Healing Panel
        HealingPanel healingPanel = new HealingPanel();
        healingPanel.setVisible(false);
        healingPanel.setEnabled(false);

        // Build the Display Panel
        JPanel display = new DisplayPanel();
        display.add(treePanel);
        display.add(lifePanel);
        display.add(closurePanel);
        display.add(healingPanel);
        this.add(display, BorderLayout.CENTER);

        // An array list of the major panels to be added to the Navigation Panel
        ArrayList<JPanel> panels = new ArrayList<>();
        panels.add(treePanel);
        panels.add(lifePanel);
        panels.add(closurePanel);
        panels.add(healingPanel);

        // Builds the Navigation Panel.
        NavigationPanel nav = new NavigationPanel(panels);
        this.add(nav, BorderLayout.WEST);

        //nav.addPanel(treePanel);
    }


    // Contains the Exceptions and the main functions fo build the whole GUI frame during  runtime.
    public static void main(String[] args) throws ExecutionException, IOException, InterruptedException, TimeoutException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Start up exceptions
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException e) { // Exception
                    e.printStackTrace();
                } catch (InstantiationException e) { // Exception
                    e.printStackTrace();
                } catch (IllegalAccessException e) { // Exception
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) { // Exception
                    e.printStackTrace();
                }

                // Building the GUI and the exception for the GUI
                JFrame window = null;
                try {
                    window = new DBMainWindow();

                    window.setTitle("Monkey DB");

                    //MonkeyDetailsPanel detailsPanel = new MonkeyDetailsPanel();
                    //window.add(detailsPanel, BorderLayout.EAST);

                    window.pack();
                    window.setVisible(true);
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } catch (ExecutionException e) { // Exception
                    e.printStackTrace();
                } catch (JsonProcessingException e) { // Exception
                    e.printStackTrace();
                } catch (InterruptedException e) { // Exception
                    e.printStackTrace();
                } catch (TimeoutException e) { // Exception
                    e.printStackTrace();
                } catch (IOException e) { // Exception
                    e.printStackTrace();
                }
            }
        });
    }
}
