package com.nsfdb.api.analytics.aggregations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.models.Monkey;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class FamilyTreeTest {

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("Press Enter to start...");
        in.nextLine();

        // family tree testing
        FamilyTree myTree = new FamilyTree();
        myTree.create();
        myTree.printTree();

        // life table testing
//        LifeTable myTable = new LifeTable();
//        myTable.printLifeTable(myTable.getLifeTableData());
    }
}
