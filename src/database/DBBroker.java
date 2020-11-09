/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domen.GeneralEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import connection.DBKonekcija;

/**
 *
 * @author Ookee
 */
public class DBBroker implements IDBBroker {

    @Override
    public List<GeneralEntity> getAllRecords(GeneralEntity entity) throws SQLException {
        List<GeneralEntity> objects = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(entity.getClassName());
        String query = sb.toString();
        System.out.println(query);
        PreparedStatement statement = DBKonekcija.getInstance().getKonekcija().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            objects.add(entity.getNewRecord(rs));
        }
        return objects;
    }

    @Override
    public GeneralEntity findRecord(GeneralEntity entity) throws Exception {
        ResultSet rs;
        Statement st;
        String query = "SELECT * FROM " + entity.getClassName() + " WHERE " + entity.getWhereCondition();
        System.out.println(query);
        boolean signal;
        st = DBKonekcija.getInstance().getKonekcija().prepareStatement(query);
        rs = st.executeQuery(query);
        signal = rs.next();
        if (signal == true) {
            return entity.getNewRecord(rs);
        }
        throw new Exception("Entity does not exist");
    }

    @Override
    public void insertRecord(GeneralEntity entity) throws Exception {
        String upit = "INSERT INTO " + entity.getClassName() + " VALUES (" + entity.getAtrValue() + ")";
        try {
            executeUpdate(upit);
        } catch (Exception ex) {
            throw new Exception("Insert query not executed");
        }
    }

    @Override
    public int insertRecordReturnId(GeneralEntity entity) throws Exception {
        int id;
        try {
            String upit = "INSERT INTO " + entity.getClassName() + " VALUES (" + entity.getAtrValue() + ")";
            System.out.println(upit);
            PreparedStatement statement = DBKonekcija.getInstance().getKonekcija().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return id;
    }

    @Override
    public void deleteRecord(GeneralEntity entity) throws Exception {
        String upit = "DELETE FROM " + entity.getClassName() + " WHERE " + entity.getWhereCondition();
        try {
            executeUpdate(upit);
        } catch (Exception ex) {
            throw new Exception("Delete query not executed");
        }
    }

    @Override
    public void updateRecord(GeneralEntity entity) throws Exception {
        String upit = "UPDATE " + entity.getClassName() + " SET " + entity.setAtrValue() + " WHERE " + entity.getWhereCondition();
        try {
            executeUpdate(upit);
        } catch (Exception ex) {
            throw new Exception("Update query not executed");
        }
    }

    @Override
    public List<GeneralEntity> findRecordByName(GeneralEntity entity) throws Exception {
        List<GeneralEntity> objects = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(entity.getClassName()).append(" WHERE ").append(entity.getSpecialCondition());
        String query = sb.toString();
        System.out.println(query);
        PreparedStatement statement = DBKonekcija.getInstance().getKonekcija().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            objects.add(entity.getNewRecord(rs));
        }
        return objects;
    }

    @Override
    public List<GeneralEntity> findRecordsByForeignKey(GeneralEntity entity) throws Exception {
        List<GeneralEntity> objects = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(entity.getClassName()).append(" WHERE ").append(entity.getForeignKeyCondition());
        String query = sb.toString();
        System.out.println(query);
        PreparedStatement statement = DBKonekcija.getInstance().getKonekcija().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            objects.add(entity.getNewRecord(rs));
        }
        return objects;
    }

    private void executeUpdate(String query) throws Exception {
        System.out.println(query);
        Statement st;
        st = DBKonekcija.getInstance().getKonekcija().prepareStatement(query);
        int rowcount = st.executeUpdate(query);
        if (rowcount <= 0) {
            throw new Exception("Update query not executed");
        }

    }

}
