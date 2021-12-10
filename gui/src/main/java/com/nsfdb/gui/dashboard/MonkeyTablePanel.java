package com.nsfdb.gui.dashboard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.analytics.aggregations.FamilyTree;
import com.nsfdb.api.analytics.aggregations.FamilyTreeNode;
import com.nsfdb.api.analytics.aggregations.LifeTable;

import javax.swing.*;
import java.util.ArrayList;

public class MonkeyTablePanel extends JPanel {
    JTable monkeyTable;

    public MonkeyTablePanel(FamilyTree myTree) throws JsonProcessingException, InterruptedException {
        // Gets the columns and data Arrays from the LifeTable class and adds it to a JTable
        LifeTable tableData = new LifeTable();
        String columns[] = {"Animal ID", "Date of Birth", "Date of Death", "Gender", "Mom", "Status"};

        ArrayList<FamilyTreeNode[]> monkeyList = myTree.getMonkeyList();
        String data[][] = new String[myTree.size()][6];

        getMonkeyArray(monkeyList, data);

        monkeyTable = new JTable(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane sp = new JScrollPane(monkeyTable);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(sp);
    }

    private void getMonkeyArray(ArrayList<FamilyTreeNode[]> monkeyList, String[][] data) {
        int[] pos = {0};
        for(FamilyTreeNode[] familyTreeNodes : monkeyList) {
            data[pos[0]][0] = familyTreeNodes[0].getMonkey().getAnimal_id();
            data[pos[0]][1] = familyTreeNodes[0].getMonkey().getDate_of_birth().toString();
            data[pos[0]][2] = (familyTreeNodes[0].getMonkey().getDate_of_death() != null)
                                    ? familyTreeNodes[0].getMonkey().getDate_of_death().toString() : "null";
            data[pos[0]][3] = familyTreeNodes[0].getMonkey().getSex();
            data[pos[0]][4] = familyTreeNodes[0].getMonkey().getBehavior_mom();
            data[pos[0]][5] = familyTreeNodes[0].getMonkey().getStatus();
            pos[0]++;
            getMonkeyArrayNodes(familyTreeNodes[0], data, pos);
        }
        System.out.println(pos[0]);
    }

    private void getMonkeyArrayNodes(FamilyTreeNode root, String[][] data, int[] pos) {
        if(root.getChild()[0] != null) {
            data[pos[0]][0] = root.getChild()[0].getMonkey().getAnimal_id();
            data[pos[0]][1] = root.getChild()[0].getMonkey().getDate_of_birth().toString();
            data[pos[0]][2] = (root.getChild()[0].getMonkey().getDate_of_death() != null)
                                ? root.getChild()[0].getMonkey().getDate_of_death().toString() : "null";
            data[pos[0]][3] = root.getChild()[0].getMonkey().getSex();
            data[pos[0]][4] = root.getChild()[0].getMonkey().getBehavior_mom();
            data[pos[0]][5] = root.getChild()[0].getMonkey().getStatus();
            pos[0]++;
            getMonkeyArraySiblings(root.getChild()[0], data, pos);
            getMonkeyArrayNodes(root.getChild()[0], data, pos);
        }
    }

    private void getMonkeyArraySiblings(FamilyTreeNode root, String[][] data, int[] pos) {
        if(root.getSibling()[0] != null) {
            data[pos[0]][0] = root.getSibling()[0].getMonkey().getAnimal_id();
            data[pos[0]][1] = root.getSibling()[0].getMonkey().getDate_of_birth().toString();
            data[pos[0]][2] = (root.getSibling()[0].getMonkey().getDate_of_death() != null)
                                ? root.getSibling()[0].getMonkey().getDate_of_death().toString() : "null";
            data[pos[0]][3] = root.getSibling()[0].getMonkey().getSex();
            data[pos[0]][4] = root.getSibling()[0].getMonkey().getBehavior_mom();
            data[pos[0]][5] = root.getSibling()[0].getMonkey().getStatus();
            pos[0]++;
            getMonkeyArrayNodes(root.getSibling()[0], data, pos);
            getMonkeyArraySiblings(root.getSibling()[0], data, pos);
        }
    }

    // Test the LifeTablePanel class by creating a JFrame and adding a LifeTablePanel to it
    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
        JFrame f = new JFrame();
        FamilyTree myTree = new FamilyTree();
        myTree.create();
        MonkeyTablePanel a = new MonkeyTablePanel(myTree);

        f.add(a);

        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
