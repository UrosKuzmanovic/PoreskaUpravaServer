/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soOpomena;

import domen.GeneralEntity;
import domen.Opomena;
import domen.Rata;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOKreirajOpomenu extends AbstractGenericOperation{
    
    @Override
    protected void validate(Object entity) throws Exception {
        ArrayList<Object> listaObj = (ArrayList<Object>) entity;
        Opomena o = (Opomena) listaObj.get(0);
        Rata r = (Rata) listaObj.get(1);
        if((!(o instanceof Opomena)) || (!(r instanceof Rata)))
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
    }

    @Override
    protected void execute(Object entity) throws Exception {
        ArrayList<Object> listaObj = (ArrayList<Object>) entity;
        Opomena o = (Opomena) listaObj.get(0);
        Rata r = (Rata) listaObj.get(1);
        dbBroker.insertRecord(o);
        dbBroker.updateRecord(r);
    }
    
}
