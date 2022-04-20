package com.nsfdb.gui.dashboard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.analytics.aggregations.LifeTable;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LifeTablePanel extends JPanel {
    JTable lifeTable;

    public LifeTablePanel() throws JsonProcessingException {
        // Gets the columns and data Arrays from the LifeTable class and adds it to a JTable
        LifeTable tableData = new LifeTable();
        String columns[] = tableData.getColumns();
        double data[][] = tableData.getLifeTableData();
        Double dataBoxed[][] = new Double[data.length][data[0].length];

        // Boxes the array type double into array type Double for use in the JTable
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[0].length; j++){
                dataBoxed[i][j] = data[i][j];
            }
        }
        lifeTable = new JTable(dataBoxed, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane sp = new JScrollPane(lifeTable);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(sp);
    }

    // Test the LifeTablePanel class by creating a JFrame and adding a LifeTablePanel to it
    public static void main(String[] args) throws JsonProcessingException {
        JFrame f = new JFrame();

        LifeTablePanel a = new LifeTablePanel();

        f.add(a);

        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
