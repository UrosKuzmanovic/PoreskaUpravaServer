/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soTransakcija;

import domen.GeneralEntity;
import domen.Racun;
import domen.Transakcija;
import so.AbstractGenericOperation;
import soRacun.SOVratiRacun;

/**
 *
 * @author Ookee
 */
public class SOVratiTranakciju extends AbstractGenericOperation {

    private GeneralEntity transakcija;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Transakcija)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        transakcija = dbBroker.findRecord((GeneralEntity) entity);
        Transakcija t = (Transakcija) transakcija;
        AbstractGenericOperation op = new SOVratiRacun();
        op.templateExecute(new Racun(t.getRacun().getObveznik(), t.getRacun().getRacun()));
        GeneralEntity racun = ((SOVratiRacun) op).getRacun();
        Racun r = (Racun) racun;
        t.setRacun(r);
    }

    public GeneralEntity getTransakcija() {
        return transakcija;
    }

}
