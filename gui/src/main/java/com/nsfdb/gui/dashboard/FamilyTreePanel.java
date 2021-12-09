package com.nsfdb.gui.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.analytics.aggregations.FamilyTree;
import com.nsfdb.api.analytics.aggregations.FamilyTreeNode;
import com.nsfdb.api.models.Monkey;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class FamilyTreePanel extends JPanel {
    private JTree tree;
    MonkeyDetailsPanel detailsPanel;
    BoneDataPanel bonePanel;

    public FamilyTreePanel() throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        super(new GridLayout(1,0));
        Dimension windowSize = new Dimension(600, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

        detailsPanel = new MonkeyDetailsPanel();
        bonePanel = new BoneDataPanel();

        //DefaultMutableTreeNode top = new DefaultMutableTreeNode("Monkeys");
        //FamilyTreeNode top = new FamilyTreeNode(new Monkey());
        Monkey fakeMonkey = new Monkey();
        fakeMonkey.setAnimal_id("Monkeys");
        FamilyTreeNode top = new FamilyTreeNode(fakeMonkey);

        //viewTest(top); // Tests the tree and the scroll feature

        FamilyTree myTree = new FamilyTree();
        myTree.create();

        /*for(int i = 0; i <myTree.getMonkeyList().size(); i++) {
            FamilyTreeNode root = myTree.getMonkeyList().get(i)[0];
            fillTree(root);
            top.add(root);
        }*/
       /* for(int i = 0; i < myTree.getMonkeyList().size(); i++)
        {
            root = new FamilyTreeNode(myTree.getMonkeyList().get(0)[0].getMonkey());
            fillTree(root);
            top.add(root);
        }*/

        myTree.printTree();

        //tree = new JTree(top);
        tree = new JTree(myTree.treeify());

        // Expands the tree to show all nodes
        for(int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }

        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                FamilyTreeNode node = (FamilyTreeNode)
                        tree.getLastSelectedPathComponent();

                if (node == null) return;
                detailsPanel.setMonkey(node.getMonkey());

            }
        });

        // create panels
        JScrollPane treeView = new JScrollPane(tree);
        JPanel treePanel = new JPanel();
        JPanel dataPanel = new JPanel();

        //create layout
        FlowLayout layout = (FlowLayout)dataPanel.getLayout();
        layout.setHgap(0);
        layout.setVgap(0);

        // create border for panels
        Border blackLine = BorderFactory.createLineBorder(Color.BLACK,1);
        detailsPanel.setBorder(blackLine);
        //bonePanel.setBorder(blackLine);

        treePanel.add(treeView);
        dataPanel.add(detailsPanel,BorderLayout.NORTH);
        dataPanel.add(bonePanel,BorderLayout.SOUTH);

        this.add(treeView, BorderLayout.WEST);
        this.add(dataPanel, BorderLayout.EAST);

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
