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
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Ookee
 */
public class SOVratiObveznikoveRacune extends AbstractGenericOperation {

    private List<GeneralEntity> racuni;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Racun)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        racuni = dbBroker.findRecordByName((GeneralEntity) entity);
        for (GeneralEntity generalEntity : racuni) {
            Racun r = (Racun) generalEntity;
            Obveznik o = (Obveznik) dbBroker.findRecord(r.getObveznik());
            VrstaRacuna vr = (VrstaRacuna) dbBroker.findRecord(r.getRacun());
            r.setObveznik(o);
            r.setRacun(vr);
        }
    }

    public List<GeneralEntity> getRacuni() {
        return racuni;
    }
}
