import java.util.*;
import java.util.stream.Collectors;

class GameBorad {

    private static final Map<String,String> colorMap = new HashMap<>();

    public static final int GameSize = 100;
    public static final int EventCount = 5;
    private static ArrayList<Event> GameArr;

    GameBorad(){
        colorMap.put("red","\u001B[31m");
        colorMap.put("blue","\u001B[34m");
        colorMap.put("yellow","\u001B[33m");
        colorMap.put("exit","\u001B[0m");

        GameArr = new ArrayList<Event>(){{
            int i = 0;
            while (i < GameSize) {
                add(null);
                i++;
            }
        }};
        int Count = 0;
        while(Count < EventCount){
            int eventPosition = (int)((Math.random()*100)%GameSize);
            if (GameArr.get(eventPosition) ==null) {
                int movePosition = (int) ((Math.random() * 100) % GameSize);
                GameArr.set(eventPosition, new Event(eventPosition, movePosition));
                Count++;
            }
        }
    }

    public static void MovePosition(Player movePlayer,int moveposition){

        if(GameArr.get(moveposition) != null){
            GameArr.get(moveposition).Move(movePlayer);
        }else{
            movePlayer.setPosition(moveposition);
        }
    }

    public static void viewGameBoard(){
        int count = 0;

        //플레이어 위치와 색 추출
        Map<Integer, String> playerInfoMap = Game.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player.getPosition(),
                        player -> player.getPlayerColor(),
                        (existing, replacement) -> replacement
                ));


        while(GameSize-1 > count) {
            for (int i = 0; i < 10; i++) {
                if (GameArr.get(count) != null){
                    System.out.printf("%-3d > %-3d", count + 1 ,GameArr.get(count).moveNum+1);
                } else if (playerInfoMap.containsKey(count)) {
                    System.out.printf(colorMap.get(playerInfoMap.get(count))+"%-8d "+colorMap.get("exit"), count + 1);

                }else{
                    System.out.printf("%-8d ", count + 1);
                }
                System.out.printf("|");
                count++;

            }
            System.out.println();
        }
    }
}
