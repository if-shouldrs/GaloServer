package com.miniclip.galo.afonso.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchDto {
    private String board;
    private String status;
    private char turn;
}
