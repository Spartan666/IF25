package modele;
import java.util.ArrayList;

import madkit.kernel.Agent;
import madkit.kernel.AgentLogger;
import madkit.kernel.Message;
import comportement.Comportement;
import comportement.ComportementLambda;
import controleur.Controleur;

public class Utilisateur extends Agent{
	
	private Chaine chaine;
	private String nom;
	private String prenom;
	private int age;
	private ArrayList<String> centresInteret;
	private Comportement comportement;
	private ArrayList<Video> videosVues = new ArrayList<Video>();
	private ArrayList<Video> likedVideos = new ArrayList<Video>();
	private ArrayList<Video> dislikedVideos = new ArrayList<Video>();
	private ArrayList<Video> videosCommentees = new ArrayList<Video>();
	private ArrayList<Chaine> abonnementsChaines = new ArrayList<Chaine>();
	
	public static Youtube youtube;

	
	public Utilisateur(String nom, String prenom, int age, ArrayList<String> centresInteret, Comportement comportement){
		this.chaine = new Chaine(this);
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.centresInteret = centresInteret;
		this.comportement = comportement;
	}
	
	public Chaine getChaine() {
		return chaine;
	}
	
	public ArrayList<Video> getVideosVues() {
		return videosVues;
	}
	
	public void addVideoVue(Video video){
		this.videosVues.add(video);
	}
	
	public ArrayList<Video> getLikedVideos() {
		return likedVideos;
	}
	
	public void addLikedVideo(Video video){
		this.likedVideos.add(video);
	}
	
	public ArrayList<Video> getDislikedVideos() {
		return dislikedVideos;
	}
	
	public void addDislikedVideo(Video video){
		this.dislikedVideos.add(video);
	}
	
	public ArrayList<Video> getVideosCommentees() {
		return videosCommentees;
	}
	
	public void addVideoCommentee(Video video){
		this.videosCommentees.add(video);
	}
	
	public ArrayList<Chaine> getAbonnementsChaines() {
		return abonnementsChaines;
	}
	
	public void addAbonnementChaine(Chaine chaine){
		this.abonnementsChaines.add(chaine);
	}
	
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	
	public int getAge() {
		return age;
	}

	public ArrayList<String> getCentresInteret() {
		return centresInteret;
	}

	public Comportement getComportement() {
		return comportement;
	}
	
	//Permet d'utiliser le logger dans la classe Comportement
	public AgentLogger getAgentLogger() {	
		return logger;
	}

	//Permet d'utiliser la méthode pause() dans la classe Comportement
	public void mettrePause(int duree) {	
		pause(duree * 1000 / Controleur.vitesse);
	}
	
	//Méthode appellée au lancement de l'agent
	public void activate() {
		this.logger.info("Je me connecte (Age : " + this.getAge() + " ans) " + this.getCentresInteret());
		pause(2000);
	}
	
	//Méthode de vie de l'agent
	public void live() {
		// TODO Auto-generated method stub
		while(true){
			this.comportement.selectionnerVideo(this, youtube);
		}
	}
	
	//Méthode appellée à la mort de l'agent
	public void end() {
		// TODO Auto-generated method stub
		this.logger.info("Je me déconnecte");
	}

}
