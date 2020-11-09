/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soObaveza;

import domen.GeneralEntity;
import domen.Obaveza;
import domen.Rata;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOKreirajObavezuIRate extends AbstractGenericOperation {

    @Override
    protected void validate(Object entity) throws Exception {
        ArrayList<Object> listaObj = (ArrayList<Object>) entity;
        Obaveza obaveza = (Obaveza) listaObj.get(0);
        ArrayList<Rata> listaRata = (ArrayList<Rata>) listaObj.get(1);
        if (!(obaveza instanceof Obaveza) && !(listaRata.get(0) instanceof Rata)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        ArrayList<Object> listaObj = (ArrayList<Object>) entity;
        Obaveza obaveza = (Obaveza) listaObj.get(0);
        ArrayList<Rata> listaRata = (ArrayList<Rata>) listaObj.get(1);
        int id = dbBroker.insertRecordReturnId(obaveza);
        for (Rata rata : listaRata) {
            rata.getResenje().setBrResenja(id);
            dbBroker.insertRecord(rata);
        }
    }
}
