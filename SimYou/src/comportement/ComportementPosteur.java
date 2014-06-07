package comportement;

import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class ComportementPosteur extends Comportement {

	private int periodicitePostage; //Valeur entre 1 (postage pour chaque visionnage) et 10 (postage pour 10 visionnages)
	
	public ComportementPosteur(int probaLike, int probaDislike,
			int probaCommenter, int probaAbonner, int bonusProbaCommenter, int frequencePostage) {
		super(probaLike, probaDislike, probaCommenter, bonusProbaCommenter, probaAbonner);
		if (frequencePostage <= 10) { 
			if (frequencePostage >= 1) {
				this.periodicitePostage = frequencePostage;
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
		Video video = new Video("video" + chaine.getVideos().size() + utilisateur.getPrenom() + utilisateur.getNom(), "Blabla", duree, null, chaine); //Création de la video
		chaine.addVideo(video);
		utilisateur.getAgentLogger().info("Video postee : " + video.getTitre() + " " + video.getDuree() + "s");
	}
	
}
