/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soObaveza;

import domen.GeneralEntity;
import domen.Obaveza;
import domen.Racun;
import java.util.List;
import so.AbstractGenericOperation;
import soRacun.SOVratiRacun;

/**
 *
 * @author Ookee
 */
public class SOVratiSveObaveze extends AbstractGenericOperation {

    private List<GeneralEntity> obaveze;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Obaveza)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        obaveze = dbBroker.getAllRecords((GeneralEntity) entity);
        for (GeneralEntity generalEntity : obaveze) {
            Obaveza o = (Obaveza) generalEntity;
            AbstractGenericOperation op = new SOVratiRacun();
            op.templateExecute(new Racun(o.getRacun().getObveznik(), o.getRacun().getRacun()));
            GeneralEntity racun = ((SOVratiRacun) op).getRacun();
            Racun r = (Racun) racun;
            o.setRacun(r);
        }
    }

    public List<GeneralEntity> getObaveze() {
        return obaveze;
    }

}
