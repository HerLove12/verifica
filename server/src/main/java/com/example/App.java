package com.example;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Biglietto b = new Biglietto(5);
        try {
            System.out.println("Server avviato");
            ServerSocket server = new ServerSocket(3000);
            while(true){
                Socket s = server.accept();
                System.out.println("in client si è connesso");

                NuovoT t1 = new NuovoT(s, "server", b);
                t1.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
