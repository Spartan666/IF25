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
		int index = new Random().nextInt(videos.size());
		Video videoVisionnee = videos.get(index);		//Initialisation avec vid�o al�atoire si toutes les vid�os sont d�j� vues.
		
		int maxNbDislikes = 0;
		while (iteratorV.hasNext()) {
			Video videoActuelle = iteratorV.next();
			if (videoActuelle.getNbDislikes() > maxNbDislikes) {
				maxNbDislikes = videoActuelle.getNbDislikes();
				//Test si vid�o pas encore vue
				if (!visionneur.getVideosVues().contains(videoActuelle)) {	
					videoVisionnee = videoActuelle;
				}
			}
		}
		
		visionner(visionneur, videoVisionnee, videoVisionnee.getChaine());
		
	}
}
