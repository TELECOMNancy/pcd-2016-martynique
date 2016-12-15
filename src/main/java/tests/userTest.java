package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import models.User;
import models.Video;

public class userTest {

    private User user;
    
    private static Video vid1;
    private static Video vid2;
    
    @BeforeClass
    public static void setupAll() {        
        vid1 = new Video("vid1", "url1", "code1");
        vid2 = new Video("vid2", "url2", "code2");
        vid1.setFavorite(true);
        vid2.setFavorite(true);
    }
    
    @Before
    public void setup() {
        user = new User();
    }
    
    
    @Test
    public void test() {
        assertFalse(user.hasFavorite(vid1));
        assertFalse(user.hasFavorite(vid2));
        
        user.addFavorite(vid1);
        
        assertTrue(user.hasFavorite(vid1));
        assertFalse(user.hasFavorite(vid2));
        
        user.addFavorite(vid2);
        
        assertTrue(user.hasFavorite(vid1));
        assertTrue(user.hasFavorite(vid2));
        
        user.removeFavorite(vid2);
        
        assertTrue(user.hasFavorite(vid1));
        assertFalse(user.hasFavorite(vid2));
        
        user.removeFavorite(vid1);
        
        assertFalse(user.hasFavorite(vid1));
        assertFalse(user.hasFavorite(vid2));
    }

}
