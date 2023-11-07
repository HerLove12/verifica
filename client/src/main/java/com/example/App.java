package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 3000);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Scanner scan = new Scanner(System.in);
            System.out.println("Indovina un numero da 1 A 100 generato dal server");
            for (int i = 0; i < 5; i++) {
                System.out.println("inserisci numero: ");
                String invia = scan.nextLine();
                out.writeBytes(invia + "\n");

                String r = in.readLine();

                if(r.equals("@")){
                    System.out.println("Il server ha risposto:  E t'hai sbagliato, il tuo numero l'era minore >:(");
                }
                else if(r.equals("#")){
                    System.out.println("Il server ha risposto:  E t'hai sbagliato, il tuo numero l'era maggiore >:(");
                }
                else if(r.equals("+")){
                    System.out.println("Il server ha risposto:  E t'hai indovinato, sei un grande :)");
                }
                else if(r.equals("!")){
                    System.out.println("Il server ha risposto:  ma icche t'hai scritto sei tutto scemo :^");
                }
            }
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
