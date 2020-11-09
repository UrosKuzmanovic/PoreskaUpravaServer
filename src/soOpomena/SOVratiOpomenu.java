/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soOpomena;

import domen.GeneralEntity;
import domen.Opomena;
import domen.Rata;
import so.AbstractGenericOperation;
import soRata.SOVratiRatu;

/**
 *
 * @author Ookee
 */
public class SOVratiOpomenu extends AbstractGenericOperation {

    private GeneralEntity opomena;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Opomena)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        opomena = dbBroker.findRecord((GeneralEntity) entity);
        Opomena o = (Opomena) opomena;
        AbstractGenericOperation op = new SOVratiRatu();
        op.templateExecute(new Rata(o.getRata().getBrRate(), o.getRata().getResenje()));
        GeneralEntity rata = ((SOVratiRatu) op).getRata();
        Rata r = (Rata) rata;
        o.setRata(r);
    }

    public GeneralEntity getOpomena() {
        return opomena;
    }

}
