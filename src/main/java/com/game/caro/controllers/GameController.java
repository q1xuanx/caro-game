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

    private final GameService gameService;

    @GetMapping("/health-check")
    public String healthCheck(@RequestParam(required = false, defaultValue = "") String userMessage) {
        final String message = userMessage == null ? "Hello this is from server!" : userMessage;
        log.info("Health Check Message: {}", message);
        return "I alive, your message: " + message;
    }


    @PostMapping("/play")
    public ResponseEntity<BaseResponse> playCaro(@RequestBody UserChoice userChoice) {
        GameStatus status = gameService.playerChoice(userChoice);
        log.info("Play Caro Message: {}", status);
        if (status == GameStatus.INVALID_TURN) {
            return ResponseEntity.ok(new BaseResponse(status.getValue(), status.getMessage(), userChoice.getPlayerTurn()));
        }
        int turnPlayer = status == GameStatus.PLAYER_2_TURN ? 2 : 1;
        log.info("Play Caro Turn Message: {}", turnPlayer);
        return ResponseEntity.ok(new BaseResponse(status.getValue(), status.getMessage(), turnPlayer));
    }
}
