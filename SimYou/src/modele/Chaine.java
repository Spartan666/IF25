package modele;

import java.util.ArrayList;

public class Chaine {
	
	private ArrayList<Video> videos=new ArrayList<Video>();
	private ArrayList<Utilisateur> abonnes= new ArrayList<Utilisateur>();
	private Utilisateur proprietaire;
	
	public Chaine(Utilisateur proprietaire){
		proprietaire=proprietaire;
	}
	
	public ArrayList<Video> getVideos(){
		return videos;
	}
	
	public void addVideos(Video video){
		videos.add(video);
	}
	
}
