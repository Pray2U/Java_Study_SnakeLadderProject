import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);

            System.out.println("서버가 시작되었습니다. 클라이언트를 기다립니다.");

            Socket player1Socket = serverSocket.accept();
            System.out.println("Player 1이 연결되었습니다.");

            Socket player2Socket = serverSocket.accept();
            System.out.println("Player 2가 연결되었습니다.");

            // 각 클라이언트에 대한 처리를 위한 스레드 시작
            Thread player1Thread = new Thread(new Handler(player1Socket, "Player 1"));
            Thread player2Thread = new Thread(new Handler(player2Socket, "Player 2"));

            player1Thread.start();
            player2Thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
