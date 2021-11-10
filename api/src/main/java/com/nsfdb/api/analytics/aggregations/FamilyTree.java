package com.nsfdb.api.analytics.aggregations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

import com.nsfdb.api.models.*;
import com.nsfdb.api.analytics.RestClient;

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
    public void create() throws JsonProcessingException {
        ArrayList<Founder> founders = mapper.readValue(client.get("/api/founder"),new TypeReference<ArrayList<Founder>>(){});

        for (Founder founder : founders) {
            MonkeyList.add(founderToMonkey(founder));
            addChain(founder.tattoo);
        }
    }
/*
    The addChain method is a recursive method. The method builds an array list of the children for a mom.
    For each mom it checks to so if the there are any children if there is not it will break out of the method.
    If there are children then it will add them to the Main array List and then use the recursive call to check
    that Monkey/Child to see if they have any Children.
 */
    private void addChain(String behavior_mom) throws JsonProcessingException {
        ArrayList<Monkey> children = mapper.readValue(client.get("/api/monkey/mom/" + behavior_mom),new TypeReference<ArrayList<Monkey>>(){});
        if(children.size() == 0){
            return;
        }

        for (Monkey child : children) {
            MonkeyList.add(child);
            addChain(child.animal_id);
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

        newMonkey.animal_id = monk.tattoo;
        newMonkey.birth_season = monk.birth_season;
        newMonkey.date_of_birth = monk.date_of_birth;
        newMonkey.pedigree = 1;
        newMonkey.sex = 'F';

        return newMonkey;
    }
}
