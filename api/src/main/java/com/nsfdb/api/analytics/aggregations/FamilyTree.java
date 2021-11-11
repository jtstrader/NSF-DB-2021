package com.nsfdb.api.analytics.aggregations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.nsfdb.api.models.*;
import com.nsfdb.api.analytics.RestClient;
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


/*
    The create method will build an array list of Monkeys in a for loop. Starting with a founder
    and then adding all of its children, with the addChain. Then it will move on to the next founder and repeat.
    It will do this process for all Founders and Monkeys.
 */
    public void create() throws JsonProcessingException, ExecutionException, InterruptedException {
        ArrayList<Founder> founders = mapper.readValue(client.get("/api/founder"),new TypeReference<ArrayList<Founder>>(){});

        for (Founder founder : founders) {
            MonkeyList.add(founderToMonkey(founder));
            addChain(founder.getTattoo());
        }
    }
/*
    The addChain method is a recursive method. The method builds an array list of the children for a mom.
    For each mom it checks to so if the there are any children if there is not it will break out of the method.
    If there are children then it will add them to the Main array List and then use the recursive call to check
    that Monkey/Child to see if they have any Children.
 */
    @Async
    void addChain(String behavior_mom) throws JsonProcessingException, ExecutionException, InterruptedException {
        System.out.println("-------------------------HERE-----------------------------");
        //Future<ArrayList<Monkey>> childrenAsync = mapper.readValue(client.get("/api/monkey/momAsync/" + behavior_mom),new TypeReference<Future<ArrayList<Monkey>>>(){});
        Future<String> AsyncPayload = new AsyncResult<String>(client.get("/api/monkey/momAsync/"+behavior_mom));
        System.out.println("-------------------------HERE NOW-----------------------------");
        if(AsyncPayload.isDone()) {
            System.out.println(AsyncPayload.get());
            ArrayList<Monkey> children = new ArrayList<Monkey>();
            if (children.size() == 0) {
                return;
            }

            for (Monkey child : children) {
                MonkeyList.add(child);
                addChain(child.getAnimal_id());
            }
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
        newMonkey.setSex('F');

        return newMonkey;
    }
}
