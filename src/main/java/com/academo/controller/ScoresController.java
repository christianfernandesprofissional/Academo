package com.academo.controller;

import com.academo.model.Scores;
import com.academo.service.scores.ScoresServicempl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoresController {
    private ScoresServicempl scoresServicempl;
//Listar todas as entidades
    @GetMapping
    public ResponseEntity<List<Scores>> getScores(){}

//Buscar entidade por ID
    @GetMapping("/{id}")
    public ResponseEntity<Scores> searchEntityById (@PathVariable Integer id) {}

//Criar nova entidade
    @PostMapping
    public ResponseEntity<Scores> createScore(@RequestBody Scores scores){}

//Atualizar entidade existente
    @PutMapping("{id}")
    public ResponseEntity<Scores> updateScore(@PathVariable Integer id, @RequestBody Scores scores){}

//Remover entidade por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Integer id){}
}
