package com.miniclip.galo.afonso.server.dto;

import com.miniclip.galo.afonso.server.model.Player;
import lombok.Data;

@Data
public class MoveRequest {
    private Player player;
    private int row;
    private int col;
}
