package com.miniclip.galo.afonso.server.controller;

import com.miniclip.galo.afonso.server.dto.MatchDto;
import com.miniclip.galo.afonso.server.dto.MatchIdDto;
import com.miniclip.galo.afonso.server.dto.MatchListDto;
import com.miniclip.galo.afonso.server.dto.MoveRequest;
import com.miniclip.galo.afonso.server.exception.InvalidMoveException;
import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.model.Move;
import com.miniclip.galo.afonso.server.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<MatchIdDto> createMatch() {
        Match match = matchService.createMatch();
        MatchIdDto matchIdDto = new MatchIdDto(match.getId());
        return new ResponseEntity<>(matchIdDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatchState(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        MatchDto matchDto = new MatchDto(
                match.getBoardState(),
                match.getStatus().name(),
                match.getTurn().getSymbol()
        );
        return ResponseEntity.ok(matchDto);
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<MatchDto> makeMove(@PathVariable Long id, @RequestBody MoveRequest request) {
        Move move = new Move(request.getPlayer(), request.getRow(), request.getCol());
        Match updatedMatch;
        try {
            updatedMatch = matchService.makeMove(id, move);
        } catch (InvalidMoveException e) {
            throw new InvalidMoveException(request);
        }
        MatchDto matchDto = new MatchDto(
                updatedMatch.getBoardState(),
                updatedMatch.getStatus().name(),
                updatedMatch.getTurn().getSymbol()
        );
        return ResponseEntity.ok(matchDto);
    }

    @GetMapping
    public ResponseEntity<MatchListDto> listMatches() {
        List<Match> matches = matchService.listAllMatches();
        List<MatchIdDto> ids = matches.stream()
                .map(match -> new MatchIdDto(match.getId()))
                .toList();
        MatchListDto matchListDto = new MatchListDto(ids);
        return ResponseEntity.ok(matchListDto);
    }

}
