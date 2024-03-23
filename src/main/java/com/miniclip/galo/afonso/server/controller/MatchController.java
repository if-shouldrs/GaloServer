package com.miniclip.galo.afonso.server.controller;

import com.miniclip.galo.afonso.server.dto.MoveRequest;
import com.miniclip.galo.afonso.server.exception.InvalidMoveException;
import com.miniclip.galo.afonso.server.exception.MatchNotFoundException;
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
    public ResponseEntity<Map<String, Object>> makeMove(@PathVariable Long id, @RequestBody MoveRequest moveRequest) {
        Match updatedMatch = matchService.makeMove(id, moveRequest);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", updatedMatch.getStatus());
        responseBody.put("board", updatedMatch.getBoardState());
        responseBody.put("turn", updatedMatch.getTurn());
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping
    public ResponseEntity<List<Long>> listMatches() {
        List<Long> matchIds = matchService.listAllMatchIds();
        return ResponseEntity.ok(matchIds);
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<Object> handleMatchNotFoundException(MatchNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidMoveException.class)
    public ResponseEntity<Object> handleInvalidMoveException(InvalidMoveException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
