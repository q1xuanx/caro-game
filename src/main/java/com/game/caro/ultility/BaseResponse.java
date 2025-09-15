package com.game.caro.ultility;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BaseResponse {
    private int status;
    private String message;
    private int playerTurn; // 1 = A, 2 = B
}
