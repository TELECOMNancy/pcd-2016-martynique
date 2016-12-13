package utils;

import java.io.File;
import java.net.URL;
import com.github.axet.vget.VGet;

/**
 *
 * @author Manindar
 */
public class YTD {

    public static void download(String ID, String path) {
        System.out.println(ID + "   " + path);
        try {
            String url = "https://www.youtube.com/watch?v=" + ID;
            VGet v = new VGet(new URL(url), new File(path));
            v.download();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void download(String ID) {
        download(ID, System.getProperty("user.dir") + "\\savedVideos\\");
    }
    
    
    public static void main(String[] args) {
        try {
            // ex: "http://www.youtube.com/watch?v=Nj6PFaDmp6c"
            String url = "http://www.youtube.com/watch?v=Nj6PFaDmp6c";
            // ex: "/Users/axet/Downloads"
            String path = "E:\\workspace\\pcd-2016-martynique\\savedVideos";
            VGet v = new VGet(new URL(url), new File(path));
            v.download();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}