package com.nsfdb.api.analytics.aggregations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import com.nsfdb.api.models.*;
import com.nsfdb.api.analytics.RestClient;
import org.apache.tomcat.jni.Time;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import javax.swing.tree.DefaultMutableTreeNode;

public class FamilyTree {
    private final ArrayList<FamilyTreeNode[]> RootList;
    private final RestClient client;
    private final ObjectMapper mapper;
    private FamilyTreeNode[] root;
    private ArrayList<Monkey> monkeyList;
    public int counter = 0;
    private int size;

    // n-tree
    /*public class FamilyTreeNode extends DefaultMutableTreeNode {
        Monkey monkey;
        FamilyTreeNode[] sibling;
        FamilyTreeNode[] child;

        public FamilyTreeNode(Monkey monkey) {
            this.monkey = monkey;
            sibling = new FamilyTreeNode[1];
            child = new FamilyTreeNode[1];
        }

        public Monkey getMonkey() { return monkey; }

        public FamilyTreeNode[] getSibling() { return sibling; }

        public FamilyTreeNode[] getChild() { return child; }
    }*/

    public FamilyTree() throws JsonProcessingException {
        this.client = new RestClient("http://localhost:8080");
        this.mapper = new ObjectMapper();
        this.RootList = new ArrayList<>();
        this.root = new FamilyTreeNode[1];
        this.monkeyList = mapper.readValue(client.get("/api/monkey"),new TypeReference<>() {});
    }

/*
    Return the generated monkey list to the user
*/
    public ArrayList<FamilyTreeNode[]> getMonkeyList() {
        return this.RootList;
    }

    private Callable<Void> toCallable(final Runnable runnable) {
        return () -> {
            runnable.run();
            return null;
        };
    }

/*
    The create method uses multithreading with the ExecutorService, Runnables, and Callables to
    generate every Family Tree at once. Generates threads based on the number of founds found
    in the first line of the function. Casts Runnables to Callables to be used by .invokeAll()
 */
    public void create() throws JsonProcessingException, InterruptedException {
        ArrayList<Founder> founders = mapper.readValue(client.get("/api/founder"), new TypeReference<>() {});
        //ArrayList<FamilyTreeNode> roots = new ArrayList<FamilyTreeNode>();
        for(Founder f: founders)
            RootList.add(new FamilyTreeNode[]{new FamilyTreeNode(founderToMonkey(f))});

        // create thread pool size of founders to generate all family trees at once
        // i.e. if there are 80 founds, we build 80 threads and build all 80 family trees
        // at one time
        ExecutorService executor = Executors.newFixedThreadPool(founders.size());
        Collection<Runnable> runnables = new ArrayList<>();
        for(int i = 0; i < founders.size(); i++) {
            int x = i;
            runnables.add(() -> {
                try {
                    addChain(RootList.get(x));
                } catch (JsonProcessingException | TimeoutException | ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // test is runnable list is populated with thread info
        Collection<Callable<Void>> callables = new ArrayList<>();
        for(Runnable r : runnables)
            callables.add(toCallable(r));

        List<Future<Void>> results = executor.invokeAll(callables);
    }

/*
    The addChain method is a recursive method. The method builds an array list of the children for a mom.
    For each mom it checks to so if the there are any children if there is not it will break out of the method.
    If there are children then it will add them to the Main array List and then use the recursive call to check
    that Monkey/Child to see if they have any Children.
 */

    void addChain(FamilyTreeNode[] ftn) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        counter++;

        //linear search to find all chidren of current monkey (kind of slow but faster than API calls)
        ArrayList<Monkey> children = new ArrayList<>();
        for(Monkey m: monkeyList) {
            String key = ftn[0].monkey.getAnimal_id(); //set the key that we are looking for to the animalId of the parent monkey
            key = key.replace("#","");  //remove #'s that are randomly at the end of animalId's but not on behavior mom's
            if(m.getBehavior_mom().equalsIgnoreCase(key)) { //add children to children list
                children.add(m);
            }
        }
        if (children.size() == 0)
            return;

        // 1. create new node
        // 2. set monkey information
        // 3. recursively get children
        // 4. recursively get siblings
        ftn[0].child[0] = new FamilyTreeNode(children.get(0));
        FamilyTreeNode[] temp = ftn[0].child;
        for (int i = 1; i < children.size(); i++) {
            temp[0].sibling[0]  = new FamilyTreeNode(children.get(i));
            temp = temp[0].sibling;
        }

        temp = ftn[0].child;
        while(temp[0] != null) {
            addChain(temp);
            temp = temp[0].sibling;
        }
    }

/*
    The founderToMonkey method adds the Founder monkey to the Monkey Class.
    In this method the pedigree is set to 1 and the gender is Female.
    Then information from the Founder's table to set to corresponding data in the Monkey Table.
    This is done so the founders can be included in the same list as the Monkeys
 */
    private Monkey founderToMonkey(Founder monk){
        Monkey newMonkey = new Monkey();

        newMonkey.setAnimal_id(monk.getTattoo());
        newMonkey.setBirth_season(monk.getBirth_season());
        newMonkey.setDate_of_birth(monk.getDate_of_birth());
        newMonkey.setPedigree(1);
        newMonkey.setSex("F");

        return newMonkey;
    }

    public void printTree() {
        for(FamilyTreeNode[] rt: RootList)
            printTree(rt);
    }
    private void printTree(FamilyTreeNode[] root) {
        System.out.println(root[0].monkey);
        if(root[0].child[0] != null)
            printTree(root[0].child);

        if(root[0].sibling[0] != null)
            printTree(root[0].sibling);
    }

    public int size() {
        size = 0;
        for(FamilyTreeNode[] rt: RootList)
            sizeRec(rt);
        return size;
    }

    private void sizeRec(FamilyTreeNode[] root) {
        size++;
        if(root[0].child[0] != null)
            sizeRec(root[0].child);

        if(root[0].sibling[0] != null)
            sizeRec(root[0].sibling);
    }



    public FamilyTreeNode treeify() {
        Monkey fakeMonkey = new Monkey();
        fakeMonkey.setAnimal_id("Monkeys");
        FamilyTreeNode top = new FamilyTreeNode(fakeMonkey);

        for (FamilyTreeNode[] familyTreeNodes : RootList) {
            FamilyTreeNode root = familyTreeNodes[0];
            jTreeifyNodes(root);
            top.add(root);
        }
        return top;
    }

    private void jTreeifyNodes(FamilyTreeNode root) {
        if(root.getChild()[0] != null) {
            root.add(root.getChild()[0]);
            jTreeifySiblings(root.getChild()[0], root);
            jTreeifyNodes(root.getChild()[0]);
        }
    }

    private void jTreeifySiblings(FamilyTreeNode root, FamilyTreeNode parent) {
        if(root.getSibling()[0] != null) {
            parent.add(root.getSibling()[0]);
            jTreeifyNodes(root.getSibling()[0]);
            jTreeifySiblings(root.getSibling()[0], parent);
        }
    }
}
