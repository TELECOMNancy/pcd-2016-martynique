package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import db.PlaylistDB;
import models.Playlist;

public class testPlaylistDB {
	
	@Before
	public void init(){
		PlaylistDB.dropTable();
		PlaylistDB.createTable();
		
	}
	
	@Test
	public void testPlaylistParser() {
		
		
		Playlist p1 = new Playlist("Disco");
		PlaylistDB.create(p1);
		Playlist p2 = PlaylistDB.getPlaylist(Integer.valueOf(p1.getID()));
		assertEquals(p1.getID(),p2.getID());
		assertEquals(p1.getName(),p2.getName());

		
	}

}
