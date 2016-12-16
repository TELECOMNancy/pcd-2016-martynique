package utils;

import com.github.axet.vget.VGet;
import db.VideoDB;
import models.Video;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Manindar
 */
public class YTD {

    public static void exec(String cmd) {
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static boolean download(Video v, String path) {
        System.out.println("#DOWNLOADING '" + v.getTitle() + "'");
        Path filePath = Paths.get(path);
        boolean status = false;
        try {
            String url = "https://www.youtube.com/watch?v=" + v.getID();
            VGet vget = new VGet(new URL(url), new File(filePath.toString()));
            vget.download();
            status = true;
            VideoDB.setDownloaded(v, true);
            
            String fileName = v.getTitle();
            
            String savePath = app.Configuration.getInstance().getSavePath();
                        
            File oldVideo =new File(Paths.get(savePath, fileName+".mp4").toString());
            File newVideo =new File(Paths.get(savePath, "video.mp4").toString());
            
            File oldAudio =new File(Paths.get(savePath, fileName+".webm").toString());
            File newAudio =new File(Paths.get(savePath, "audio.webm").toString());

            if(oldAudio.renameTo(newAudio) && oldVideo.renameTo(newVideo) ){
                // la video a ete telecharger en deux fichiers, un mp4 avec la video et un webm avec le son
                // on utilise ffmpeg pour les remultiplexer ensemble
                String ffmpegCmd = "ffmpeg -i \""+ newVideo.getAbsolutePath() +"\" -i \""+ newAudio.getAbsolutePath()+ "\" -c:v copy -c:a aac -strict experimental \"" + Paths.get(savePath, fileName+".mp4") + "\"";
                //System.out.println(ffmpegCmd);
                Runtime.getRuntime().exec(ffmpegCmd).waitFor();
                
                // on supprime les fichiers intermediaires
                newVideo.delete();
                newAudio.delete();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return status;
    }

}