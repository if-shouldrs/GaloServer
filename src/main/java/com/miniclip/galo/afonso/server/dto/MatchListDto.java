package com.miniclip.galo.afonso.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MatchListDto {
    private List<MatchIdDto> matches;
}
