/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import database.DBBroker;
import database.IDBBroker;
import connection.DBKonekcija;

/**
 *
 * @author Ookee
 */
public abstract class AbstractGenericOperation {
    
    protected IDBBroker dbBroker;

    public AbstractGenericOperation() {
        dbBroker = new DBBroker();
    }

    public final void templateExecute(Object entity) throws Exception {
        try {
            validate(entity);
            try {
                startTransaction();
                execute(entity);
                commitTransaction();
            } catch (Exception ex) {
                rollbackTransaction();
                throw ex;
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            throw ex;
        }
    }

    protected abstract void validate(Object entity) throws Exception;

    private void startTransaction() throws Exception {
        DBKonekcija.getInstance().pocniTransakciju();
    }

    protected abstract void execute(Object entity) throws Exception;

    private void commitTransaction() throws Exception {
        DBKonekcija.getInstance().commit();
    }

    private void rollbackTransaction() throws Exception {
        DBKonekcija.getInstance().rollback();
    }
    
}
