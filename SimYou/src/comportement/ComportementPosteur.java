package comportement;

import java.util.ArrayList;

import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;
import modele.ListeCentresInteret;

public class ComportementPosteur extends Comportement {

	private int periodicitePostage; //Valeur entre 1 (postage pour chaque visionnage) et 10 (postage pour 10 visionnages)
	
	public ComportementPosteur(int probaVote, int probaLike,
			int probaCommenter, int probaAbonner, int bonusProbaCommenter, int periodicitePostage) {
		super(probaVote, probaLike, probaCommenter, bonusProbaCommenter, probaAbonner);
		if (periodicitePostage <= 10) { 
			if (periodicitePostage >= 1) {
				this.periodicitePostage = periodicitePostage;
			}
			else {
				this.periodicitePostage = 1;
			}
		}
		else {
			this.periodicitePostage = 10;
		}
		// TODO Auto-generated constructor stub
	}

	public void selectionnerVideo(Utilisateur utilisateur, Youtube youtube) {
		poster(utilisateur);
		for (int i = 1; i <= periodicitePostage; i++) {
			super.selectionnerVideo(utilisateur, youtube);
		}
	}
	
	public void poster(Utilisateur utilisateur) {
		Chaine chaine = utilisateur.getChaine();	//Postage d'une video de durée entre 30s et 1h
		int duree = new Random().nextInt(3570) + 30;
		int ageRequis = 13 + new Random().nextInt(6); //Age requis entre 13 et 18 ans
		ArrayList<String> tags = new ArrayList<String>();
		int nbTags = 1 + new Random().nextInt(3);
		for (int i = 1; i <= nbTags; i++) {
		    tags.add(ListeCentresInteret.getRandomValue());
		}
		Video video = new Video("video" + chaine.getVideos().size() + utilisateur.getPrenom() + utilisateur.getNom(), "Blabla", duree, tags, chaine, ageRequis); //Création de la video
		chaine.addVideo(video);
		utilisateur.getAgentLogger().info("Video postee : " + video.getTitre() + " " + video.getDuree() + "s Age requis : " + video.getAgeRequis() + " ans " + video.getTags());
		utilisateur.mettrePause(1000);	
	}
	
	
}
