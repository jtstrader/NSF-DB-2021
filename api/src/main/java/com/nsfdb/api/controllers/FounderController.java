package com.nsfdb.api.controllers;

import com.nsfdb.api.models.Founder;
import com.nsfdb.api.repositories.FounderRepository;
import com.nsfdb.api.repositories.MonkeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/founder")
public class FounderController {
    @Autowired
    private FounderRepository founderRepository;

    @GetMapping
    public List<Founder> list() { return founderRepository.findAll(); }

    @GetMapping
    @RequestMapping("{m_code}")
    public Founder get(@PathVariable String m_code){ return founderRepository.getById(m_code); }

    @PostMapping
    public Founder create(@RequestBody final Founder founder) { return founderRepository.saveAndFlush(founder); }

}
