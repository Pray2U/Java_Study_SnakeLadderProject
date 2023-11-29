package org.example;

import org.example.model.Dice;

import java.util.ArrayList;
import java.util.List;

public class SnakeLadderGame {

    private Board board = new Board();
    private List<IPlayer> players =  new ArrayList<>();
    private Dice dice = new Dice(1, 6);
    IPlayer winner;

    public void startGame() {
        initializeGame();

        while (winner == null) {
            startTurn();
        }
    }

    public void joinPlayer(IPlayer player) {
        players.add(player);
    }

    private void initializeGame() {
        players.forEach(IPlayer::initialize);
        board.clearBoard();
        board.createBoard(2, 3);
        winner = null;
    }

    private void startTurn() {
        for(IPlayer player : players) {
            int distance = dice.roll();
            player.move(distance, board);
            if(player.isFinished()) {
                winner = player;
            }
        }
    }

}
