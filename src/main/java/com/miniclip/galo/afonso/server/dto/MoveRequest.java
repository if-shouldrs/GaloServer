package com.miniclip.galo.afonso.server.dto;

import lombok.Data;

@Data
public class MoveRequest {
    private String player;
    private int row;
    private int col;
}
