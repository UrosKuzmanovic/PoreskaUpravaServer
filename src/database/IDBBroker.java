/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domen.GeneralEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ookee
 */
public interface IDBBroker {
    
    List<GeneralEntity> getAllRecords(GeneralEntity entity) throws SQLException;

    GeneralEntity findRecord(GeneralEntity entity) throws Exception;

    List<GeneralEntity> findRecordByName(GeneralEntity entity) throws Exception;
    
    List<GeneralEntity> findRecordsByForeignKey(GeneralEntity entity) throws Exception;

    void insertRecord(GeneralEntity entity) throws Exception;
    
    int insertRecordReturnId(GeneralEntity entity) throws Exception;

    void deleteRecord(GeneralEntity entity) throws Exception;

    void updateRecord(GeneralEntity entity) throws Exception;

}
