package com.nsfdb.analytics.FamilyTree;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.nsfdb.analytics.FamilyTree.*;
import com.nsfdb.api.models;

public class FamilyTree {
    private ArrayList<Monkey> MonkeyList;
    private RestClient client;
    private ObjectMapper mapper;

    public FamilyTree()
    {
        this.client = new RestClient("http://localhost:8080");
        this.mapper = new ObjectMapper();
        this.MonkeyList = new ArrayList<Monkey>();
    }
/*
    The create method will build an array list of Monkeys in a for loop. Starting with a founder
    and then adding all of its children, with the addChain. Then it will move on to the next founder and repeat.
    It will do this process for all Founders and Monkeys.
 */
    public ArrayList<Monkey> create(){
        private ArrayList<Founder> founders = mapper.readValue(client.get("api/founder"),new TypeReference<ArrayList<Founder>>(){});

        for(int i = 0, i < founders.size(), i++){
            MonkeyList.add(founderToMonkey(founders.get(i)));
            addChain(founders.get(i).tattoo);
        }

        return MonkeyList;
    }
/*
    The addChain method is an recursive method. The method builds an array list of the children for a mom.
    For each mom it checks to so if the there are any children if there is not it will break out of the method.
    If there are children then it will add them to the Main array List and then use the recursive call to check
    that Monkey/Child to see if they have any Children.
 */
    private void addChain(string behavior_mom){
        private ArrayList<Monkey> children = mapper.readValue(client.get("api/monkey/mom/" + behavior_mom),new TypeReference<ArrayList<Monkey>>(){});
        if(children.size() == 0){
            return;
        }

        for(int i = 0; i < children.size(), i++){
            MonkeyList.add(children.get(i))
            addChain(children.get(i).animalid);
        }
    }
/*
    The founderToMonkey method adds the Founder monkey to the Monkey Class.
    In this method the pegidree is set to 1 and the gender is Female.
    Then informations from the Founder's table to set to corrisponding data in the Monkey Table.
    This is done so the founders can be includes in the same list as the Monkeys
 */
    private Monkey founderToMonkey(founder monk){
        Monkey newMonkey = new Monkey();

        newMonkey.animalID = monk.tattoo;
        newMonkey.birth_season = monk.birth_season;
        newMonkey.date_of_birth = monk.date_of_birth;
        newMonkey.pegidree = 1;
        newMonkey.sex = 'F';

        return newMonkey;
    }
}
