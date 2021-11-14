package com.nsfdb.gui.dashboard;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class FamilyTreePanel extends JPanel {
    private JTree tree;

    public FamilyTreePanel() {
        super(new GridLayout(1,0));
        Dimension windowSize = new Dimension(300, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Monkeys");

        viewTest(top); // Tests the tree and the scroll feature

        tree = new JTree(top);

        // Expands the tree to show all nodes
        for(int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }

        JScrollPane treeView = new JScrollPane(tree);
        this.add(treeView);
    }

    // Fills the tree as a test
    private void viewTest(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode sec =
                new DefaultMutableTreeNode("Second");
        top.add(sec);
        DefaultMutableTreeNode sec2 =
                new DefaultMutableTreeNode("Second2");
        top.add(sec2);
        DefaultMutableTreeNode third =
                new DefaultMutableTreeNode("Third");
        sec.add(third);
        DefaultMutableTreeNode third2=
                new DefaultMutableTreeNode("Third2");
        sec.add(third2);
        DefaultMutableTreeNode fourth =
                new DefaultMutableTreeNode("Fourth");
        sec.add(third);
        third.add(fourth);
        DefaultMutableTreeNode fourth2 =
                new DefaultMutableTreeNode("Fourth2");
        third.add(fourth2);
        DefaultMutableTreeNode Fifth =
                new DefaultMutableTreeNode("Fifth");
        fourth.add(Fifth);
        DefaultMutableTreeNode Fifth2 =
                new DefaultMutableTreeNode("Fifth2");
        fourth.add(Fifth2);

        for(int i = 0; i <= 100; i++) {
            top.add(new DefaultMutableTreeNode("Filler"));
        }
    }
}
