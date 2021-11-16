package com.nsfdb.gui.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.analytics.aggregations.FamilyTree;
import com.nsfdb.api.analytics.aggregations.FamilyTreeNode;
import com.nsfdb.api.models.Monkey;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class FamilyTreePanel extends JPanel {
    private JTree tree;

    public FamilyTreePanel() throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        super(new GridLayout(1,0));
        Dimension windowSize = new Dimension(300, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

        //DefaultMutableTreeNode top = new DefaultMutableTreeNode("Monkeys");
        //FamilyTreeNode top = new FamilyTreeNode(new Monkey());
        Monkey fakeMonkey = new Monkey();
        FamilyTreeNode top = new FamilyTreeNode(fakeMonkey);

        //viewTest(top); // Tests the tree and the scroll feature

        FamilyTree myTree = new FamilyTree();
        myTree.create();

        for(int i = 0; i <myTree.getMonkeyList().size(); i++) {
            FamilyTreeNode root = myTree.getMonkeyList().get(i)[0];
            fillTree(root);
            top.add(root);
        }
       /* for(int i = 0; i < myTree.getMonkeyList().size(); i++)
        {
            root = new FamilyTreeNode(myTree.getMonkeyList().get(0)[0].getMonkey());
            fillTree(root);
            top.add(root);
        }*/

        myTree.printTree();

        tree = new JTree(top);

        // Expands the tree to show all nodes
        for(int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }

        JScrollPane treeView = new JScrollPane(tree);
        this.add(treeView);
    }

    private void fillTree(FamilyTreeNode root) {
        if(root.getChild()[0] != null) {
            root.add(root.getChild()[0]);
            fillSibling(root.getChild()[0], root);
            fillTree(root.getChild()[0]);
        }
    }

    private void fillSibling(FamilyTreeNode root, FamilyTreeNode parent) {
        if(root.getSibling()[0] != null) {
            parent.add(root.getSibling()[0]);
            fillTree(root.getSibling()[0]);
            fillSibling(root.getSibling()[0], parent);
        }
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
