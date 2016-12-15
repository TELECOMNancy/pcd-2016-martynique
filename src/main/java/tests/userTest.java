package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import models.User;
import models.Video;

public class userTest {

    private User user;
    
    private Video vid1;
    private Video vid2;
    
    @BeforeClass
    public void setupAll() {        
        vid1 = new Video("vid1", "url1", "code1");
        vid2 = new Video("vid2", "url2", "code2");
    }
    
    @Before
    public void setup() {
        user = new User();
    }
    
    
    @Test
    public void testFavorite() {
        assertFalse(user.hasFavorite(vid1));
        assertFalse(user.hasFavorite(vid2));
    }

}
