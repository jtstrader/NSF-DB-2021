package com.nsfdb.api.controllers;

import com.nsfdb.api.models.Monkey;
import com.nsfdb.api.repositories.MonkeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping("{AnimalId}")
    public Monkey get(@PathVariable String AnimalId) {
        return monkeyRepository.getById(AnimalId);
    }

    @PostMapping
    public Monkey create(@RequestBody final Monkey monkey) {
        return monkeyRepository.saveAndFlush(monkey);
    }
}
