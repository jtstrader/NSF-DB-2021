package com.nsfdb.gui.dashboard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.analytics.aggregations.LifeTable;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LifeTablePanel extends JPanel {
    JTable lifeTable;

    public LifeTablePanel() throws JsonProcessingException {
        LifeTable tableData = new LifeTable();
        String columns[] = tableData.getColumns();
        double data[][] = tableData.getLifeTableData();
        Double dataBoxed[][] = new Double[data.length][data[0].length];

        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[0].length; j++){
                dataBoxed[i][j] = data[i][j];
            }
        }
        lifeTable = new JTable(dataBoxed, columns);
        JScrollPane sp = new JScrollPane(lifeTable);
        this.add(sp);
    }

    public static void main(String[] args) throws JsonProcessingException {
        JFrame f = new JFrame();

        LifeTablePanel a = new LifeTablePanel();

        f.add(a);

        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
