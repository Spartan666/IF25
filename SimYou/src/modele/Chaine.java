package modele;

import java.util.ArrayList;

public class Chaine {
	
	private ArrayList<Video> videos = new ArrayList<Video>();
	private ArrayList<Utilisateur> abonnes = new ArrayList<Utilisateur>();
	private Utilisateur proprietaire;
	

	public Utilisateur getProprietaire() {
		return proprietaire;
	}


	public Chaine(Utilisateur proprietaire){
		this.proprietaire = proprietaire;
	}
	
	public ArrayList<Video> getVideos(){
		return videos;
	}
	
	public void addVideo(Video video){
		this.videos.add(video);
	}

	public ArrayList<Utilisateur> getAbonnes() {
		return abonnes;
	}

	public void addAbonne (Utilisateur utilisateur) {
		this.abonnes.add(utilisateur);
	}
	
}
