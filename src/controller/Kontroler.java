/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.GeneralEntity;
import domen.Obaveza;
import domen.Obveznik;
import domen.Opomena;
import domen.Racun;
import domen.Rata;
import domen.Transakcija;
import domen.VrstaRacuna;
import java.util.ArrayList;
import java.util.List;
import so.AbstractGenericOperation;
import soObaveza.SOIzmeniObavezu;
import soObaveza.SOKreirajObavezu;
import soObaveza.SOKreirajObavezuIRate;
import soObaveza.SOObrisiObavezu;
import soObaveza.SOVratiObavezeZaRacun;
import soObaveza.SOVratiObavezu;
import soObaveza.SOVratiSveObaveze;
import soObveznik.SOIzmeniObveznika;
import soObveznik.SOKreirajObveznika;
import soObveznik.SONadjiObveznikaPremaImenu;
import soObveznik.SOObrisiObveznika;
import soObveznik.SOVratiObveznika;
import soObveznik.SOVratiSveObveznike;
import soOpomena.SOIzmeniOpomenu;
import soOpomena.SOKreirajOpomenu;
import soOpomena.SOObrisiOpomenu;
import soOpomena.SOVratiOpomenu;
import soOpomena.SOVratiSveOpomene;
import soRacun.SOIzmeniRacun;
import soRacun.SOKreirajRacun;
import soRacun.SOObrisiRacun;
import soRacun.SOVratiObveznikoveRacune;
import soRacun.SOVratiRacun;
import soRacun.SOVratiSveRacune;
import soRata.SOIzmeniRatu;
import soRata.SOKreirajRatu;
import soRata.SOObrisiRateSaListe;
import soRata.SOObrisiRatu;
import soRata.SOVratiRateZaObavezu;
import soRata.SOVratiSveRate;
import soTransakcija.SOIzmeniTransakciju;
import soTransakcija.SOKreirajTransakciju;
import soTransakcija.SOObrisiTransakciju;
import soTransakcija.SOPovratakPretplate;
import soTransakcija.SOPreknjizavanje;
import soTransakcija.SOVratiSveTransakcije;
import soTransakcija.SOVratiTranakciju;
import soVrstaRacuna.SOIzmeniVrstuRacuna;
import soVrstaRacuna.SOKreirajVrstuRacuna;
import soVrstaRacuna.SOObrisiVrstuRacuna;
import soVrstaRacuna.SOVratiSveVrsteRacuna;
import soVrstaRacuna.SOVratiVrstuRacuna;

/**
 *
 * @author Ookee
 */
public class Kontroler {

    private static Kontroler instance;

