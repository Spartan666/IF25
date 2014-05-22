package modele;
import java.util.ArrayList;

import madkit.kernel.Agent;
import madkit.kernel.Message;


public class Utilisateur extends Agent{
	
	private Chaine chaine;
	private String nom;
	private String prenom;
	private ArrayList<String> centresInteret;
	private Comportement comportement;
	private ArrayList<Video> videosVues;
	private ArrayList<Video> likedVideos;
	private ArrayList<Video> dislikedVideos;
	private ArrayList<Video> videosCommentees;
	private ArrayList<Chaine> abonnementsChaines;

	
	public Utilisateur(String nom, String prenom,ArrayList<String> centres_interet, String comportement){
		chaine=new Chaine(this);
		nom=nom;
		prenom=prenom;
		centres_interet=centres_interet;
		comportement=comportement;
	}

	public void activate() {
		logger.info("Je me connecte");
		pause(2000);
	}
	
	public void live() {
		// TODO Auto-generated method stub
		while(true){
			pause(1000);
			chaine.getVideos().add(new Video());
			logger.info("nouvelle video");
		}
	}
	
	public void end() {
		// TODO Auto-generated method stub
		System.out.println("Je me deconnecte");
	}
	
	public void Visionner(){
		
	}
}
