/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soRata;

import domen.GeneralEntity;
import domen.Obaveza;
import domen.Rata;
import java.util.List;
import so.AbstractGenericOperation;
import soObaveza.SOVratiObavezu;

/**
 *
 * @author Ookee
 */
public class SOVratiSveRate extends AbstractGenericOperation {

    private List<GeneralEntity> rate;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Rata)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        rate = dbBroker.getAllRecords((GeneralEntity) entity);
        for (GeneralEntity generalEntity : rate) {
            Rata r = (Rata) generalEntity;
            AbstractGenericOperation op = new SOVratiObavezu();
            op.templateExecute(new Obaveza(r.getResenje().getBrResenja()));
            GeneralEntity obaveza = ((SOVratiObavezu) op).getObaveza();
            Obaveza o = (Obaveza) obaveza;
            r.setResenje(o);
        }
    }

    public List<GeneralEntity> getRate() {
        return rate;
    }

}
