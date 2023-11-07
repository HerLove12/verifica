package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /*
         * protocollo ricezione:
         * D = disponibilita biglietto
         * A = acquista biglietto
         * Q = invodinato
         * ! = input sbagliato
         */
        try {
            Socket s = new Socket("localhost", 3000);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            while(true){
                Scanner scan = new Scanner(System.in);
                System.out.println("Cosa vuoi fare?\n'A' = acquisto, 'D' = disponibilita, 'Q' = esci");
                System.out.println("inserisci selezione: ");
                String invia = scan.nextLine();
                out.writeBytes(invia + "\n");

                String r = in.readLine();

                if (r.equals("@")) {
                    System.out.println("Il server ha risposto:\nI biglietti disponibili sono: ");
                    r = in.readLine();
                    System.out.println(r);
                } else if (r.equals("#")) {
                    System.out.println("Il server ha risposto:\nHai acquistato il biglietto con successo! I biglietti disponibili ora sono: ");
                    r = in.readLine();
                    System.out.println(r);
                } else if (r.equals("+")) {
                    System.out.println("Il server ha risposto:\nArrivederci e grazie ");
                    s.close();
                    break;
                } else if (r.equals("!")) {
                    System.out.println("Il server ha risposto:\nI biglietti sono esauriti :( ");
                    s.close();
                    break;
                } else if (r.equals("?")) {
                    System.out.println("Hai inserito un input non riconosciuto dal server. Riprova.");
                }
                
            }
            //s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
