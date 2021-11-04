package com.nsfdb.analytics.FamilyTree;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.nsfdb.analytics.FamilyTree.*;
import com.nsfdb.api.*;

public class FamilyTree {
    private List<Monkey> MonkeyList;
    private RestClient client;
    private ObjectMapper mapper;

    public FamilyTree()
    {
        this.client = new RestClient("http://localhost:8080");
        this.mapper = new ObjectMapper();
    }

    public create(){
        private List<Founder> founders = mapper.readValue(client.get("api/founder"),new TypeReference<List<Founder>>(){});

        for(int i = 0, i < founders.size(), i++){
            MonkeyList.add(founderToMonkey(founders.get(i)));
            addChain(founders.get(i).tattoo);
        }

        return MonkeyList;
    }

    private addChain(string behavior_mom){
        private List<Monkey> children = mapper.readValue(client.get("api/monkey/mom/" + behavior_mom),new TypeReference<List<Monkey>>(){});
        if(children.size() == 0){
            break;
        }

        for(int i = 0; i < children.size(), i++){
            MonkeyList.add(children.get(i))
            addChain(children.get(i).animalid);
        }
    }

    private founderToMonkey(founder monk){
        Monkey newMonkey = new Monkey();

        newMonkey.animalID = monk.tattoo;
        newMonkey.birth_season = monk.birth_season;
        newMonkey.date_of_birth = monk.date_of_birth;
        newMonkey.pegidree = 1;
        newMonkey.sex = 'F';

        return newMonkey;
    }
}
