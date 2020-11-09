/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soTransakcija;

import domen.GeneralEntity;
import domen.Transakcija;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOKreirajTransakciju extends AbstractGenericOperation{
    
    @Override
    protected void validate(Object entity) throws Exception {
        if(!(entity instanceof Transakcija))
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dbBroker.insertRecord((GeneralEntity) entity);
    }
    
}