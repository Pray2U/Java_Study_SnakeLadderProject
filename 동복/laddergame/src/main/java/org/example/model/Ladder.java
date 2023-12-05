package org.example.model;

public class Ladder {
    private int from;
    private int to;

    //정적 팩토리 패턴 사용하여 내부 에서 만들어 반환 예정
    private Ladder(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getStart() {
        return from;
    }

    public int getEnd() {
        return to;
    }

    public static Ladder of(LadderDirection direction) {
        int num1 = getRandomPosition();
        int num2 = getRandomPosition();

        while(num1 == num2 || Math.abs(num1 - num2) > 30) {
            num2 = getRandomPosition();
        }

        if (num1 < num2) {
            int tmp = num1;
            num1 = num2;
            num2 = tmp;
        }

        if(direction == LadderDirection.UP) {
            return new Ladder(num1, num2);
        }
        return new Ladder(num2, num1);
    }

    private static int getRandomPosition() {
        return (int)(Math.random() * 55) + 1;
    }
}
