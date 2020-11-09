/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import domen.Obaveza;
import domen.Obveznik;
import domen.Opomena;
import domen.Racun;
import domen.Rata;
import domen.Transakcija;
import domen.VrstaRacuna;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import konstante.Konstante;
import controller.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Ookee
 */
public class ObradaKlijentskogZahtevaNit extends Thread {

    Socket klijentskiSoket;
    boolean kraj = false;

    public ObradaKlijentskogZahtevaNit(Socket klijentskiSoket) {
        this.klijentskiSoket = klijentskiSoket;
    }

    @Override
    public void run() {
        while (!kraj) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();

            try {
                switch (kz.getOperacija()) {
                    case Konstante.VRATI_SVE_OBVEZNIKE:
                        ArrayList<Obveznik> listaObveznika = Kontroler.getInstance().vratiSveObveznike();
                        so.setOdogovor(listaObveznika);
                        break;
                    case Konstante.NADJI_OBVEZNIKA:
                        Obveznik obv1 = (Obveznik) kz.getParametar();
                        ArrayList<Obveznik> listaPretrazenihObveznika = Kontroler.getInstance().nadjiObveznikaPremaImenu(obv1);
                        so.setOdogovor(listaPretrazenihObveznika);
                        break;
                    case Konstante.VRATI_OBVEZNIKA:
                        Obveznik obv2 = (Obveznik) kz.getParametar();
                        Obveznik obv3 = Kontroler.getInstance().vratiObveznika(obv2);
                        so.setOdogovor(obv3);
                        break;
                    case Konstante.KREIRAJ_NOVOG_OBVEZNIKA:
                        Obveznik obv4 = (Obveznik) kz.getParametar();
                        Kontroler.getInstance().kreirajObveznika(obv4);
                        so.setPoruka("1");
                        break;
                    case Konstante.OBRISI_OBVEZNIKA:
                        Obveznik obv5 = (Obveznik) kz.getParametar();
                        Kontroler.getInstance().obrisiObveznika(obv5);
                        so.setPoruka("1");
                        break;
                    case Konstante.VRATI_OBVEZNIKOVE_RACUNE:
                        Racun r1 = (Racun) kz.getParametar();
                        ArrayList<Racun> listaRacuna = Kontroler.getInstance().vratiObveznikoveRacune(r1);
                        so.setOdogovor(listaRacuna);
                        break;
                    case Konstante.VRATI_OBAVEZE_ZA_RACUN:
                        Obaveza ob1 = (Obaveza) kz.getParametar();
                        ArrayList<Obaveza> listaObaveza = Kontroler.getInstance().vratiObavezeZaRacun(ob1);
                        so.setOdogovor(listaObaveza);
                        break;
                    case Konstante.VRATI_RATE_ZA_OBAVEZU:
                        Rata rata1 = (Rata) kz.getParametar();
                        ArrayList<Rata> listaRata = Kontroler.getInstance().vratiSveRateZaObavezu(rata1);
                        so.setOdogovor(listaRata);
                        break;
                    case Konstante.VRATI_SVE_VRSTE_RACUNA:
                        ArrayList<VrstaRacuna> listaVrstaRacuna = Kontroler.getInstance().vratiSveVrsteRacuna();
                        so.setOdogovor(listaVrstaRacuna);
                        break;
                    case Konstante.KREIRAJ_NOVI_RACUN:
                        Racun r2 = (Racun) kz.getParametar();
                        Kontroler.getInstance().kreirajRacun(r2);
                        so.setPoruka("1");
                        break;
                    case Konstante.KREIRAJ_NOVU_OBAVEZU:
                        ArrayList<Object> listaObj = (ArrayList<Object>) kz.getParametar();
                        Kontroler.getInstance().kreirajObavezuIRate(listaObj);
                        so.setPoruka("1");
                        break;
                    /*case Konstante.OBRISI_RATU:
                        Rata rata2 = (Rata) kz.getParametar();
                        Kontroler.getInstance().obrisiRatu(rata2);
                        so.setPoruka("1");
                        break;*/
                    case Konstante.OBRISI_RATU:
                        ArrayList<Object> listaObj1 = (ArrayList<Object>) kz.getParametar();
                        Kontroler.getInstance().obrisiRateSaListe(listaObj1);
                        so.setPoruka("1");
                        break;
                    case Konstante.UPLATA_PRETPLATE:
                        Transakcija t = (Transakcija) kz.getParametar();
                        Kontroler.getInstance().kreirajTransakciju(t);
                        so.setPoruka("1");
                        break;
                    case Konstante.AZURIRAJ_STANJE:
                        Racun r3 = (Racun) kz.getParametar();
                        Kontroler.getInstance().izmeniRacun(r3);
                        break;
                    case Konstante.PREKNJIZAVANJE_PRETPLATE:
                        ArrayList<Object> listaObj2 = (ArrayList<Object>) kz.getParametar();
                        Kontroler.getInstance().preknjizavanjePretplate(listaObj2);
                        so.setPoruka("1");
                        break;
                    case Konstante.POVRATAK_PRETPLATE:
                        ArrayList<Object> listaObj3 = (ArrayList<Object>) kz.getParametar();
                        Kontroler.getInstance().povratakPretplate(listaObj3);
                        so.setPoruka("1");
                        break;
                    case Konstante.POSALJI_OPOMENU:
                        ArrayList<Object> listaObj4 = (ArrayList<Object>) kz.getParametar();
                        Kontroler.getInstance().kreirajOpomenu(listaObj4);
                        so.setPoruka("1");
                        break;
                }
            } catch (Exception e) {
                System.out.println("GRESKA");
                System.out.println(e);
                so.setPoruka("0");
            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(klijentskiSoket.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            //Logger.getLogger(ObradaKlijentskogZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(ObradaKlijentskogZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(klijentskiSoket.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            //Logger.getLogger(ObradaKlijentskogZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
            kraj = true;
            System.out.println("Klijent je prekinuo vezu.");
        }
    }

}
