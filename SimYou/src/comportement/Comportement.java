package comportement;

import java.util.ArrayList;
import java.util.Random;

import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class Comportement {
	
	private int probaLike;
	private int probaDislike;
	private int probaCommenter;
	private int probaAbonner;
	
	public Comportement(int probaLike, int probaDislike, int probaCommenter, int probaAbonner) {
		probaLike = probaLike;
		probaDislike = probaDislike;
		probaCommenter = probaCommenter;
		probaAbonner = probaAbonner;
	}
	
	public void visionner(Utilisateur visionneur, Youtube youtube) {
		ArrayList<Utilisateur> utilisateurs = youtube.getUtilisateurs();
		int index = new Random().nextInt(utilisateurs.size());
		Utilisateur proprietaire = utilisateurs.get(index);
		ArrayList<Video> videos = proprietaire.getChaine().getVideos();
		index = new Random().nextInt(videos.size());
		Video videoVisionnee = videos.get(index);
		
		visionneur.mettrePause(videoVisionnee.getDuree());
		int proba = new Random().nextInt(100);
		if (proba <= probaLike) {
			liker();
		}
		else {
			proba = new Random().nextInt(100);
			if (proba <= probaDislike) {
				disliker();
			}
		}
		proba = new Random().nextInt(100);
		if (proba <= probaCommenter) {
			commenter();
		}
		proba = new Random().nextInt(100);
		if (proba <= probaAbonner) {
			abonner();
		}
		visionneur.mettrePause(100);
	}
	
	public void liker() {
		
	}
	
	public void disliker() {
		
	}

	public void commenter() {
		
	}
	
	public void abonner() {
		
	}
}
