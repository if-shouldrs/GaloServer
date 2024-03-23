package com.miniclip.galo.afonso.server.controller;

import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<Long> createMatch() {
        Match match = matchService.createMatch();
        return new ResponseEntity<>(match.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMatchState(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        return ResponseEntity.ok(match.getGameState());
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<String> makeMove(@PathVariable Long id, @RequestBody String move) {
        Match updatedMatch = matchService.makeMove(id, move);
        return ResponseEntity.ok(updatedMatch.getGameState());
    }

    @GetMapping
    public ResponseEntity<List<Long>> listMatches() {
        List<Long> matchIds = matchService.listAllMatchIds();
        return ResponseEntity.ok(matchIds);
    }

}
