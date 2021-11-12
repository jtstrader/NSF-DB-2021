package com.nsfdb.api.controllers;

import com.nsfdb.api.models.Monkey;
import com.nsfdb.api.repositories.MonkeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/monkey")
public class MonkeyController {
    @Autowired
    private MonkeyRepository monkeyRepository;

    @GetMapping
    public List<Monkey> list() {
        return monkeyRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{sequence_id}")
    public Monkey get(@PathVariable Integer sequence_id) {
        return monkeyRepository.getById(sequence_id);
    }

    @GetMapping
    @RequestMapping("mom/{behavior_mom}")
    public List<Monkey> getChildren(@PathVariable String behavior_mom) { return monkeyRepository.getChildren(behavior_mom); }

    @PostMapping
    public Monkey create(@RequestBody final Monkey monkey) {
        return monkeyRepository.saveAndFlush(monkey);
    }
}
