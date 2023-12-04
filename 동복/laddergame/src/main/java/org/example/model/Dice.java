package org.example.model;

public enum Dice {
    ONE(1, "one.png"),
    TWO(2, "two.png"),
    THREE(3, "three.png"),
    FOUR(4, "four.png"),
    FIVE(5, "five.png"),
    SIX(6, "six.png");

    private final int number;
    private final String imgURL;

    public String getUrl() {
        return "images/" + imgURL;
    }

    public String getUrl(int number) {
        return "images/" + values()[number - 1].imgURL;
    }

    public int getNumber() {
        return number;
    }


    private Dice( int number, String imageUrl) {
        this.imgURL = imageUrl;
        this.number = number;
    }


    public static Dice roll() {
        return values()[(int)(Math.random() * 6)];
    }

}
