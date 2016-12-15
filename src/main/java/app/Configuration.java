package app;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.google.common.io.Files;

public class Configuration {

    private static Configuration config;

    private final String HEADER = " -- MARTYNIQUE APP --";
    private final String CONFIG_DIR = ".martynique";
    private final String CONFIG_FILE = "config.martynique";
    private final String DB_PATH_KEY = "dbpath";
    private final String DOWNLOADS_KEY = "savepath";
    private final String DB_FILENAME = "martynique.db";
    private final String dir = System.getProperty("user.home");
    private final String SETTINGS_FILEPATH = this.getPath(CONFIG_FILE);

    private Properties props;

    private String defaultDbPath = Paths.get(dir, CONFIG_DIR).toString();
    private String defaultSavePath = Paths.get(dir, CONFIG_DIR,"videos").toString();

    private Configuration() {
        this.props = new Properties();
        File settings = new File(this.SETTINGS_FILEPATH);

        if (!settings.exists()) {
            try {
                Files.createParentDirs(settings);
                this.defaultValues();
                OutputStream out = new FileOutputStream(settings);
                props.store(out, HEADER);

            } catch (Exception e) {  e.printStackTrace();  }
        }

        try {
            this.props.load(new FileInputStream(settings));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.init();
    }

    private void init() {
        new File(this.props.getProperty(DOWNLOADS_KEY)).mkdirs();
    }
    
    public static Configuration getInstance() {
        if (Configuration.config == null)
            Configuration.config = new Configuration();
        return Configuration.config;
    }
    
    public void setSavePath(String sp) {
        this.props.setProperty(this.DOWNLOADS_KEY, sp);
        updateSettings();
    }
    
    public String getSavePath() {
        return this.props.getProperty(this.DOWNLOADS_KEY);
    }
    
    public void setDbPath(String dbp) {
        this.props.setProperty(this.DB_PATH_KEY, dbp);
        updateSettings();
    }
    
    public String getDbLoc() {
        return Paths.get(this.props.getProperty(this.DB_PATH_KEY)).toString();
    }
    
    public String getDbPath() {
        return Paths.get(this.props.getProperty(this.DB_PATH_KEY), this.DB_FILENAME).toString();
    }
    
    public void updateSettings() {
        try {
            this.props.store(new FileOutputStream(this.SETTINGS_FILEPATH), HEADER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void defaultValues() throws IOException {
        this.props.setProperty(DB_PATH_KEY, this.defaultDbPath);
        this.props.setProperty(DOWNLOADS_KEY, this.defaultSavePath);
        new File(this.props.getProperty(DOWNLOADS_KEY)).mkdirs();
        updateSettings();
    }

    private String getPath(String entry) {
        return Paths.get(dir, CONFIG_DIR, entry).toString();
    }
    
    public void purgeVideoDirectory() {
        File dir = new File(this.props.getProperty(DOWNLOADS_KEY));
        for (File file: dir.listFiles()) {
            file.delete();
        }
    }

}
