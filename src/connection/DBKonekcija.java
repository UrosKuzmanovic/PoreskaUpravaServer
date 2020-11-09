/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import settings.PropertyLoader;

/**
 *
 * @author Ookee
 */
public class DBKonekcija {

    private final Connection konekcija;
    private static DBKonekcija instance;

    private DBKonekcija() throws SQLException {
        String url = PropertyLoader.getInstance().getProperties("url");
        String user = PropertyLoader.getInstance().getProperties("user");
        String password = PropertyLoader.getInstance().getProperties("password");
        konekcija = DriverManager.getConnection(url, user, password);
        konekcija.setAutoCommit(false);
    }

    public static DBKonekcija getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBKonekcija();
        }
        return instance;
    }

    public void pocniTransakciju() throws SQLException {
        konekcija.setAutoCommit(false);
    }

    public Connection getKonekcija() {
        return konekcija;
    }

    public void zatvoriKonekciju() throws SQLException {
        konekcija.close();
    }

    public void commit() throws SQLException {
        konekcija.commit();
    }

    public void rollback() throws SQLException {
        konekcija.rollback();
    }

}
