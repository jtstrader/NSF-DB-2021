package com.nsfdb.api.analytics.aggregations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.models.Monkey;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class FamilyTreeTest {

    public static void main(String[] args) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        Scanner in = new Scanner(System.in);
        System.out.println("Press Enter to start...");
        in.nextLine();
        FamilyTree myTree = new FamilyTree();
        myTree.create();
        Thread.sleep(15000);
        System.out.println("myTree.size(): " + myTree.getMonkeyList().size());
//        ArrayList<Monkey> monkeys = myTree.getMonkeyList();
    }
}
