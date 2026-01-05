package com.game.caro.services;

import com.game.caro.dto.UserChoice;
import com.game.caro.enums.GameStatus;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final int [][] board = new int[][]{
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
    };

    private int checkByRows(int [][]board, int row, int column, int totalTraversal, boolean [] visited, int player) {
        if (totalTraversal == 0 || column == board.length || column < 0 || visited[column]) {
            return 0;
        }
        visited[column] = true;
        int currentMark = board[row][column] == player ? 1 : 0;
        totalTraversal--;
        currentMark += checkByRows(board, row, column + 1, totalTraversal, visited, player);
        currentMark += checkByRows(board, row, column - 1, totalTraversal, visited, player);
        return currentMark;
    }

    private int checkByColumn(int [][] board, int row, int column, int totalTraversal, boolean[] visited, int player) {
        if (totalTraversal == 0 || row == board.length || row < 0 || visited[row]) {
            return 0;
        }
        visited[row] = true;
        int currentMark = board[row][column] == player ? 1 : 0;
        totalTraversal--;
        currentMark += checkByColumn(board, row + 1, column, totalTraversal, visited, player);
        currentMark += checkByColumn(board, row - 1, column, totalTraversal, visited, player);
        return currentMark;
    }

    private int checkCross(int [][] board, int row, int column, int totalTraversal, boolean[] visited, int player) {
        if (totalTraversal == 0 || row == board.length || row < 0 || column == board.length || column < 0 || (visited[column] && visited[row])) {
            return 0;
        }
        visited[row] = true;
        visited[column] = true;
        int currentMark = board[row][column] == player ? 1 : 0;
        totalTraversal--;
        currentMark += checkCross(board, row + 1, column + 1, totalTraversal, visited, player);
        currentMark += checkCross(board, row - 1, column - 1, totalTraversal, visited, player);
        return currentMark;
    }

    private void refillBoard() {
        int n = board.length, m = board[0].length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                board[i][j] = 0;
            }
        }
    }

    public GameStatus playerChoice(UserChoice userChoice) {
        // Define constant variable
        final int PLAYER_1 = 1;
        final int PLAYER_2 = 2;
        final int TOTAL_TRAVERSAL = 5;

        // Validate when user choose the wrong move
        if (board[userChoice.getRow()][userChoice.getColumn()] != 0) {
            return GameStatus.INVALID_TURN;
        }
        board[userChoice.getRow()][userChoice.getColumn()] = userChoice.getPlayerTurn() == 1 ? PLAYER_1 : PLAYER_2;

        int currentPlayer = userChoice.getPlayerTurn() == 1 ? PLAYER_1 : PLAYER_2;
        int rowMarked = checkByRows(board, userChoice.getRow(), userChoice.getColumn(), TOTAL_TRAVERSAL, new boolean[board.length], currentPlayer);
        int columMark = checkByColumn(board, userChoice.getRow(), userChoice.getColumn(), TOTAL_TRAVERSAL, new boolean[board.length], currentPlayer);
        int crossMark = checkCross(board, userChoice.getRow(), userChoice.getColumn(), TOTAL_TRAVERSAL, new boolean[board.length], currentPlayer);

        if (rowMarked == TOTAL_TRAVERSAL || columMark == TOTAL_TRAVERSAL || crossMark == TOTAL_TRAVERSAL) {
            refillBoard();
            return userChoice.getPlayerTurn() == 1 ? GameStatus.PLAYER_1_WIN : GameStatus.PLAYER_2_WIN;
        }

        return userChoice.getPlayerTurn() == 1 ? GameStatus.PLAYER_2_TURN : GameStatus.PLAYER_1_TURN;
    }

}
