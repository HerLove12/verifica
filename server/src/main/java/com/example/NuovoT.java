package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class NuovoT extends Thread{
    String n;
    Socket s;

    public NuovoT(Socket s, String n){
        this.s=s;
        this.n=n;
    }

    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Random rand = new Random();
            Integer num = rand.nextInt(1, 100);
            System.out.println("Il numero generato dal server è:  " + num);

            String risposta = "";
            for (int i = 0; i < 5; i++) {
                risposta = in.readLine();
                System.out.println("il client ha inviato: " + risposta);
                Integer intrisposta = Integer.parseInt(risposta);

                /*
                * protocollo:
                * # = sbagliato - il numero è maggiore
                * @ = sbagliato - il numero è minore
                * + = invodinato
                * ! = input sbagliato
                */
                if (intrisposta == num) {
                out.writeBytes("+" + "\n");
                    System.out.println("Il client ha indovinato! Grande :)");
                    s.close();
                } else if (intrisposta < num && intrisposta > 0) {
                    out.writeBytes("@" + "\n");
                } else if (intrisposta > num && intrisposta < 100) {
                    out.writeBytes("#" + "\n");
                } else {
                    out.writeBytes("!" + "\n");
                }

            }
            System.out.println("Il client non ha indovinato un sega :(");
            s.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}