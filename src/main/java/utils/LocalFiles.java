package utils;

import app.Configuration;

import java.io.File;
import java.io.FilenameFilter;


public class LocalFiles {

    public static File[] getDownloads() {
        File path = new File(Configuration.getInstance().getSavePath());
        System.out.println(path);
        File[] matchingFiles = path.listFiles(new FilenameFilter(){
            public boolean accept(File dir, String name){
                return name.endsWith(".mp4");
            }
        });

        return matchingFiles;
    }
}
