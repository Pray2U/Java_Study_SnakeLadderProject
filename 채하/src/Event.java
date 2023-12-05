public class Event{
    int startNum; //이벤트가 위치한 번호
    int moveNum;  //이동할 번호
    public Event(int inputStartNum, int inputMoveNum){
        startNum = inputStartNum;
        moveNum = inputMoveNum;
    }

    public void Move(Player player) {
        System.out.printf("%s님은 이벤트 칸에 멈췄습니다. %3d > %3d",player.getPlayerColor(),this.startNum+1,this.moveNum + 1);
        System.out.println();
        player.setPosition(moveNum);
    }
}
