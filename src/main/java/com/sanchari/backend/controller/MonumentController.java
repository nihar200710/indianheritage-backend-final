package com.sanchari.backend.controller;

import com.sanchari.backend.model.Monument;
import com.sanchari.backend.repository.MonumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monuments")
public class MonumentController {

    @Autowired
    private MonumentRepository monumentRepository;

    @GetMapping
    public List<Monument> getAllMonuments() {
        return monumentRepository.findAllByOrderByIdDesc();
    }

    @PostMapping
    public Monument createMonument(@RequestBody Monument monument) {
        return monumentRepository.save(monument);
    }
}
