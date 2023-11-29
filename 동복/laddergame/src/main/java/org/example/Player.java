package org.example;

import org.example.model.Dice;

public class Player implements IPlayer {
    private int position;
    private String nickname;

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void move(int distance, IBoard board) {
        position += distance;
        if(!isFinished()) {
            position = board.positionCheck(position);
        }

    }

    public boolean isFinished() {
        return position >= 60;
    }

    public void initialize() {
        position = 0;
    }

    public int getPosition() {
        return position;
    }
}