    public Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ArrayList<Obaveza> vratiSveObaveze() throws Exception {
        AbstractGenericOperation op = new SOVratiSveObaveze();
        op.templateExecute(new Obaveza());
        List<GeneralEntity> lista = ((SOVratiSveObaveze) op).getObaveze();
        ArrayList<Obaveza> obaveze = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Obaveza o = (Obaveza) generalEntity;
            obaveze.add(o);
        }
        return obaveze;
    }

    public ArrayList<Obveznik> vratiSveObveznike() throws Exception {
        AbstractGenericOperation op = new SOVratiSveObveznike();
        op.templateExecute(new Obveznik());
        List<GeneralEntity> lista = ((SOVratiSveObveznike) op).getObveznici();
        ArrayList<Obveznik> obveznici = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Obveznik o = (Obveznik) generalEntity;
            obveznici.add(o);
        }
        return obveznici;
    }

    public ArrayList<Opomena> vratiSveOpomene() throws Exception {
        AbstractGenericOperation op = new SOVratiSveOpomene();
        op.templateExecute(new Opomena());
        List<GeneralEntity> lista = ((SOVratiSveOpomene) op).getOpomene();
        ArrayList<Opomena> opomene = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Opomena o = (Opomena) generalEntity;
            opomene.add(o);
        }
        return opomene;
    }

    public ArrayList<Racun> vratiSveRacune() throws Exception {
        AbstractGenericOperation op = new SOVratiSveRacune();
        op.templateExecute(new Racun());
        List<GeneralEntity> lista = ((SOVratiSveRacune) op).getRacuni();
        ArrayList<Racun> racuni = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Racun r = (Racun) generalEntity;
            racuni.add(r);
        }
        return racuni;
    }

    public ArrayList<Rata> vratiSveRate() throws Exception {
        AbstractGenericOperation op = new SOVratiSveRate();
        op.templateExecute(new Rata());
        List<GeneralEntity> lista = ((SOVratiSveRate) op).getRate();
        ArrayList<Rata> rate = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Rata r = (Rata) generalEntity;
            rate.add(r);
        }
        return rate;
    }

    public ArrayList<Transakcija> vratiSveTransakcije() throws Exception {
        AbstractGenericOperation op = new SOVratiSveTransakcije();
        op.templateExecute(new Transakcija());
        List<GeneralEntity> lista = ((SOVratiSveTransakcije) op).getTransakcije();
        ArrayList<Transakcija> transakcije = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Transakcija t = (Transakcija) generalEntity;
            transakcije.add(t);
        }
        return transakcije;
    }

    public ArrayList<VrstaRacuna> vratiSveVrsteRacuna() throws Exception {
        AbstractGenericOperation op = new SOVratiSveVrsteRacuna();
        op.templateExecute(new VrstaRacuna());
        List<GeneralEntity> lista = ((SOVratiSveVrsteRacuna) op).getVrsteRacuna();
        ArrayList<VrstaRacuna> vrsteRacuna = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            VrstaRacuna vr = (VrstaRacuna) generalEntity;
            vrsteRacuna.add(vr);
        }
        return vrsteRacuna;
    }

    public Obaveza vratiObavezu(Obaveza o) throws Exception {
        AbstractGenericOperation op = new SOVratiObavezu();
        op.templateExecute(o);
        return (Obaveza) ((SOVratiObavezu) op).getObaveza();
    }

    public Obveznik vratiObveznika(Obveznik o) throws Exception {
        AbstractGenericOperation op = new SOVratiObveznika();
        op.templateExecute(o);
        return (Obveznik) ((SOVratiObveznika) op).getObveznik();
    }

    public Opomena vratiOpomena(Opomena o) throws Exception {
        AbstractGenericOperation op = new SOVratiOpomenu();
        op.templateExecute(o);
        return (Opomena) ((SOVratiOpomenu) op).getOpomena();
    }

    public Racun vratiRacun(Racun r) throws Exception {
        AbstractGenericOperation op = new SOVratiRacun();
        op.templateExecute(r);
        return (Racun) ((SOVratiRacun) op).getRacun();
    }

    public Transakcija vratiTransakciju(Transakcija t) throws Exception {
        AbstractGenericOperation op = new SOVratiTranakciju();
        op.templateExecute(t);
        return (Transakcija) ((SOVratiTranakciju) op).getTransakcija();
    }

    public VrstaRacuna vratiVrstuRacuna(VrstaRacuna vr) throws Exception {
        AbstractGenericOperation op = new SOVratiVrstuRacuna();
        op.templateExecute(vr);
        return (VrstaRacuna) ((SOVratiVrstuRacuna) op).getVrstaRacuna();
    }

    public void kreirajObavezu(Obaveza o) throws Exception {
        AbstractGenericOperation op = new SOKreirajObavezu();
        op.templateExecute(o);
    }

    public void kreirajObveznika(Obveznik o) throws Exception {
        AbstractGenericOperation op = new SOKreirajObveznika();
        op.templateExecute(o);
    }

    public void kreirajOpomenu(ArrayList<Object> listaObj) throws Exception {
        AbstractGenericOperation op = new SOKreirajOpomenu();
        op.templateExecute(listaObj);
    }

    public void kreirajRacun(Racun r) throws Exception {
        AbstractGenericOperation op = new SOKreirajRacun();
        op.templateExecute(r);
    }

    public void kreirajRatu(Rata r) throws Exception {
        AbstractGenericOperation op = new SOKreirajRatu();
        op.templateExecute(r);
    }

    public void kreirajTransakciju(Transakcija t) throws Exception {
        AbstractGenericOperation op = new SOKreirajTransakciju();
        op.templateExecute(t);
    }

    public void kreirajvrstuRacuna(VrstaRacuna vr) throws Exception {
        AbstractGenericOperation op = new SOKreirajVrstuRacuna();
        op.templateExecute(vr);
    }

    public void izmeniObavezu(Obaveza o) throws Exception {
        AbstractGenericOperation op = new SOIzmeniObavezu();
        op.templateExecute(o);
    }

    public void izmeniObveznika(Obveznik o) throws Exception {
        AbstractGenericOperation op = new SOIzmeniObveznika();
        op.templateExecute(o);
    }

    public void izmeniOpomenu(Opomena o) throws Exception {
        AbstractGenericOperation op = new SOIzmeniOpomenu();
        op.templateExecute(o);
    }

    public void izmeniRacun(Racun r) throws Exception {
        AbstractGenericOperation op = new SOIzmeniRacun();
        op.templateExecute(r);
    }

    public void izmeniRatu(Rata r) throws Exception {
        AbstractGenericOperation op = new SOIzmeniRatu();
        op.templateExecute(r);
    }

    public void izmeniTransakciju(Transakcija t) throws Exception {
        AbstractGenericOperation op = new SOIzmeniTransakciju();
        op.templateExecute(t);
    }

    public void izmeniVrstuRacuna(VrstaRacuna vr) throws Exception {
        AbstractGenericOperation op = new SOIzmeniVrstuRacuna();
        op.templateExecute(vr);
    }

    public void obrisiObavezu(Obaveza o) throws Exception {
        AbstractGenericOperation op = new SOObrisiObavezu();
        op.templateExecute(o);
    }

    public void obrisiObveznika(Obveznik o) throws Exception {
        AbstractGenericOperation op = new SOObrisiObveznika();
        op.templateExecute(o);
    }

    public void obrisiOpomenu(Opomena o) throws Exception {
        AbstractGenericOperation op = new SOObrisiOpomenu();
        op.templateExecute(o);
    }

    public void obrisiRacun(Racun r) throws Exception {
        AbstractGenericOperation op = new SOObrisiRacun();
        op.templateExecute(r);
    }

    public void obrisiRatu(Rata r) throws Exception {
        AbstractGenericOperation op = new SOObrisiRatu();
        op.templateExecute(r);
    }

    public void obrisiTransakciju(Transakcija t) throws Exception {
        AbstractGenericOperation op = new SOObrisiTransakciju();
        op.templateExecute(t);
    }

    public void obrisiVrstuRacuna(VrstaRacuna vr) throws Exception {
        AbstractGenericOperation op = new SOObrisiVrstuRacuna();
        op.templateExecute(vr);
    }

    public ArrayList<Obveznik> nadjiObveznikaPremaImenu(Obveznik o) throws Exception {
        AbstractGenericOperation op = new SONadjiObveznikaPremaImenu();
        op.templateExecute(o);
        List<GeneralEntity> lista = ((SONadjiObveznikaPremaImenu) op).getObveznici();
        ArrayList<Obveznik> obveznici = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Obveznik ob = (Obveznik) generalEntity;
            obveznici.add(ob);
        }
        return obveznici;
    }

    public ArrayList<Racun> vratiObveznikoveRacune(Racun r1) throws Exception {
        AbstractGenericOperation op = new SOVratiObveznikoveRacune();
        op.templateExecute(r1);
        List<GeneralEntity> lista = ((SOVratiObveznikoveRacune) op).getRacuni();
        ArrayList<Racun> racuni = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Racun r = (Racun) generalEntity;
            racuni.add(r);
        }
        return racuni;
    }

    public ArrayList<Obaveza> vratiObavezeZaRacun(Obaveza o) throws Exception {
        AbstractGenericOperation op = new SOVratiObavezeZaRacun();
        op.templateExecute(o);
        List<GeneralEntity> lista = ((SOVratiObavezeZaRacun) op).getObaveze();
        ArrayList<Obaveza> obaveze = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Obaveza o1 = (Obaveza) generalEntity;
            obaveze.add(o1);
        }
        return obaveze;
    }

    public ArrayList<Rata> vratiSveRateZaObavezu(Rata r) throws Exception {
        AbstractGenericOperation op = new SOVratiRateZaObavezu();
        op.templateExecute(r);
        List<GeneralEntity> lista = ((SOVratiRateZaObavezu) op).getRate();
        ArrayList<Rata> rate = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Rata r1 = (Rata) generalEntity;
            rate.add(r1);
        }
        return rate;
    }

    public void kreirajObavezuIRate(ArrayList<Object> listaObj) throws Exception {
        AbstractGenericOperation op = new SOKreirajObavezuIRate();
        op.templateExecute(listaObj);
    }

    public void preknjizavanjePretplate(ArrayList<Object> listaObj) throws Exception {
        AbstractGenericOperation op = new SOPreknjizavanje();
        op.templateExecute(listaObj);
    }

    public void povratakPretplate(ArrayList<Object> listaObj) throws Exception {
        AbstractGenericOperation op = new SOPovratakPretplate();
        op.templateExecute(listaObj);
    }

    public void obrisiRateSaListe(ArrayList<Object> listaObj) throws Exception {
        AbstractGenericOperation op = new SOObrisiRateSaListe();
        op.templateExecute(listaObj);
    }

}
