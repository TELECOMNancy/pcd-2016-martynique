package utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.axet.vget.VGet;

import controllers.*;

/**
 *
 * @author Manindar
 */
public class YTD {

    public static void download(String ID, String path) {
        Path filePath = Paths.get(path, ID + ".mp4");
        try {
            String url = "https://www.youtube.com/watch?v=" + ID;
            VGet v = new VGet(new URL(url), new File(filePath.toString()));
            v.download();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}