package utils;

import java.io.File;
import java.net.URL;
import com.github.axet.vget.VGet;

import controllers.*;

/**
 *
 * @author Manindar
 */
@SuppressWarnings("restriction")
public class YTD {

    public static void download(String ID, String path, AppController ctrl) {
        System.out.println(ID + "   " + path);
        try {
            String url = "https://www.youtube.com/watch?v=" + ID;
            VGet v = new VGet(new URL(url), new File(path));
            v.download();
        } catch (Exception e) {
            ctrl.warning("Video couldn't donwload");
            /*
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("I have a great message for you!");

            alert.showAndWait();
            */
            
            throw new RuntimeException(e);
        }
    }
    
    public static void download(String ID, AppController ctrl) {
        download(ID, System.getProperty("user.dir") + "\\savedVideos\\", ctrl);
    }
}