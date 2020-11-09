/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soVrstaRacuna;

import domen.GeneralEntity;
import domen.VrstaRacuna;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOVratiVrstuRacuna extends AbstractGenericOperation {
    
    private GeneralEntity vrstaRacuna;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof VrstaRacuna)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        vrstaRacuna = dbBroker.findRecord((GeneralEntity) entity);
    }

    public GeneralEntity getVrstaRacuna() {
        return vrstaRacuna;
    }

}
