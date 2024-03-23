package com.miniclip.galo.afonso.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchIdDto {
    @JsonProperty("match_id")
    private Long matchId;
}
