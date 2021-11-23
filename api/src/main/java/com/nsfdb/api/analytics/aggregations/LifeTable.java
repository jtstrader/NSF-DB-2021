package com.nsfdb.api.analytics.aggregations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsfdb.api.analytics.RestClient;

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
        for(int i = 0; i < monkeyAges.size(); i++){
            // this is so the age is a whole number, based on the year. This is so we can get the total that died a spefic age based on years only
            tempPos = (int)Math.floor(monkeyAges.get(i).get(0));
            lifeTable[tempPos][1] += monkeyAges.get(i).get(1);
            totalMonkeys += monkeyAges.get(i).get(1); // The total number monkeys at that died
        }



            /*
            LifeTable Columns
            Age = 0
            Total = 1
            Deaths = 2
            Nx = 3
            mx = 4
            qx = 5
            px = 6
            Ix = 7
            dx = 8
            Lx = 9
            Tx = 10
            Ex = 11
            */

        //getting NX is getting the all of the subjects who lived through that age
        double NX = totalMonkeys;
        for (int i = 0; i < lifeTable.length; i++) {
            NX -= lifeTable[i][1];
            lifeTable[i][2] = NX;
        }

        //mx = Death/NX
            double mx;
            double nx;
            double d; // total number of monkeys that dead at a specific age
            for (int i = 0; i < lifeTable.length; i++) {
                nx = lifeTable[i][2];
                d = lifeTable[i][1];
                mx = d / nx;
                //Set the average to the last value in case of divide by zero
                if(mx == 0.0 || nx == 0.0)
                    mx = lifeTable[i-1][3];
                lifeTable[i][3] = mx; // puts values into table
            }

        //qx = 1 - exp(-mx)
        double qx;
        for (int i = 0; i < lifeTable.length; i++) {
            qx = 1 - exp(-lifeTable[i][3]);
            lifeTable[i][4] = qx; // puts values into table
        }

        //px = 1 - qx
        double px;
        for (int i = 0; i < lifeTable.length; i++) {
            px = 1 - lifeTable[i][4];
            lifeTable[i][5] = px; // puts values into table
        }

        //Proportion Surviving Nx/ Total
        double Ix = 1;
        for (int i = 0; i < lifeTable.length; i++) {
            if (i == 0)
                Ix = 1;
            else
                Ix = lifeTable[i - 1][2] / totalMonkeys;
            lifeTable[i][6] = Ix; // puts values into table
        }

            //Lx
            for (int i = 0; i < lifeTable.length - 1; i++) {
                lifeTable[i][7] = (lifeTable[i][2] + lifeTable[i+1][2])/2;
            }
            lifeTable[35][7] = lifeTable[35][2]/2;


        //getting Tx is getting the sum of the previous column starting from that row
        double Tx;
        for (int i = 0; i < lifeTable.length; i++) {
            Tx = 0;

            for (int x = i; x < lifeTable.length; x++)
                Tx += lifeTable[x][7];

            lifeTable[i][8] = Tx; // puts values into table
        }

        //Ex = Tx / Ix
        double Ex;
        for (int i = 0; i < lifeTable.length; i++) {
            if(lifeTable[i][2] != 0)
                Ex = lifeTable[i][8]/lifeTable[i][2];
            else
                Ex = 0;
            lifeTable[i][9] = Ex; // puts values into table
        }

        return lifeTable;
    }
//Prints the table for testing, and check the values.
    public void printLifeTable(double[][] lifeTable)
    {
        String columns [] = {"Age","Dead", "NX", "mx", "qx", "px", "Ix", "Lx", "Tx", "Ex"};
        for(int i = 0; i < columns.length; i++)
            System.out.print(columns[i] + "\t\t\t\t");

        System.out.println("");

        for(int i = 0; i < lifeTable.length; i++) {
            for (int x = 0; x < 9; x++) {
                System.out.print(lifeTable[i][x] + "\t\t\t");
            }
            System.out.println("");
        }
    }
}