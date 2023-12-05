package org.example;

import org.example.model.Dice;

public class SnakeLadderGame {

    private Board board = new Board();
    private IPlayer currentPlayer;
    private IPlayer opponentPlayer;
    IPlayer winner;

    public void setCurrentPlayer(IPlayer player) {
        this.currentPlayer = player;
    }

    public void setOpponentPlayer(IPlayer player) {
        this.opponentPlayer = player;
    }

    public void startGame() {
        currentPlayer.initialize();
        opponentPlayer.initialize();
        board.clearBoard();
        board.createBoard(2, 3);
        winner = null;
    }

    public Board getBoard() {
        return board;
    }

    public void startTurn(boolean myTurn) {

        IPlayer curPlayer;
        IPlayer nextPlayer;

        if(myTurn) {
            curPlayer = currentPlayer;
            nextPlayer = opponentPlayer;
        }else {
            curPlayer = opponentPlayer;
            nextPlayer = currentPlayer;
        }

        if (curPlayer.getTurn()) {
            int distance = Dice.roll().getNumber();
            curPlayer.move(distance, board);

            if (curPlayer.isFinished()) {
                winner = curPlayer;
            }

            curPlayer.setTurn(false);
            nextPlayer.setTurn(true);
        }
    }

}
