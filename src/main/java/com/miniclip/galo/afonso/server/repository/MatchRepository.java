package com.miniclip.galo.afonso.server.repository;

import com.miniclip.galo.afonso.server.model.Match;
import com.miniclip.galo.afonso.server.model.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByStatus(MatchStatus status);

}
