package modele;

import java.util.ArrayList;

public class Chaine {
	
	private ArrayList<Video> videos = new ArrayList<Video>();
	private ArrayList<Utilisateur> abonnes = new ArrayList<Utilisateur>();
	private Utilisateur proprietaire;
	
<<<<<<< HEAD
	public Utilisateur getProprietaire() {
		return proprietaire;
	}

=======
>>>>>>> 8f4d81d3b1f0e9cf78ea68a0d9ecaec88eeb60ca
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
