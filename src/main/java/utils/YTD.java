package utils;

import com.github.axet.vget.VGet;
import db.VideoDB;
import models.Video;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Manindar
 */
public class YTD {

    public static boolean download(Video v, String path) {
        Path filePath = Paths.get(path);
        boolean status = false;
        try {
            String url = "https://www.youtube.com/watch?v=" + v.getID();
            VGet vget = new VGet(new URL(url), new File(filePath.toString()));
            vget.download();
            status = true;
            VideoDB.setDownloaded(v, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return status;
    }

}