package com.example.times.e.campeonatos.service;

import com.example.times.e.campeonatos.model.Time;
import com.example.times.e.campeonatos.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {
    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<Time> listarTimes() {
        return timeRepository.findAll();
    }

    public Time salvarTime(Time time) {
        return timeRepository.save(time);
    }
}