package comportement;

import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class ComportementPosteur extends Comportement {

	public ComportementPosteur(int probaLike, int probaDislike,
			int probaCommenter, int probaAbonner) {
		super(probaLike, probaDislike, probaCommenter, probaAbonner);
		// TODO Auto-generated constructor stub
	}

	public void visionner(Utilisateur utilisateur, Youtube youtube) {
		super.visionner(utilisateur, youtube);
		
	}
	
	public void poster(Utilisateur utilisateur) {
		Chaine chaine = utilisateur.getChaine();
		int duree = new Random().nextInt(18000);
		Video video = new Video("video" + chaine.getVideos().size(), "Blabla", duree);
		chaine.addVideo(video);
		utilisateur.getAgentLogger().info("Video postée");
	}
	
}
