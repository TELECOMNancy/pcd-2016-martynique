package tests;

import org.junit.Before;
import org.junit.Test;

import db.PlaylistLinkDB;
import models.Playlist;
import models.Video;

public class testPlaylistLinkDB {
	
	@Before
	public void init(){
		PlaylistLinkDB.dropTable();
		PlaylistLinkDB.createTable();
		
	}
	
	@Test
	public void testLinkPlaylistVideo(){
		Video v1 = new Video("track1", "thumb", "code");
		Playlist p1;
	}

}
