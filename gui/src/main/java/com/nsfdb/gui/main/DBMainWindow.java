package com.nsfdb.gui.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.analytics.aggregations.FamilyTree;
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

        // Application currently doesn't support resizing
        this.setResizable(false);

        FamilyTree myTree = new FamilyTree();
        myTree.create();

        // Build the Family Tree Panel
        // This will be the default display so it will be enabled at start
        FamilyTreePanel treePanel = new FamilyTreePanel(myTree);
        treePanel.setVisible(true);
        treePanel.setEnabled(true);
        //this.add(treePanel, BorderLayout.CENTER);

        // Build the life Panel
        // This panel will be disabled at start so the FamilyTreePanel can be shown
        // The navigation panel controls this
        LifeTablePanel lifePanel = new LifeTablePanel();
        lifePanel.setVisible(false);
        lifePanel.setEnabled(false);

        // Build the Closure Panel
        // This panel will be disabled at start so the FamilyTreePanel can be shown
        // The navigation panel controls this
        ClosurePanel closurePanel = new ClosurePanel();
        closurePanel.setVisible(false);
        closurePanel.setEnabled(false);

        //Build the Healing Panel
        // This panel will be disabled at start so the FamilyTreePanel can be shown
        // The navigation panel controls this
        HealingPanel healingPanel = new HealingPanel();
        healingPanel.setVisible(false);
        healingPanel.setEnabled(false);

        // Build the Monkey Table Panel
        // This panel will be disabled at start so the FamilyTreePanel can be shown
        // The navigation panel controls this
        MonkeyTablePanel monkeyPanel = new MonkeyTablePanel(myTree);
        monkeyPanel.setVisible(false);
        monkeyPanel.setEnabled(false);

        // Adds that other displays to the DisplayPanel, which is where they will be displayed
        JPanel display = new DisplayPanel();
        display.add(treePanel);
        display.add(lifePanel);
        display.add(closurePanel);
        display.add(healingPanel);
        display.add(monkeyPanel);
        this.add(display, BorderLayout.CENTER);

        // An array list of the major panels to be sent to
        // the NavigationPanel so that it can enable and display them
        // based on the buttons pressed
        ArrayList<JPanel> panels = new ArrayList<>();
        panels.add(treePanel);
        panels.add(lifePanel);
        panels.add(closurePanel);
        panels.add(healingPanel);
        panels.add(monkeyPanel);

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
                // Sets the Appearance to that of Windows
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
