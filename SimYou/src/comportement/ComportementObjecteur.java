package comportement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class ComportementObjecteur extends Comportement {

	public ComportementObjecteur(int probaLike, int probaDislike,
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
		int index = new Random().nextInt(videos.size());
		Video videoVisionnee = videos.get(index);		//Initialisation avec vidéo aléatoire si toutes les vidéos sont déjà vues.
		
		int maxNbDislikes = 0;
		while (iteratorV.hasNext()) {
			Video videoActuelle = iteratorV.next();
			if (videoActuelle.getNbDislikes() > maxNbDislikes) {
				maxNbDislikes = videoActuelle.getNbDislikes();
				//Test si vidéo pas encore vue
				if (!visionneur.getVideosVues().contains(videoActuelle)) {	
					videoVisionnee = videoActuelle;
				}
			}
		}
		
		visionner(visionneur, videoVisionnee, videoVisionnee.getChaine());
		
	}
}
