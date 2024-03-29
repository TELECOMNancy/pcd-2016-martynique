package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Playlist implements Identifiable {

	private ID id;
	private String name;
	List<Video> videoList;
	
	public Playlist(String newname){
		this.name = newname;
		this.videoList = new ArrayList<Video>();
	}
	
	public Playlist(String newname, List<Video> liste){
		this.name = newname;
		this.videoList = liste;
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

	public String getRandomThumbnail() {
		if(this.videoList.size() == 0)
			return null;
		else {
			Random rand = new Random();
			if(this.videoList.size() != 0) {
				int x = rand.nextInt(this.videoList.size()) + this.videoList.size() - 1;
				return this.videoList.get(x).getThumbnail();
			}
			else
				return null;
		}

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
	public String getID() {
		return this.id.getValue();
	}

	@Override
	public void setID(ID id) {
		this.id = id;
	}

	public String toString() {
		return this.getName() + ": " + this.videoList.toString();
	}

	public boolean equals(Object o) {
		if(o != null && o instanceof Playlist) {
			Playlist tmp = (Playlist) o;
			return this.getName().equals(tmp.getName());
		}
		return false;
	}


}
