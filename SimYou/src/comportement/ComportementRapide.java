package comportement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class ComportementRapide extends Comportement {

	public ComportementRapide(int probaLike, int probaDislike,
			int probaCommenter, int bonusProbaCommenter, int probaAbonner) {
		super(probaLike, probaDislike, probaCommenter, bonusProbaCommenter, probaAbonner);
		// TODO Auto-generated constructor stub
	}

	public void selectionnerVideo(Utilisateur visionneur, Youtube youtube) {
		ArrayList<Utilisateur> utilisateurs = youtube.getUtilisateurs();	//Récupération des utilisateurs
		
		Iterator<Utilisateur> iteratorU = utilisateurs.iterator();
		ArrayList<Chaine> chaines = new ArrayList<Chaine>();
		while (iteratorU.hasNext()) {
			chaines.add(iteratorU.next().getChaine());		//Récupération des chaînes
		}
		
		Iterator<Chaine> iteratorC = chaines.iterator();
		ArrayList<Video> videos = new ArrayList<Video>();
		while (iteratorC.hasNext()) {
			videos.addAll(iteratorC.next().getVideos());		//Récupération des videos
		}
		
		Iterator<Video> iteratorV = videos.iterator();
		int test = new Random().nextInt(100);
		int index = new Random().nextInt(videos.size());
		Video videoVisionnee = videos.get(index);		//Initialisation avec vidéo aléatoire si toutes les vidéos sont déjà vues.
		
		//Test si sélection par nombre de vues ou de likes
		if (test <= 50) {
			int maxNbVues = 0;
			while (iteratorV.hasNext()) {
				Video videoActuelle = iteratorV.next();
				if (videoActuelle.getNbVues() > maxNbVues) {
					maxNbVues = videoActuelle.getNbVues();
					//Test si vidéo pas encore vue
					if (!visionneur.getVideosVues().contains(videoActuelle)) {	
						videoVisionnee = videoActuelle;
					}
				}
			}
		}
		else {
			int maxNbLikes = 0;
			while (iteratorV.hasNext()) {
				Video videoActuelle = iteratorV.next();
				if (videoActuelle.getNbLikes() > maxNbLikes) {
					maxNbLikes = videoActuelle.getNbLikes();
					//Test si vidéo pas encore vue
					if (!visionneur.getVideosVues().contains(videoActuelle)) {	
						videoVisionnee = videoActuelle;
					}
				}
			}
		}

		visionner(visionneur, videoVisionnee, videoVisionnee.getChaine());
		
	}
}
