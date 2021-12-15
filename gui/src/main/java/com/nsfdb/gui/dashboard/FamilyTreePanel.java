package com.nsfdb.gui.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.analytics.aggregations.FamilyTree;
import com.nsfdb.api.analytics.aggregations.FamilyTreeNode;
import com.nsfdb.api.models.Monkey;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class FamilyTreePanel extends JPanel {
    private JTree tree;
    MonkeyDetailsPanel detailsPanel;
    BoneDataPanel bonePanel;

    public FamilyTreePanel(FamilyTree myTree) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
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

        //FamilyTree myTree = new FamilyTree();
        //myTree.create();

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

        //myTree.printTree();

        //tree = new JTree(top);
        tree = new JTree(myTree.treeify());
        tree.setCellRenderer(new MyRenderer());
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

        // create border for details panel panels
        Border empty = BorderFactory.createEmptyBorder(0, -1, 0, -1);
        Border grayLine = BorderFactory.createLineBorder(new Color(130, 135, 144),1);
        Border topAndBotBorder = new CompoundBorder(empty, grayLine);
        detailsPanel.setBorder(topAndBotBorder);
        //bonePanel.setBorder(grayLine);

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

    private class MyRenderer extends DefaultTreeCellRenderer {
        Icon femaleIcon;
        Icon maleIcon;
        Icon unknownIcon;

        public MyRenderer() {
            // Loads the image from Resources
            try {
                ImageIcon femaleImageIcon = new ImageIcon(getClass().getClassLoader().getResource("FemaleMonkey.png"));
                ImageIcon maleImageIcon = new ImageIcon(getClass().getClassLoader().getResource("MaleMonkey.png"));
                ImageIcon unknownImageIcon = new ImageIcon(getClass().getClassLoader().getResource("UnknownMonkey.png"));

                // Sets as image and resizes it
                Image femaleImage = femaleImageIcon.getImage();
                Image maleImage = maleImageIcon.getImage();
                Image unknownImage = unknownImageIcon.getImage();
                femaleImage = femaleImage.getScaledInstance(16, 13, Image.SCALE_SMOOTH);
                maleImage = maleImage.getScaledInstance(16, 13, Image.SCALE_SMOOTH);
                unknownImage = unknownImage.getScaledInstance(16, 13, Image.SCALE_SMOOTH);

                // Sets the resized image as an icon
                femaleIcon = new ImageIcon(femaleImage);
                maleIcon = new ImageIcon(maleImage);
                unknownIcon = new ImageIcon(unknownImage);
            }
            catch(NullPointerException e) {
                System.out.println("Icon Resources are missing");
            }
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                                                    boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel,
                                            expanded, leaf, row, hasFocus);
            // Sets the icon based on gender
            if(genderOf(value).equals("NULL")) {
                setIcon(unknownIcon);
            }
            else if(genderOf(value).equals("M")) {
                setIcon(maleIcon);
            }
            else {
                setIcon(femaleIcon);
            }

            return this;
        }

        // Gets the Monkey Object and returns its gender
        protected String genderOf(Object value) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            FamilyTreeNode familyTreeNode = (FamilyTreeNode) (node);

            if(familyTreeNode.getMonkey().getSex() == null)
                return "NULL";
            else
                return familyTreeNode.getMonkey().getSex();
        }
    }
}
