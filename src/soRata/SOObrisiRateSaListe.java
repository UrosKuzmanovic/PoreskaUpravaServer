/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soRata;

import domen.GeneralEntity;
import domen.Obaveza;
import domen.Rata;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOObrisiRateSaListe extends AbstractGenericOperation {

    @Override
    protected void validate(Object entity) throws Exception {
        ArrayList<Object> listaObj = (ArrayList<Object>) entity;
        ArrayList<Rata> listaRata = (ArrayList<Rata>) listaObj.get(0);
        Obaveza obaveza = (Obaveza) listaObj.get(1);
        if ((!(listaRata.get(0) instanceof Rata)) || (!(obaveza instanceof Obaveza))) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        ArrayList<Object> listaObj = (ArrayList<Object>) entity;
        ArrayList<Rata> listaRata = (ArrayList<Rata>) listaObj.get(0);
        Obaveza obaveza = (Obaveza) listaObj.get(1);
        for (Rata rata : listaRata) {
            dbBroker.deleteRecord(rata);
        }
        dbBroker.updateRecord(obaveza);
    }

}
