package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import models.Playlist;
import models.Video;

public class testPlaylist {
	
	private Playlist p1;
	private Video v1;
	private Video v2;
	List<Video> listvideo= new ArrayList<Video>();
	
	@Before
	public void setup(){
		
		v1 = new Video("track1", "789", "d4qs5d456");
		v2 = new Video("track2", "789741", "d4qs564sdg56df5d456");
		
		listvideo.add(v1);
		listvideo.add(v2);
		p1 = new Playlist("Disco",listvideo);
	}

	@Test
	public void namePlaylist() {
		assertEquals("Disco",p1.getName());
	}
	
	@Test
	public void listVideo() {
		assertEquals(listvideo,p1.getVideoList());
	}

}
