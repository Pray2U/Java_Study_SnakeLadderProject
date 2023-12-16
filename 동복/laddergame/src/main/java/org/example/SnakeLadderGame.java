package org.example;


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

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }


    public void startTurn(int distance) {

        IPlayer curPlayer;
        IPlayer nextPlayer;

        if(currentPlayer.getTurn()) {
            curPlayer = currentPlayer;
            nextPlayer = opponentPlayer;
        }else {
            curPlayer = opponentPlayer;
            nextPlayer = currentPlayer;
        }

        if (curPlayer.getTurn()) {
            curPlayer.move(distance, board);

            if (curPlayer.isFinished()) {
                winner = curPlayer;
            }

            curPlayer.setTurn(false);
            nextPlayer.setTurn(true);
        }
    }

    public IPlayer getWinner() {
        return winner;
    }

    public boolean isCurrentPlayerTurn() {
        return currentPlayer.getTurn();
    }

    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public IPlayer getOpponentPlayer() {
        return opponentPlayer;
    }
}
