package com.game.caro.enums;

import lombok.Getter;

@Getter
public enum GameStatus {
    INVALID_TURN(888, "This place have marked"),
    VALID_TURN(1, "Success"),
    PLAYER_1_WIN(2, "Player 1 win"),
    PLAYER_2_WIN(3, "Player 2 win"),
    PLAYER_1_TURN(444, "Player 1 turn"),
    PLAYER_2_TURN(555, "Player 2 turn"),;

    private final int value;
    private final String message;

    GameStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public String toString() {
        return "GameStatus{" +
                "value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
