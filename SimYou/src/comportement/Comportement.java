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
		ArrayList<Utilisateur> utilisateurs = youtube.getUtilisateurs();	//Sélection d'un propriétaire aléatoire
		int index = new Random().nextInt(utilisateurs.size());
		Utilisateur proprietaire = utilisateurs.get(index);
		
		Chaine chaine = proprietaire.getChaine();		//Récupération de la chaîne
		ArrayList<Video> videos = chaine.getVideos();	//Sélection d'une video aléatoire
		index = new Random().nextInt(videos.size());
		Video videoVisionnee = videos.get(index);
		
		visionneur.getAgentLogger().info("Visionne une vidéo : " + videoVisionnee.getTitre() + " " + videoVisionnee.getDuree() + "s");		//Simulation visionnage
		visionneur.mettrePause(videoVisionnee.getDuree());
		int proba = new Random().nextInt(100);
		
		//Test probabilité de liker
		if (proba <= probaLike) {
			liker(visionneur, videoVisionnee);
		}
		//Sinon, test probabilité de disliker
		else {
			proba = new Random().nextInt(100);
			if (proba <= probaDislike) {
				disliker(visionneur, videoVisionnee);
			}
		}
		
		//Test probabilité de commenter
		proba = new Random().nextInt(100);
		if (proba <= probaCommenter) {
			commenter(visionneur, videoVisionnee);
		}
		
		//Test probabilité de s'abonner à la chaîne
		proba = new Random().nextInt(100);
		if (proba <= probaAbonner) {
			abonner(visionneur, chaine);
		}
		visionneur.mettrePause(1000);
		
		videoVisionnee.addVue();
		visionneur.addVideoVue(videoVisionnee); //Ajoute la video à la liste des videos vues
	}
	
	public void liker(Utilisateur visionneur, Video video) {
		video.addLike();
		visionneur.addLikedVideo(video);
		visionneur.getAgentLogger().info("Video likée");
	}
	
	public void disliker(Utilisateur visionneur, Video video) {
		video.addDislike();
		visionneur.addDislikedVideo(video);
		visionneur.getAgentLogger().info("Video dislikée");
	}

	public void commenter(Utilisateur visionneur, Video video) {
		video.addCommentaire();
		visionneur.addVideoCommentee(video);
		visionneur.getAgentLogger().info("Video commentée");
	}
	
	public void abonner(Utilisateur visionneur, Chaine chaine) {
		chaine.addAbonne(visionneur);
		visionneur.addAbonnementChaine(chaine);
		visionneur.getAgentLogger().info("Abonné à la chaîne");
	}
}
