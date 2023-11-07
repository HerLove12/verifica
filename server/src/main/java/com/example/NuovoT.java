package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class NuovoT extends Thread {
    String n;
    Socket s;
    Biglietto b;

    public NuovoT(Socket s, String n, Biglietto b) {
        this.s = s;
        this.n = n;
        this.b = b;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            /*
             * protocollo ricezione:
             * @ = disponibilita biglietto
             * # = acquista biglietto
             * + = chiusura server
             * ! = biglietti arrivati a 0
             * ? = input strano
             */

            while (true) {
                System.out.println("DEBUG: INIZIO CICLO");

                String risposta = "";
                risposta = in.readLine();
                System.out.println("il client ha inviato: " + risposta);

                if (risposta.equals("D")) {
                    out.writeBytes("@" + "\n");
                    out.writeBytes(b.n + "\n");
                } else if (risposta.equals("A")) {
                    if (b.n > 0) {
                        b.n = b.n - 1;
                        if(checkIfZero()){
                            out.writeBytes("!" + "\n");
                            s.close();
                        }
                        out.writeBytes("#" + "\n");
                        out.writeBytes(b.n + "\n");
                        //out.writeBytes("Hai acquistato il biglietto con successo! I biglietti disponibili ora sono: " + this.b + "\n");
                    }
                    else{
                        out.writeBytes("!" + "\n");
                        s.close();
                    }
                } else if (risposta.equals("Q")) {
                    out.writeBytes("+" + "\n");
                    //out.writeBytes("Arrivederci e grazie" + "\n");
                    s.close();
                } else{
                    out.writeBytes("?" + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkIfZero(){
        if(this.b.n <= 0){
            return true;
        }
        return false;
    }
}