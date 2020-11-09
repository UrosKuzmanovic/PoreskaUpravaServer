/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ookee
 */
public class PropertyLoader {

    private static PropertyLoader instance;
    private Properties properties;

    public PropertyLoader() {
        loadProperties();
    }

    public static PropertyLoader getInstance() {
        if (instance == null) {
            instance = new PropertyLoader();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            properties = new Properties();
            properties.load(new FileReader(new File("database.properties")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertyLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertyLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key, "n/a");
    }
    
    public void setProperties(String key, String value) {
        properties.setProperty(key, value);
    }
    
    public void saveProperty(){
        try {
            properties.store(new FileOutputStream("database.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertyLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertyLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
