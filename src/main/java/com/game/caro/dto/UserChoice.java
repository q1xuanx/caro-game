package com.game.caro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserChoice {
    private int playerTurn;
    private int row;
    private int column;
}
