package tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.Configuration;
import db.ConnectionDB;
import db.VideoDB;
import models.Video;

public class testVideoDB {
	
	
	
	@Before
	public void createTable(){
		VideoDB.dropTable();
		VideoDB.createTable();
		
	}

	@Test
	public void testVideoParser() {
		Video v1 = new Video("track1", "789", "d4f65sdf65");
		VideoDB.create(v1);
		Video v2 = VideoDB.findById("d4f65sdf65");
		assertEquals(v2.getTitle(),v1.getTitle());
		assertEquals(v2.getThumbnail(),v1.getThumbnail());
		assertEquals(v2.getID(),v1.getID());
	}
	
	@Test
	public void testSetFavorite(){
		Video v1 = new Video("track1", "789", "d4f65sdf652");
		v1.setFavorite(true);
		VideoDB.create(v1);
		Video v2 = VideoDB.findById("d4f65sdf652");
		assertTrue(v2.isFavorite());
	}
	
	
	


}
