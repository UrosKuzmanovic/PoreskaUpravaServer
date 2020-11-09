/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soObveznik;

import domen.GeneralEntity;
import domen.Obveznik;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOKreirajObveznika extends AbstractGenericOperation {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Obveznik)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dbBroker.insertRecordReturnId((GeneralEntity) entity);
    }

}
