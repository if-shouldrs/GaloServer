package com.miniclip.galo.afonso.server.controller;

import com.miniclip.galo.afonso.server.dto.MatchIdDto;
import com.miniclip.galo.afonso.server.dto.MoveRequest;
import com.miniclip.galo.afonso.server.exception.InvalidMoveException;
import com.miniclip.galo.afonso.server.exception.MatchNotFoundException;
import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> createMatch() {
        Match match = matchService.createMatch();
        Map<String, Long> responseBody = Collections.singletonMap("match_id", match.getId());
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getMatchState(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", match.getStatus());
        responseBody.put("board", match.getBoardState());
        responseBody.put("turn", match.getTurn());
        return ResponseEntity.ok(responseBody);
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
    public ResponseEntity<Map<String, List<MatchIdDto>>> listMatches() {
        List<Match> matches = matchService.listAllMatches();
        List<MatchIdDto> ids = matches.stream()
                .map(match -> new MatchIdDto(match.getId()))
                .toList();
        Map<String, List<MatchIdDto>> responseBody = Collections.singletonMap("matches", ids);
        return ResponseEntity.ok(responseBody);
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
