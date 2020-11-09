/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soTransakcija;

import domen.GeneralEntity;
import domen.Racun;
import domen.Transakcija;
import java.util.List;
import so.AbstractGenericOperation;
import soRacun.SOVratiRacun;

/**
 *
 * @author Ookee
 */
public class SOVratiSveTransakcije extends AbstractGenericOperation {

    private List<GeneralEntity> transakcije;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Transakcija)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        transakcije = dbBroker.getAllRecords((GeneralEntity) entity);
        for (GeneralEntity generalEntity : transakcije) {
            Transakcija t = (Transakcija) generalEntity;
            AbstractGenericOperation op = new SOVratiRacun();
            op.templateExecute(new Racun(t.getRacun().getObveznik(), t.getRacun().getRacun()));
            GeneralEntity racun = ((SOVratiRacun) op).getRacun();
            Racun r = (Racun) racun;
            t.setRacun(r);
        }
    }

    public List<GeneralEntity> getTransakcije() {
        return transakcije;
    }

}
