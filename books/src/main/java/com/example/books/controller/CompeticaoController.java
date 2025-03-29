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
@RequestMapping("/competicoes")
public class CompeticaoController {

    @Autowired
    private CompeticaoRepository competicaoRepository;

    @Autowired
    private TimeRepository timeRepository;

    // 1. Listar todas as competições
    @GetMapping
    public List<Competicao> listarTodas() {
        return competicaoRepository.findAll();
    }

    // 2. Obter uma competição por ID
    @GetMapping("/{id}")
    public ResponseEntity<Competicao> buscarPorId(@PathVariable Long id) {
        return competicaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Criar uma nova competição
    @PostMapping
    public Competicao criar(@RequestBody Competicao competicao) {
        return competicaoRepository.save(competicao);
    }

    // 4. Atualizar uma competição existente
    @PutMapping("/{id}")
    public ResponseEntity<Competicao> atualizar(@PathVariable Long id,
                                                @RequestBody Competicao competicaoAtualizada) {
        return competicaoRepository.findById(id)
                .map(competicao -> {
                    competicao.setNome(competicaoAtualizada.getNome());
                    return ResponseEntity.ok(competicaoRepository.save(competicao));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Deletar uma competição
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return competicaoRepository.findById(id)
                .map(competicao -> {
                    competicaoRepository.delete(competicao);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 6. Adicionar um time a uma competição
    @PostMapping("/{competicaoId}/times/{timeId}")
    public ResponseEntity<Competicao> adicionarTimeACompeticao(@PathVariable Long competicaoId,
                                                               @PathVariable Long timeId) {
        Competicao competicao = competicaoRepository.findById(competicaoId)
                .orElseThrow(() -> new RuntimeException("Competição não encontrada"));

        Time time = timeRepository.findById(timeId)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));

        // Adiciona o time ao set de times da competicao
        competicao.getTimes().add(time);
        // Adiciona a competicao ao set de competicoes do time
        time.getCompeticoes().add(competicao);

        competicaoRepository.save(competicao);
        timeRepository.save(time);

        return ResponseEntity.ok(competicao);
    }
}
