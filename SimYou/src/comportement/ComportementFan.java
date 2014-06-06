package comportement;

import java.util.ArrayList;
import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class ComportementFan extends Comportement {
	
	public ComportementFan(int probaLike, int probaDislike, int probaCommenter, int bonusProbaCommenter,
			int probaAbonner) {
		super(probaLike, probaDislike, probaCommenter, bonusProbaCommenter, probaAbonner);
		// TODO Auto-generated constructor stub
	}

	public void selectionnerVideo(Utilisateur visionneur, Youtube youtube) {
		ArrayList<Chaine> chainesAbonnees = visionneur.getAbonnementsChaines();
		if (chainesAbonnees.size() != 0) {
			int index = new Random().nextInt(chainesAbonnees.size());
			Chaine chaine = chainesAbonnees.get(index);
			ArrayList<Video> videos = chaine.getVideos();
			if (videos.size() != 0) {
				index = new Random().nextInt(videos.size());
				Video videoVisionnee = videos.get(index);
				visionner(visionneur, videoVisionnee, chaine);
			}
		}
		else {
			super.selectionnerVideo(visionneur, youtube);
		}
	}
}
