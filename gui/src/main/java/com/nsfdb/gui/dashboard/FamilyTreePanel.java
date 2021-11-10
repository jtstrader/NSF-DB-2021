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

        JScrollPane treeView = new JScrollPane(tree);
        this.add(treeView);
    }

    // Fills the tree as a test
    private void viewTest(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode sec =
                new DefaultMutableTreeNode("Second");
        top.add(sec);
        DefaultMutableTreeNode third =
                new DefaultMutableTreeNode("Third");
        sec.add(third);
        DefaultMutableTreeNode fourth =
                new DefaultMutableTreeNode("Fourth");
        third.add(fourth);
        DefaultMutableTreeNode Fifth =
                new DefaultMutableTreeNode("Fifth");
        fourth.add(Fifth);

        for(int i = 0; i <= 100; i++) {
            top.add(new DefaultMutableTreeNode("Filler"));
        }
    }
}
