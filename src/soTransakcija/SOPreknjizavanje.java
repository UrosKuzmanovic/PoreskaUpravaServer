/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soTransakcija;

import domen.GeneralEntity;
import domen.Racun;
import domen.Transakcija;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOPreknjizavanje extends AbstractGenericOperation {

    ArrayList<Object> listaObj;

    @Override
    protected void validate(Object entity) throws Exception {
        listaObj = (ArrayList<Object>) entity;
        Transakcija t1 = (Transakcija) listaObj.get(0);
        Transakcija t2 = (Transakcija) listaObj.get(1);
        Racun r1 = (Racun) listaObj.get(2);
        Racun r2 = (Racun) listaObj.get(3);
        if ((!(t1 instanceof Transakcija)) || (!(t2 instanceof Transakcija)) || (!(r1 instanceof Racun)) || (!(r2 instanceof Racun))) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        listaObj = (ArrayList<Object>) entity;
        Transakcija t1 = (Transakcija) listaObj.get(0);
        Transakcija t2 = (Transakcija) listaObj.get(1);
        Racun r1 = (Racun) listaObj.get(2);
        Racun r2 = (Racun) listaObj.get(3);
        dbBroker.insertRecord(t1);
        dbBroker.insertRecord(t2);
        dbBroker.updateRecord(r1);
        dbBroker.updateRecord(r2);
    }

}
