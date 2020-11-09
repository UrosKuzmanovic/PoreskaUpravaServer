/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ookee
 */
public class PokreniServerNit extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(5555);
            System.out.println("Server se pokrenuo.");
            while (true) {
                Socket s = ss.accept();
                System.out.println("Klijent se povezao.");
                ObradaKlijentskogZahtevaNit okzn = new ObradaKlijentskogZahtevaNit(s);
                okzn.start();
            }
        } catch (IOException ex) {
            System.out.println("Server ne radi.");
            Logger.getLogger(PokreniServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
