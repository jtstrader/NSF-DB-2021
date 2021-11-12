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

public class FamilyTree {
    private final ArrayList<Monkey> MonkeyList;
    private final RestClient client;
    private final ObjectMapper mapper;

    /*
    // potential fix: sibling/child tree
    class FamilyTreeNode {
        Monkey monkey;
        FamilyTreeNode sibling;
        FamilyTree child;
    }
    */

    public FamilyTree()
    {
        this.client = new RestClient("http://localhost:8080");
        this.mapper = new ObjectMapper();
        this.MonkeyList = new ArrayList<Monkey>();
    }
/*
    Return the generated monkey list to the user
*/
    public ArrayList<Monkey> getMonkeyList() {
        return this.MonkeyList;
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
        ExecutorService executor = Executors.newFixedThreadPool(founders.size());
        Collection<Runnable> runnables = new ArrayList<Runnable>();
        for(int i = 0; i < founders.size(); i++) {
            int x = i;
            runnables.add(() -> {
                try {
                    MonkeyList.add(founderToMonkey(founders.get(x)));
                    addChain(founders.get(x).getTattoo());
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
        System.out.println("ArrayList<Runnable>.size(): " + runnables.size());

        Collection<Callable<Void>> callables = new ArrayList<Callable<Void>>();
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

    void addChain(String behavior_mom) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        client.getAsync("/api/monkey/mom/"+behavior_mom)
            .subscribe(monkey -> this.MonkeyList.add(monkey));
        ArrayList<Monkey> children = new ArrayList<Monkey>();
        if (children.size() == 0) {
            return;
        }

        for (Monkey child : children) {
            MonkeyList.add(child);
            addChain(child.getAnimal_id());
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
}
