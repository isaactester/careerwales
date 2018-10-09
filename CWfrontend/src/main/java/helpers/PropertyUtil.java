package helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by isaacthomas on 08/10/2018.
 */
public class PropertyUtil {public String getProperty(String propName){
    String propValue="";

    Properties prop =new Properties();
    String propFileName ="config.properties";

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
    try{
        prop.load(inputStream);
    }catch(IOException e){
        e.printStackTrace();
    }
    if (inputStream==null){
        try{
            throw new FileNotFoundException("property file '"+propFileName + " ' not found in the classpath '");
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    propValue =prop.getProperty(propName);
    return propValue;
}
    public String getElementProperty(String propName){
        String propValue ="";
        Properties prop = new Properties();
        String propFileName = "element.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try{
            prop.load(inputStream);
        }catch(IOException  e){
            e.printStackTrace();
        }
        if (inputStream==null){
            try{
                throw new FileNotFoundException("property file '"+propFileName + " ' not found in the classpath '");
            } catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        propValue=prop.getProperty(propName);
        return propValue;

    }

    public String getHomeElementProperty(String propName){

        String propValue ="";
        Properties prop = new Properties();
        String propFileName = "home.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try{
            prop.load(inputStream);
        }catch(IOException  e){
            e.printStackTrace();
        }
        if (inputStream==null){
            try{
                throw new FileNotFoundException("property file '"+propFileName + " ' not found in the classpath '");
            } catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        propValue=prop.getProperty(propName);
        return propFileName;

    }
}

