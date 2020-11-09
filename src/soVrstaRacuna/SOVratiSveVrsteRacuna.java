/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soVrstaRacuna;

import domen.GeneralEntity;
import domen.VrstaRacuna;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOVratiSveVrsteRacuna extends AbstractGenericOperation {
    
    private List<GeneralEntity> vrsteRacuna;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof VrstaRacuna)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        vrsteRacuna = dbBroker.getAllRecords((GeneralEntity) entity);
    }

    public List<GeneralEntity> getVrsteRacuna() {
        return vrsteRacuna;
    }

}
