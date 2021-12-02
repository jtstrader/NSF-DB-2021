package com.nsfdb.api.analytics.aggregations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsfdb.api.analytics.RestClient;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

import static java.lang.Math.exp;



public class LifeTable {
    private double[][] lifeTable;
    private String[] columns;
    private final RestClient client;
    private final ObjectMapper mapper;

    // Calls on the datebase for the monkeys death
    public LifeTable() throws JsonProcessingException {
        this.client = new RestClient("http://localhost:8080");
        this.mapper = new ObjectMapper();

        columns = new String[]{ "Age", "Died", "Nx", "Mx", "Qx", "Px", "Ix", "Lx", "Tx", "Ex"};

        lifeTable = createLifeTable("/api/monkey/deathAges");
    }

    //returns a 2 dimension array
    //designed for the average life span of the monkeys.
    public double[][] getLifeTableData() {
        return lifeTable;
    }

    //returns all the columns used for the array
    public String[] getColumns() {
        return columns;
    }
//Creates the table with the finds the values
    private double[][] createLifeTable(String table) throws JsonProcessingException {
        // there are 36 rows for the ages in the array
        // While last monkeys dies at age 31 the extra rows are for any potential outliers
        //there are 10 colomns for any extra date that need to be found. currently only 9 are being used
        double[][] lifeTable = new double[36][10];
        List<List<Double>> monkeyAges = mapper.readValue(client.get(table), new TypeReference<List<List<Double>>>(){});
// builds and labels all the rows.
        for(int i = 0; i < 36; i++){
            lifeTable[i][0] = i;
        }
// Gets all the monkeys that died at that age
        int tempPos = -1;
        double totalMonkeys = 0;
        lifeTable[0][1] = 0;
        for(int i = 0; i < monkeyAges.size(); i++){
            // this is so the age is a whole number, based on the year. This is so we can get the total that died a specific age based on years only
            tempPos = (int)Math.floor(monkeyAges.get(i).get(0));
            lifeTable[tempPos+1][1] += monkeyAges.get(i).get(1); //adds to number of monkeys that will die by age tempPos + 1. THIS IS DX
            totalMonkeys += monkeyAges.get(i).get(1); // The total number monkeys at that died
        }

            /*
            LifeTable Columns
            Age = 0
            DX = 1
            NX = 2
            IX = 3
            QX = 4
            PX = 5
            LX = 6
            TX = 7
            EX = 8
            */

        System.out.println("Calculating NX");
        //getting NX is getting the all of the subjects who lived through that age
        double NX = totalMonkeys;
        for (int i = 0; i < lifeTable.length; i++) {
            NX -= lifeTable[i][1];
            lifeTable[i][2] = NX;
        }

        //calc ix aka proportion surviving
        //ix = nx / total monkeys
        for(int i = 0; i < lifeTable.length; i++) {
            lifeTable[i][3] = lifeTable[i][2] / lifeTable[0][2];
        }

        //calc qx aka mortality rate
        //qx = dx/nx
        //dx is stored at index 1
        for(int i = 0; i < lifeTable.length; i++) {
            if(i < lifeTable.length-1)
                lifeTable[i][4] = lifeTable[i+1][1]/lifeTable[i][2]; // puts values into table
            else
                lifeTable[i][4] = 1; // puts values into table, default is 1 because all of the last monkeys have died
        }

        //calc px aka survivability rate
        //px = 1 - qx, inverse of mortality rate
        for(int i = 0; i < lifeTable.length; i++) {
            lifeTable[i][5] = 1 - lifeTable[i][4]; // puts values into table
        }

        //calc lx aka average number alive in class
        //lx = (lx + lx-1) / 2
        for(int i = 0; i < lifeTable.length; i++) {
            if(i < 35)
                lifeTable[i][6] = (lifeTable[i][2] + lifeTable[i+1][2])/2.0; // puts values into table
            else
                lifeTable[i][6] = lifeTable[i][2]/2.0; // puts values into table
        }

        //calc tx aka sum of prev lx's
        //gets sum of all items in column(index) 6 from i to lifetable.length
        double tx;
        for(int i = 0; i < lifeTable.length; i++) {
            tx = 0;
            for(int j = i; j < lifeTable.length; j++) {
                tx += lifeTable[j][6];
            }

            lifeTable[i][7] = tx; // puts values into table
        }

        //calc ex aka life expectancy
        //ex = tx/nx
        for(int i = 0; i < lifeTable.length; i++) {
            if(lifeTable[i][2] != 0) //handling divide by zero
                lifeTable[i][8] = lifeTable[i][7] / lifeTable[i][2]; // puts values into table
            else
                lifeTable[i][8] = 0; //default is zero years
        }
        return lifeTable;
    }
//Prints the table for testing, and check the values.
    public void printLifeTable(double[][] lifeTable)
    {
        /*
        String columns [] = {"Age","Dead", "NX", "mx", "qx", "px", "Ix", "Lx", "Tx", "Ex"};
        for(int i = 0; i < columns.length; i++)
            System.out.print(columns[i] + "\t\t\t\t");

        System.out.println("");

        for(int i = 0; i < lifeTable.length; i++) {
            for (int x = 0; x < 9; x++) {
                System.out.print(lifeTable[i][x] + "\t\t\t\t");
            }
            System.out.println("");
        }
        */
        DecimalFormat df = new DecimalFormat("###.###");
        System.out.println("Age Class \t\t dx \t\t\t\t NX \t\t\t ix \t\t\t\t qx \t\t\t\t  px \t\t\t\t lx \t\t\t\t tx \t\t\t\t ex");
        for(int i = 0; i < lifeTable.length; i++) {
            if(lifeTable[i][2] == 0)
                break;
            for (int x = 0; x < 9; x++) {
                if(x != 1)
                    System.out.print(df.format(lifeTable[i][x]) + "\t\t\t\t");
                else
                    System.out.print(df.format(lifeTable[i+1][x]) + "\t\t\t\t");
            }
            System.out.println("");
        }
    }
}