/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soObaveza;

import domen.GeneralEntity;
import domen.Obaveza;
import domen.Racun;
import so.AbstractGenericOperation;
import soRacun.SOVratiRacun;

/**
 *
 * @author Ookee
 */
public class SOVratiObavezu extends AbstractGenericOperation {

    private GeneralEntity obaveza;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Obaveza)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        obaveza = dbBroker.findRecord((GeneralEntity) entity);
        Obaveza o = (Obaveza) obaveza;
        AbstractGenericOperation op = new SOVratiRacun();
        op.templateExecute(new Racun(o.getRacun().getObveznik(), o.getRacun().getRacun()));
        GeneralEntity racun = ((SOVratiRacun) op).getRacun();
        Racun r = (Racun) racun;
        o.setRacun(r);
    }

    public GeneralEntity getObaveza() {
        return obaveza;
    }

}
