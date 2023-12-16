package org.example;

public class Player implements IPlayer {
    private boolean turn;
    private int position;
    private String nickname;

    public Player(String nickname, boolean turn) {
        this.nickname = nickname;
        this.turn = turn;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getNickname() {
        return nickname;
    }

    public void move(int distance, IBoard board) {
        position += distance;
        if(!isFinished()) {
            position = board.positionCheck(position);
        }else {
            position = 59;
        }
    }

    public boolean isFinished() {
        return position >= 59;
    }

    public void initialize() {
        position = 0;
    }

    public int getPosition() {
        return position;
    }
}
