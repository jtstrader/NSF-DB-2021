package com.nsfdb.api.analytics.aggregations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nsfdb.api.models.Monkey;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class FamilyTreeTest {

    public static void main(String[] args) throws JsonProcessingException, ExecutionException, InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("Press Enter to start...");
        in.nextLine();
        FamilyTree myTree = new FamilyTree();
        myTree.create();
        ArrayList<Monkey> monkeys = myTree.getMonkeyList();

        //for(Monkey m: monkeys)
        //{
        //    System.out.println(m.getPedigree());
        //}
    }
}
