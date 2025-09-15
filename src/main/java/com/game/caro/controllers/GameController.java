package com.game.caro.controllers;

import com.game.caro.dto.UserChoice;
import com.game.caro.enums.GameStatus;
import com.game.caro.services.GameService;
import com.game.caro.ultility.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GameController {

    private String message = "Hello";
    private final GameService gameService;

    @GetMapping("/health-check")
    public String healthCheck(@RequestParam(required = false, defaultValue = "") String message) {
        if (!message.isEmpty()) {
            this.message = message;
        }
        log.info("Health Check Message: {}", this.message);
        return "I alive, your message: " + this.message;
    }


    @PostMapping("/play")
    public ResponseEntity<BaseResponse> playCaro(@RequestBody UserChoice userChoice) {
        GameStatus status = gameService.playerChoice(userChoice);
        if (status == GameStatus.INVALID_TURN) {
            return ResponseEntity.ok(new BaseResponse(status.getValue(), status.getMessage(), userChoice.getPlayerTurn()));
        }
        int turnPlayer = 1;
        if (status == GameStatus.PLAYER_2_TURN) {
            turnPlayer = 2;
        }
        return ResponseEntity.ok(new BaseResponse(status.getValue(), status.getMessage(), turnPlayer));
    }
}
