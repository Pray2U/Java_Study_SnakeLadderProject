import java.net.*;
import java.util.*;
import java.io.*;

public class TcpIpServer {
    HashMap clients;

    TcpIpServer(){
        clients = new HashMap();
        Collections.synchronizedMap(clients);
    }
    public void start(){
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("서버가 시작되었습니다.");

            while (true){
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress()+":"+socket.getPort()+" 접속");
                ServerReceiver thread = new ServerReceiver(socket);
                thread.start();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void sendToAll(String msg){
        Iterator it = clients.keySet().iterator();

        while(it.hasNext()){
            try {
                DataOutputStream out = (DataOutputStream) clients.get(it.next());
                out.writeUTF(msg);
            }catch (IOException e){}
        }
    }

    class ServerReceiver extends Thread {
        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        ServerReceiver(Socket socket) {
            this.socket = socket;
            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
            }
        }
        public void run () {
            String name = "";

            try {
                name = in.readUTF();
                sendToAll(name+"접속했습니다. 2명이상 접속하면 게임이 시작됩니다.");
                clients.put(name, out);
                while (clients.size() >= 1){
                    // 게임 시작
                }

            }catch (IOException e){

            }finally {
                sendToAll("게임 인원이 부족합니다");
                clients.remove(name);
                System.out.println("게임 인원이 부족합니다.");

            }
        }
    }
}
