package org.example;


public interface IPlayer {
    void move(int distance, IBoard board);
    boolean isFinished();
    void initialize();
    int getPosition();
    boolean getTurn();
    void setTurn(boolean turn);
    String getNickname();
}