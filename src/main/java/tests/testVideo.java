package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import models.Video;
import models.VarcharID;

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
        
        vid1.setID(new VarcharID("code1_bis"));
        vid2.setID(new VarcharID("code2_bis"));
        
        assertTrue(vid1.getID().equals("code1_bis"));
        assertTrue(vid2.getID().equals("code2_bis"));
    }
    
    @Test
    public void testTitle() {
        assertTrue(vid1.getTitle().equals("vid1"));
        assertTrue(vid2.getTitle().equals("vid2"));
    }

}
