import java.util.Scanner;
public class Player {
    Scanner scanner = new Scanner(System.in);
    private int position;
    String playerColor;

    Player(String colorName){
        playerColor = colorName;
        position = -1;
    }

    void Play(){
        System.out.printf("%s님의 차례입니다. 엔터키를 눌러 주사위를 굴리세요.",this.playerColor);
        System.out.println();
        scanner.nextLine();
        int roll = (int)((Math.random()*10)%6)+1;
        System.out.printf("%s 주사위의 눈은 %d 입니다.",this.playerColor, roll);
        System.out.println();
        int movePosition = roll + position;
        if (movePosition > GameBorad.GameSize-1) movePosition = GameBorad.GameSize-1;
        GameBorad.MovePosition(this, movePosition);
        System.out.printf("%s님이 %d로 이동했습니다.",this.playerColor, position+1);
        System.out.println();
    }

    public void setPosition(int movePosition){
        position = movePosition;
    }
    public int getPosition() {
        return position;
    }
    public void setPlayerColor(String colorname){
        playerColor = colorname;
    }
    public String getPlayerColor() {
        return playerColor;
    }

}
