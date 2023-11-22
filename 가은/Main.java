import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int BOARD_SIZE = 10;
    private static final Map<Integer, Integer> snakePositions = new HashMap<>();
    private static final Map<Integer, Integer> ladderPositions = new HashMap<>();

    static {
        // 뱀의 위치 설정
        snakePositions.put(16, 6);
        snakePositions.put(47, 26);
        snakePositions.put(49, 11);
        snakePositions.put(56, 53);
        snakePositions.put(62, 19);
        snakePositions.put(64, 60);
        snakePositions.put(87, 24);
        snakePositions.put(93, 73);
        snakePositions.put(95, 75);
        snakePositions.put(98, 78);

        // 사다리의 위치 설정
        ladderPositions.put(1, 38);
        ladderPositions.put(4, 14);
        ladderPositions.put(9, 31);
        ladderPositions.put(21, 42);
        ladderPositions.put(28, 84);
        ladderPositions.put(36, 44);
        ladderPositions.put(51, 67);
        ladderPositions.put(71, 91);
        ladderPositions.put(80, 100);
    }

    private static int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    private static void drawBoard(int playerPosition) {
        for (int row = BOARD_SIZE; row >= 1; row--) {
            for (int col = 1; col <= BOARD_SIZE; col++) {
                int currentCell = (row - 1) * BOARD_SIZE + col;
                String cellContent;

                if (currentCell == playerPosition) {
                    cellContent = "P";
                } else if (snakePositions.containsKey(currentCell)) {
                    cellContent = currentCell + ">" + snakePositions.get(currentCell);
                } else if (ladderPositions.containsKey(currentCell)) {
                    cellContent = currentCell + ">" + ladderPositions.get(currentCell);
                } else {
                    cellContent = String.valueOf(currentCell);
                }

                // 간격을 맞춰 출력
                System.out.print(String.format("%-8s", cellContent));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int currentPlayerPosition = 1;

        Scanner scanner = new Scanner(System.in);

        while (currentPlayerPosition < BOARD_SIZE * BOARD_SIZE) {
            drawBoard(currentPlayerPosition);

            System.out.println("주사위를 굴려주세요. (Enter를 누르세요)");
            scanner.nextLine();

            int diceValue = rollDice();
            System.out.println("주사위 값: " + diceValue);

            int newPosition = currentPlayerPosition + diceValue;

            if (snakePositions.containsKey(newPosition)) {
                System.out.println("뱀을 만났습니다! " + newPosition + "에서 " + snakePositions.get(newPosition) + "로 이동합니다.");
                currentPlayerPosition = snakePositions.get(newPosition);
            } else if (ladderPositions.containsKey(newPosition)) {
                System.out.println("사다리를 만났습니다! " + newPosition + "에서 " + ladderPositions.get(newPosition) + "로 이동합니다.");
                currentPlayerPosition = ladderPositions.get(newPosition);
            } else {
                currentPlayerPosition = newPosition;
            }

            System.out.println("현재 위치: " + currentPlayerPosition);

            if (currentPlayerPosition == BOARD_SIZE * BOARD_SIZE) {
                drawBoard(currentPlayerPosition);
                System.out.println("게임 종료! 승리했습니다!");
            }
        }
    }
}
