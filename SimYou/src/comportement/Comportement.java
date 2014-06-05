package comportement;

import java.util.ArrayList;
import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class Comportement {
	
	private int probaLike;
	private int probaDislike;
	private int probaCommenter;
	private int probaAbonner;
	
	public Comportement(int probaLike, int probaDislike, int probaCommenter, int probaAbonner) {
		this.probaLike = probaLike;
		this.probaDislike = probaDislike;
		this.probaCommenter = probaCommenter;
		this.probaAbonner = probaAbonner;
	}
	
	public void visionner(Utilisateur visionneur, Youtube youtube) {
		ArrayList<Utilisateur> utilisateurs = youtube.getUtilisateurs();	//S�lection d'un propri�taire al�atoire
		int index = new Random().nextInt(utilisateurs.size());
		Utilisateur proprietaire = utilisateurs.get(index);
		
		Chaine chaine = proprietaire.getChaine();		//R�cup�ration de la cha�ne
		ArrayList<Video> videos = chaine.getVideos();	//S�lection d'une video al�atoire
		index = new Random().nextInt(videos.size());
		Video videoVisionnee = videos.get(index);
		
		visionneur.getAgentLogger().info("Visionne une vid�o : " + videoVisionnee.getTitre() + " " + videoVisionnee.getDuree() + "s");		//Simulation visionnage
		visionneur.mettrePause(videoVisionnee.getDuree());
		int proba = new Random().nextInt(100);
		
		//Test probabilit� de liker
		if (proba <= probaLike) {
			liker(visionneur, videoVisionnee);
		}
		//Sinon, test probabilit� de disliker
		else {
			proba = new Random().nextInt(100);
			if (proba <= probaDislike) {
				disliker(visionneur, videoVisionnee);
			}
		}
		
		//Test probabilit� de commenter
		proba = new Random().nextInt(100);
		if (proba <= probaCommenter) {
			commenter(visionneur, videoVisionnee);
		}
		
		//Test probabilit� de s'abonner � la cha�ne
		proba = new Random().nextInt(100);
		if (proba <= probaAbonner) {
			abonner(visionneur, chaine);
		}
		visionneur.mettrePause(1000);
		
		videoVisionnee.addVue();
		visionneur.addVideoVue(videoVisionnee); //Ajoute la video � la liste des videos vues
	}
	
	public void liker(Utilisateur visionneur, Video video) {
		video.addLike();
		visionneur.addLikedVideo(video);
		visionneur.getAgentLogger().info("Video lik�e");
	}
	
	public void disliker(Utilisateur visionneur, Video video) {
		video.addDislike();
		visionneur.addDislikedVideo(video);
		visionneur.getAgentLogger().info("Video dislik�e");
	}

	public void commenter(Utilisateur visionneur, Video video) {
		video.addCommentaire();
		visionneur.addVideoCommentee(video);
		visionneur.getAgentLogger().info("Video comment�e");
	}
	
	public void abonner(Utilisateur visionneur, Chaine chaine) {
		chaine.addAbonne(visionneur);
		visionneur.addAbonnementChaine(chaine);
		visionneur.getAgentLogger().info("Abonn� � la cha�ne");
	}
}
