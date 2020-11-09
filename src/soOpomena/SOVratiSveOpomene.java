/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soOpomena;

import domen.GeneralEntity;
import domen.Opomena;
import domen.Rata;
import java.util.List;
import so.AbstractGenericOperation;
import soRata.SOVratiRatu;

/**
 *
 * @author Ookee
 */
public class SOVratiSveOpomene extends AbstractGenericOperation{
    
    private List<GeneralEntity> opomene;
    
    @Override
    protected void validate(Object entity) throws Exception {
        if(!(entity instanceof Opomena))
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
    }

    @Override
    protected void execute(Object entity) throws Exception {
        opomene = dbBroker.getAllRecords((GeneralEntity) entity);
        for (GeneralEntity generalEntity : opomene) {
            Opomena o = (Opomena) generalEntity;
            AbstractGenericOperation op = new SOVratiRatu();
            op.templateExecute(new Rata(o.getRata().getBrRate(), o.getRata().getResenje()));
            GeneralEntity rata = ((SOVratiRatu)op).getRata();
            Rata r = (Rata) rata;
            o.setRata(r);
        }
    }

    public List<GeneralEntity> getOpomene() {
        return opomene;
    }
    
}
