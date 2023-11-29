package org.example;

import org.example.model.Ladder;
import org.example.model.LadderDirection;

import java.util.Arrays;

public class Board implements IBoard{

    private final int[] board = new int[61];

    public void createBoard(int upLadder, int downLadder) {
        createLadders(LadderDirection.UP, upLadder);
        createLadders(LadderDirection.DOWN, downLadder);
    }

    public void clearBoard() {
        for(int i = 0; i < board.length; i++) {
            board[i] = i;
        }
    }

    public int positionCheck(int position) {
        return board[position];
    }

    private void createLadders(LadderDirection direction, int ladderNumber) {
        while(ladderNumber > 0) {
            ladderNumber--;

            Ladder ladder = Ladder.of(direction);

            int ladderStart = ladder.getStart();
            int ladderEnd = ladder.getEnd();

            if(board[ladderStart] == 0 && board[ladderEnd] == 0) {
                board[ladderStart] = ladderEnd;
            }
        }
    }
}
