package org.example;

public interface IBoard {
    void createBoard(int upLadder, int downLadder);
    void clearBoard();

    int positionCheck(int position);
    int[] getBoardInfo();
}
