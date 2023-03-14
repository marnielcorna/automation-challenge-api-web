package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    FileInputStream fis = null;
    Properties pro = null;
    String filepath = System.getProperty("user.dir")+"/config.properties";

    public PropertiesReader() {
        try {

            fis = new FileInputStream(filepath);
            pro = new Properties();
            pro.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String getPropValue(String keyvalue) {
        return pro.getProperty(keyvalue);
    }

}
