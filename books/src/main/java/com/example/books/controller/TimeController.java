package com.example.crudmvc.controller;

import com.example.crudmvc.model.Competicao;
import com.example.crudmvc.model.Time;
import com.example.crudmvc.repository.CompeticaoRepository;
import com.example.crudmvc.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private CompeticaoRepository competicaoRepository;

    // 1. Listar todos os times
    @GetMapping
    public List<Time> listarTodos() {
        return timeRepository.findAll();
    }

    // 2. Obter um time por ID
    @GetMapping("/{id}")
    public ResponseEntity<Time> buscarPorId(@PathVariable Long id) {
        return timeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Criar um novo time
    @PostMapping
    public Time criar(@RequestBody Time time) {
        return timeRepository.save(time);
    }

    // 4. Atualizar um time existente
    @PutMapping("/{id}")
    public ResponseEntity<Time> atualizar(@PathVariable Long id, @RequestBody Time timeAtualizado) {
        return timeRepository.findById(id)
                .map(time -> {
                    time.setNome(timeAtualizado.getNome());
                    // Se quiser atualizar as competições, você pode fazer algo adicional aqui
                    return ResponseEntity.ok(timeRepository.save(time));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Deletar um time
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return timeRepository.findById(id)
                .map(time -> {
                    timeRepository.delete(time);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 6. Adicionar uma competição a um time
    @PostMapping("/{timeId}/competicoes/{competicaoId}")
    public ResponseEntity<Time> adicionarCompeticaoAoTime(@PathVariable Long timeId,
                                                          @PathVariable Long competicaoId) {
        Time time = timeRepository.findById(timeId)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));

        Competicao competicao = competicaoRepository.findById(competicaoId)
                .orElseThrow(() -> new RuntimeException("Competição não encontrada"));

        // Adiciona a competição ao set de competições do time
        time.getCompeticoes().add(competicao);
        // Adiciona o time ao set de times da competição (importante para manter ambos os lados sincronizados)
        competicao.getTimes().add(time);

        timeRepository.save(time);
        competicaoRepository.save(competicao);

        return ResponseEntity.ok(time);
    }
}
