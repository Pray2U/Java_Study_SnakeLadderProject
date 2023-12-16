package org.example;

import org.example.model.Ladder;
import org.example.model.LadderDirection;

import java.util.Arrays;

public class Board implements IBoard{

    private int[] board = new int[61];

    public Board() {
    }

    public Board(int[] board) {
        this.board = board;
    }

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

    public int[] getBoardInfo() {
        return this.board;
    }

    private void createLadders(LadderDirection direction, int ladderNumber) {
        while(ladderNumber > 0) {
            ladderNumber--;

            Ladder ladder = Ladder.of(direction);

            int ladderStart = ladder.getStart();
            int ladderEnd = ladder.getEnd();

            if(board[ladderStart] == ladderStart && board[ladderEnd] == ladderEnd) {
                board[ladderStart] = ladderEnd;
            }else {
                ladderNumber++;
            }
        }
    }
}
