import db.PlaylistDB;
import db.PlaylistLinkDB;
import models.Playlist;
import models.Video;

public class testDB {

	public static void main(String[] args) {
		System.out.println("CREATE");
		Playlist p1 = PlaylistDB.importVideo(1);
		
		System.out.println("Playlist : "+p1.getName());
		for( Video i : p1.getVideoList()){
			System.out.println("Title "+i.getTitle());
		}
		System.out.println("CREATED");

	}

}
