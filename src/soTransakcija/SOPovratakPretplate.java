/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soTransakcija;

import domen.Racun;
import domen.Transakcija;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOPovratakPretplate extends AbstractGenericOperation {

    ArrayList<Object> listaObj;

    @Override
    protected void validate(Object entity) throws Exception {
        listaObj = (ArrayList<Object>) entity;
        Transakcija t = (Transakcija) listaObj.get(0);
        Racun r = (Racun) listaObj.get(1);
        if ((!(t instanceof Transakcija)) || (!(r instanceof Racun))) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        listaObj = (ArrayList<Object>) entity;
        Transakcija t = (Transakcija) listaObj.get(0);
        Racun r = (Racun) listaObj.get(1);
        dbBroker.insertRecord(t);
        dbBroker.updateRecord(r);
    }

}
