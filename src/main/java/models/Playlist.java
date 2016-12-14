package models;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Identifiable{

	
	private int id;
	private String name;
	List<Video> videoList;
	
	public Playlist(String newname){
		this.name = newname;
		this.videoList = new ArrayList<Video>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String newname){
		this.name = newname;
	}
	
	public void setVideoList(List<Video> newlist){
		this.videoList = newlist;
	}
	
	public List<Video> getVideoList(){
		return videoList;
	}
	
	public Playlist(String newname,ArrayList<Video> newvideoList){
		this.name = newname;
		this.videoList = newvideoList;
	}
	
	public void addVideo(Video vid){
		if(!this.videoList.contains(vid))
			this.videoList.add(vid);
		else
			System.out.println("La video existe déja dans la playlist");
	}
	
	public void delVideo(Video vid){
		if(this.videoList.contains(vid))
			this.videoList.remove(vid);
		else
			System.out.println("La video n'existe pas dans la playlist");
			
	}
	

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void setID(int id) {
		this.id = id;
		
	}

}
