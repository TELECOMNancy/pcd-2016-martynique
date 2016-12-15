package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.Video;

public class testVideo {
    
    private Video vid1;
    private Video vid2;
    
    @Before
    public void setup() {
        vid1 = new Video("vid1", "url1", "code1");
        vid2 = new Video("vid2", "url2", "code2");
    }
    
    @Test
    public void testID() {
        assertTrue(vid1.getID().equals("code1"));
        assertTrue(vid2.getID().equals("code2"));
    }
    
    @Test
    public void testTitle() {
        assertTrue(vid1.getTitle().equals("vid1"));
        assertTrue(vid2.getTitle().equals("vid2"));
    }
    
    @Test
    public void testThumbnail() {
        assertTrue(vid1.getThumbnail().equals("url1"));
        assertTrue(vid2.getThumbnail().equals("url2"));
    }
    
    @Test
    public void testFavorite() {
        assertTrue(vid1.isFavorite() == false);
        assertTrue(vid2.isFavorite() == false);
        
        vid1.setFavorite(true);
        vid2.setFavorite(true);
        
        assertTrue(vid1.isFavorite() == true);
        assertTrue(vid2.isFavorite() == true);
    }
    
    @Test
    public void testToString() {
        assertTrue(vid1.toString().equals("[code1#Video] vid1 - "));
        assertTrue(vid2.toString().equals("[code2#Video] vid2 - "));
        
        vid1.setFavorite(true);
        vid2.setFavorite(true);
        
        assertTrue(vid1.toString().equals("[code1#Video] vid1 - FAV"));
        assertTrue(vid2.toString().equals("[code2#Video] vid2 - FAV"));
    }
    
    @Test
    public void testEquals() {
        Video vid3 = new Video("vid1", "url1", "code1");

        assertTrue(vid1.equals(vid3));
    }

}
