package com.miniclip.galo.afonso.server.dto;

import com.miniclip.galo.afonso.server.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoveRequestDto {
    private Player player;
    private int row;
    private int col;
}
