package org.example.model;

public class Dice {
    int[] dice;

    public Dice(int startNum, int endNum) {
        dice = new int[endNum - startNum];
        for (int i = startNum; i < endNum; i++) {
            dice[i - startNum] = i;
        }
    }

    public int roll() {
        return (int)(Math.random() * 6);
    }

}
