package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import db.PlaylistDB;
import db.PlaylistLinkDB;
import db.VideoDB;
import models.Playlist;
import models.Video;

public class testPlaylistLinkDB {
	
	@Before
	public void init(){
		VideoDB.dropTable();
		VideoDB.createTable();
		PlaylistDB.dropTable();
		PlaylistDB.createTable();
		PlaylistLinkDB.dropTable();
		PlaylistLinkDB.createTable();
		
	}
	
	@Test
	public void testLinkPlaylistVideoParser(){
		
		Video v1 = new Video("track1", "thumb", "code");
		Video v2 = new Video("track2", "thumb2", "code2");
		Video v3 = new Video("track3", "thumb3", "code3");
		
		VideoDB.create(v1);
		VideoDB.create(v2);
		VideoDB.create(v3);
		
		List<Video> listv1= new ArrayList<Video>();
		listv1.add(v1);
		listv1.add(v2);
		listv1.add(v3);
		Playlist p1 = new Playlist("Disco", listv1);
		
		PlaylistDB.create(p1);
		
		List<String> string = PlaylistLinkDB.getVideoIdOfPlaylist(Integer.valueOf(p1.getID()));
		
		List<Video> listv2= new ArrayList<Video>();
		
		for(String v : string){
			System.out.println("Id VIDeo "+v);
			listv2.add(VideoDB.findById(v));
		}
		
		assertEquals(p1.getVideoList().size(),listv2.size());
		
		for(int  i=0; i<p1.getVideoList().size();i++){
			assertEquals(p1.getVideoList().get(i),listv2.get(i));
		}
		
		
	
	}

}
