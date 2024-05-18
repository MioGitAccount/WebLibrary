package com.example.demo.controllers;

import com.example.demo.Model.Worker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/worker")
public class WorkerController {
    @GetMapping
    public Worker getWorker(){
        return new Worker("Branki",28);

    }

}
