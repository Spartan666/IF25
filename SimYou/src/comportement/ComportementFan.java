package comportement;

import java.util.ArrayList;
import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class ComportementFan extends Comportement {
	
	static int nbinstance=0;
	
	public ComportementFan(int probaVote, int probaLike, int probaCommenter, int bonusProbaCommenter,
			int probaAbonner) {
		super(probaVote, probaLike, probaCommenter, bonusProbaCommenter, probaAbonner);
		// TODO Auto-generated constructor stub
		nbinstance+=1;
	}

	public static int getNbinstance() {
		return nbinstance;
	}

	public void selectionnerVideo(Utilisateur visionneur, Youtube youtube) {
		ArrayList<Chaine> chainesAbonnees = visionneur.getAbonnementsChaines();
		//Test si abonn� � une ou plusieurs cha�nes
		if (chainesAbonnees.size() != 0) {	
			int index = new Random().nextInt(chainesAbonnees.size());	//S�lection d'une cha�ne al�atoire.
			Chaine chaine = chainesAbonnees.get(index);
			
			ArrayList<Video> videos = chaine.getVideos();	//S�lection d'une vid�o al�atoire
			index = new Random().nextInt(videos.size());
			Video videoVisionnee = videos.get(index);
			
			visionner(visionneur, videoVisionnee, chaine);

		}
		//Sinon comportement lambda
		else {	
			super.selectionnerVideo(visionneur, youtube);
		}
	}
}
