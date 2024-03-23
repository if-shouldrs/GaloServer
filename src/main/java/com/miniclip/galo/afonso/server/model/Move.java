package com.miniclip.galo.afonso.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Move {
    private Player player;
    private int row;
    private int col;
}
