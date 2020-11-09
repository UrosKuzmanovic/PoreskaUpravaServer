/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soRacun;

import domen.GeneralEntity;
import domen.Obveznik;
import domen.Racun;
import domen.VrstaRacuna;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOVratiRacun extends AbstractGenericOperation {

    private GeneralEntity racun;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Racun)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        racun = dbBroker.findRecord((GeneralEntity) entity);
        Racun r = (Racun) racun;
        r.setObveznik((Obveznik) dbBroker.findRecord(r.getObveznik()));
        r.setRacun((VrstaRacuna) dbBroker.findRecord(r.getRacun()));
    }

    public GeneralEntity getRacun() {
        return racun;
    }

}
