import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String playerName;

    public Handler(Socket socket, String playerName) {
        this.clientSocket = socket;
        this.playerName = playerName;

        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            out.println("게임에 참여하신 것을 환영합니다!");

            while (true) {
                // 플레이어의 차례를 출력
                out.println(playerName + "의 차례입니다. 주사위를 굴려주세요. (Enter를 누르세요)");
                in.readLine(); // 클라이언트에서 엔터 입력 기다림

                int diceValue = rollDice();
                out.println("주사위 값: " + diceValue);

                out.println(playerName + "의 게임 진행 상태: " + "현재 위치 " + diceValue + "칸 전진");

                // 게임 종료 조건 예시 (20칸 이동하면 종료)
                if (diceValue >= 20) {
                    out.println("게임 종료! " + playerName + "이(가) 승리했습니다!");
                    break;
                }

                // 다음 플레이어의 차례를 기다림
                out.println("상대 플레이어의 차례를 기다립니다...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int rollDice() {
        // 간단한 주사위 굴리기 구현
        return (int) (Math.random() * 6) + 1;
    }
}
