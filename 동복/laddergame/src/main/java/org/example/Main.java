package org.example;

import org.example.network.Client;
import org.example.network.Server;
import org.example.view.frame.MainFrame;

public class Main {
    public static void main(String[] args) {
//        MainFrame tf = new MainFrame();
        Server.run();
    }
}