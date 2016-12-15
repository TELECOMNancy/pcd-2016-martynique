
public class test {

    
    public static void main(String[] args) {
        String savePath = System.getProperty("user.dir") + "\\savedVideos\\";
        
        System.out.println(savePath);
        
        System.out.println(savePath.replaceAll("\\\\", "/"));
    }

}