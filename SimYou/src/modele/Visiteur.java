package modele;

import java.util.ArrayList;
import java.util.Random;

import madkit.kernel.Agent;
import madkit.kernel.AgentLogger;
import comportement.Comportement;
import controleur.Controleur;

public class Visiteur extends Agent {

	public static Youtube youtube;
	
	public void activate() {
		this.logger.info("Visiteur en ligne");
		pause(2000);
	}
	
	public void live() {
		// TODO Auto-generated method stub
			int nbVisionnages = new Random().nextInt(3) + 1;
			for (int i = 1; i <= nbVisionnages; i++) {
				selectionnerVideo(youtube);
			}
	}
	
	public void end() {
		// TODO Auto-generated method stub
		this.logger.info("Visiteur hors ligne");
	}

	
	public void selectionnerVideo(Youtube youtube) {
		ArrayList<Utilisateur> utilisateurs = youtube.getUtilisateurs();	//Sélection d'un propriétaire (de chaîne) aléatoire
		int index = new Random().nextInt(utilisateurs.size());
		Utilisateur proprietaire = utilisateurs.get(index);
		
		Chaine chaine = proprietaire.getChaine();		//Récupération de la chaîne
		ArrayList<Video> videos = chaine.getVideos();	//Sélection d'une video aléatoire
		if (videos.size() != 0) {
			index = new Random().nextInt(videos.size());
			Video videoVisionnee = videos.get(index);
			visionner(videoVisionnee);
		}
		else {
			selectionnerVideo(youtube);
		}
	}
	
	public void visionner(Video video) {
		//Test si le visionneur a l'age requis
		if (video.getAgeRequis() > 13) {
			this.logger.info("Visionne une video : " + video.getTitre() + " " + video.getDuree() + "s");		//Simulation visionnage
			pause(video.getDuree() * 1000 / Controleur.vitesse);
			pause(5);		
			video.addVue();
		}
		else {
			this.logger.info("Trop jeune pour la video");
		}
	}
}
