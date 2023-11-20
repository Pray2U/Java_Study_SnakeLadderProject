package org.example.model;

public class Ladder {
    int from;
    int to;
    LadderDirection direction;

    //정적 팩토리 패턴 사용하여 내부 에서 만들어 반환 예정
    private Ladder(int from, int to, LadderDirection direction) {
        this.from = from;
        this.to = to;
        this.direction = direction;
    }

}
