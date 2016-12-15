package app;

import java.io.File;
import java.nio.charset.Charset;

import com.google.common.io.Files;

public class Configuration {

    private static Configuration config;
    
    private String savePath;
    private String dbPath;
    private String dir = System.getProperty("user.dir").replaceAll("\\\\", "/");
    private CharSequence defaultSettings = "dbpath="+ dir + "/db/\nsavepath="+ dir +"/savedVideos/";
    
    private Configuration() {
        File settings = new File(dir + "/settings.properties");
        if (! settings.exists()) {
            try {
                settings.createNewFile();
                Files.write(defaultSettings, settings, Charset.forName("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        try {
            // gets directories
            dbPath=Files.readLines(settings, Charset.forName("UTF-8")).get(0).replaceAll("dbpath=", "");
            savePath=Files.readLines(settings, Charset.forName("UTF-8")).get(1).replaceAll("savepath=", "");
            
            // creates directories if they don't exist
            File dbDir = new File(dbPath);
            File saveDir = new File(savePath);
            dbDir.mkdirs();
            saveDir.mkdirs();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static Configuration getInstance() {
        if (Configuration.config == null)
            Configuration.config = new Configuration();
        return Configuration.config;
    }
    
    public void setSavePath(String sp) {
        Configuration.config.savePath = sp;
        updateSettings();
    }
    
    public String getSavePath() {
        return savePath;
    }
    
    public void setDbPath(String dbp) {
        Configuration.config.dbPath = dbp;
        updateSettings();
    }
    
    public String getDbPath() {
        return dbPath;
    }
    
    private void updateSettings() {
        File settings = new File(dir + "/settings.properties");
        
        CharSequence newSettings = "dbpath="+ dbPath +"\nsavepath="+ savePath;
        
        try {
            settings.createNewFile();
            Files.write(newSettings, settings, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void resetSettings() {
        File settings = new File(dir + "/settings.properties");
        try {
            settings.createNewFile();
            Files.write(defaultSettings, settings, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
