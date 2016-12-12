
import java.io.File;
import java.net.URL;
import com.github.axet.vget.VGet;

/**
 *
 * @author Manindar
 */
public class YTD {

    public static void main(String[] args) {
        try {
            String url = "https://www.youtube.com/watch?v=s10ARdfQUOY";
            //String url = "https://www.youtube.com/embed/WldIsP2dUxg"
            String path = System.getProperty("user.dir") + "/savedVideos/";
            VGet v = new VGet(new URL(url), new File(path));
            v.download();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}