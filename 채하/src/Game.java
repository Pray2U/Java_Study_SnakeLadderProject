import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private GameBorad gameBorad;
    private String gameWinner;
    private static ArrayList<Player> Players;
    public void runGame(){
        Scanner scanner = new Scanner(System.in);

        gameBorad = new GameBorad();
        Players = new ArrayList();

        System.out.println("플레이어 1는 red입니다.");
        scanner.nextLine();
        Players.add(new Player("red"));

        System.out.println("플레이어 2는 blue입니다.");
        scanner.nextLine();
        Players.add(new Player("blue"));

        gameWinner = null;

        while(gameWinner == null){
            for (Player player: Players) {
                gameBorad.viewGameBoard();
                player.Play();
                if (player.getPosition() >= GameBorad.GameSize-1){
                    gameWinner = player.getPlayerColor();
                    break;
                }
            }
        }

        System.out.printf("%s님이 승리하셨습니다.",gameWinner);
        System.out.println();
    }

    public GameBorad getGameBorad() {
        return gameBorad;
    }

    public void setGameBorad(GameBorad gameBorad) {
        this.gameBorad = gameBorad;
    }

    public String getGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(String gameWinner) {
        this.gameWinner = gameWinner;
    }

    public static ArrayList<Player> getPlayers() {
        return Players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        Players = players;
    }
}
