package com.example.times.e.campeonatos.controller;

import com.example.times.e.campeonatos.model.Time;
import com.example.times.e.campeonatos.service.TimeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping
    public List<Time> listarTimes() {
        return timeService.listarTimes();
    }

    @PostMapping
    public Time criarTime(@RequestBody Time time) {
        return timeService.salvarTime(time);
    }
}