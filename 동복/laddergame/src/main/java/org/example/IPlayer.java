package org.example;

public interface IPlayer {
    int role();
    void move(IBoard board, int distance);
}
