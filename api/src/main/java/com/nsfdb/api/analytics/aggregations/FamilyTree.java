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
    public int counter = 0;

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

    public FamilyTree()
    {
        this.client = new RestClient("http://localhost:8080");
        this.mapper = new ObjectMapper();
        this.RootList = new ArrayList<FamilyTreeNode[]>();
        this.root = new FamilyTreeNode[1];
    }

/*
    Return the generated monkey list to the user
*/
    public ArrayList<FamilyTreeNode[]> getMonkeyList() {
        return this.RootList;
    }

    private Callable<Void> toCallable(final Runnable runnable) {
        return new Callable<Void>() {
            @Override
            public Void call() {
                runnable.run();
                return null;
            }
        };
    }

/*
    The create method uses multithreading with the ExecutorService, Runnables, and Callables to
    generate every Family Tree at once. Generates threads based on the number of founds found
    in the first line of the function. Casts Runnables to Callables to be used by .invokeAll()
 */
    public void create() throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        ArrayList<Founder> founders = mapper.readValue(client.get("/api/founder"),new TypeReference<ArrayList<Founder>>(){});
        //ArrayList<FamilyTreeNode> roots = new ArrayList<FamilyTreeNode>();
        for(Founder f: founders)
        {
            //root[0] = new FamilyTreeNode(founderToMonkey(f));
            RootList.add(new FamilyTreeNode[]{new FamilyTreeNode(founderToMonkey(f))});
            //for(FamilyTreeNode[] n: RootList)
            //    System.out.println(n[0].monkey);
        }

        /*
        for(FamilyTreeNode ftn : RootList)
        {
            System.out.println(ftn.monkey);
        }
        */

        ExecutorService executor = Executors.newFixedThreadPool(founders.size());
        Collection<Runnable> runnables = new ArrayList<Runnable>();
        for(int i = 0; i < founders.size(); i++) {
            int x = i;
            runnables.add(() -> {
                try {
                    addChain(RootList.get(x));
                    System.out.println("DONE WITH THIS ROOT");
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            });
        }

        // test is runnable list is populated with thread info
        //System.out.println("ArrayList<Runnable>.size(): " + runnables.size());

        Collection<Callable<Void>> callables = new ArrayList<Callable<Void>>();
        for(Runnable r : runnables)
            callables.add(toCallable(r));

        List<Future<Void>> results = executor.invokeAll(callables);

        //Monkey mon  = new Monkey();
        //mon.setAnimal_id("2I3");
        //root[0] = new FamilyTreeNode(mon);
        //addChain(root);
    }

/*
    The addChain method is a recursive method. The method builds an array list of the children for a mom.
    For each mom it checks to so if the there are any children if there is not it will break out of the method.
    If there are children then it will add them to the Main array List and then use the recursive call to check
    that Monkey/Child to see if they have any Children.
 */

    void addChain(FamilyTreeNode[] ftn) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        counter++;
        //System.out.println(counter);
        //client.getAsync("/api/monkey/mom/"+behavior_mom)
        //    .subscribe(monkey -> this.MonkeyList.add(monkey));
        //System.out.println("==============IM HERE=============");

        ArrayList<Monkey> children = mapper.readValue(client.get("/api/monkey/mom/" + ftn[0].monkey.getAnimal_id()), new TypeReference<ArrayList<Monkey>>(){});
        if (children.size() == 0) {
            return;
        }

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

    void addChain_noAsync(String behavior_mom) throws JsonProcessingException {
        System.out.println("-------------------------HERE-----------------------------");
        String payload = client.get("/api/monkey/mom/"+behavior_mom);
        System.out.println("-------------------------HERE NOW-----------------------------");
        System.out.println(payload);
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
        newMonkey.setSex('F');

        return newMonkey;
    }

    public void printTree() {
        for(FamilyTreeNode[] rt: RootList) {
            printTree(rt);
            //System.out.println(rt[0].monkey);
        }
    }
    private void printTree(FamilyTreeNode[] root) {
        System.out.println(root[0].monkey);
        if(root[0].child[0] != null)
            printTree(root[0].child);

        if(root[0].sibling[0] != null)
            printTree(root[0].sibling);
        return;
    }
}
