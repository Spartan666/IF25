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
		ArrayList<Utilisateur> utilisateurs = youtube.getUtilisateurs();	//R�cup�ration des utilisateurs
		
		Iterator<Utilisateur> iteratorU = utilisateurs.iterator();
		ArrayList<Chaine> chaines = new ArrayList<Chaine>();
		while (iteratorU.hasNext()) {
			chaines.add(iteratorU.next().getChaine());		//R�cup�ration des cha�nes
		}
		
		Iterator<Chaine> iteratorC = chaines.iterator();
		ArrayList<Video> videos = new ArrayList<Video>();
		while (iteratorC.hasNext()) {
			videos.addAll(iteratorC.next().getVideos());		//R�cup�ration des videos
		}
		
		Iterator<Video> iteratorV = videos.iterator();
		int test = new Random().nextInt(100);
		Video videoVisionnee = videos.get(0);
		
		//Test si s�lection par nombre de vues ou de likes
		if (test <= 50) {
			int maxNbVues = 0;
			while (iteratorV.hasNext()) {
				Video videoActuelle = iteratorV.next();
				if (videoActuelle.getNbVues() > maxNbVues) {
					maxNbVues = videoActuelle.getNbVues();
					videoVisionnee = videoActuelle;
				}
			}
		}
		else {
			int maxNbLikes = 0;
			while (iteratorV.hasNext()) {
				Video videoActuelle = iteratorV.next();
				if (videoActuelle.getNbLikes() > maxNbLikes) {
					maxNbLikes = videoActuelle.getNbLikes();
					videoVisionnee = videoActuelle;
				}
			}
		}

		visionner(visionneur, videoVisionnee, videoVisionnee.getChaine());
		
	}
}
