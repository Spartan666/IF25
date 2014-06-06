package comportement;

import java.util.ArrayList;
import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class Comportement {
	
	private int probaVote;
	private int probaLike;
	private int probaCommenter;
	private int bonusProbaCommenter;
	private int probaAbonner;
	
	public Comportement(int probaVote, int probaLike, int probaCommenter, int bonusProbaCommenter, int probaAbonner) {
		if (probaVote >= 0) {
			if (probaVote <= 100) {
				this.probaVote = probaVote;
			}
			else {
				this.probaVote = 100;
			}
		}
		else {
			this.probaVote = 0;
		}
		
		if (probaLike >= 0) {
			if (probaLike <= 100) {
				this.probaLike = probaLike;
			}
			else {
				this.probaLike = 100;
			}
		}
		else {
			this.probaLike = 0;
		}
		
		if (probaCommenter >= 0) {
			if (probaCommenter <= 100) {
				this.probaCommenter = probaCommenter;
			}
			else {
				this.probaCommenter = 100;
			}
		}
		else {
			this.probaCommenter = 0;
		}
		
		if (bonusProbaCommenter >= 0) {
			if (bonusProbaCommenter <= 25) {
				this.bonusProbaCommenter = bonusProbaCommenter;
			}
			else {
				this.bonusProbaCommenter = 25;
			}
		}
		else {
			this.bonusProbaCommenter = 0;
		}
		
		if (probaAbonner >= 0) {
			if (probaAbonner <= 100) {
				this.probaAbonner = probaAbonner;
			}
			else {
				this.probaAbonner = 100;
			}
		}
		else {
			this.probaAbonner = 0;
		}


	}
	
	public void selectionnerVideo(Utilisateur visionneur, Youtube youtube) {
		ArrayList<Utilisateur> utilisateurs = youtube.getUtilisateurs();	//S�lection d'un propri�taire (de cha�ne) al�atoire
		int index = new Random().nextInt(utilisateurs.size());
		Utilisateur proprietaire = utilisateurs.get(index);
		
		Chaine chaine = proprietaire.getChaine();		//R�cup�ration de la cha�ne
		ArrayList<Video> videos = chaine.getVideos();	//S�lection d'une video al�atoire
		index = new Random().nextInt(videos.size());
		Video videoVisionnee = videos.get(index);
		visionner(visionneur, videoVisionnee, chaine);

	}
	
	public void visionner(Utilisateur visionneur, Video video, Chaine chaine) {
		visionneur.getAgentLogger().info("Visionne une vid�o : " + video.getTitre() + " " + video.getDuree() + "s");		//Simulation visionnage
		visionneur.mettrePause(video.getDuree());
		int proba = new Random().nextInt(100);
		
		//Test probabilit� de voter
		if (proba <= probaVote) {
			proba = new Random().nextInt(100);
			//Test probabilit� de liker
			if (proba <= probaLike) {
				liker(visionneur, video);
				//Test probabilit� de commenter
				proba = new Random().nextInt(100);
				int probaBonus = new Random().nextInt(25);
				proba += probaBonus;
				if (proba <= probaCommenter + bonusProbaCommenter) {
					commenter(visionneur, video);
				}
				//Test probabilit� de s'abonner � la cha�ne
				proba = new Random().nextInt(100);
				if (proba <= probaAbonner) {
					abonner(visionneur, chaine);
				}
			}
			//Sinon dislike
			else {
				//Test probabilit� de commenter
				proba = new Random().nextInt(100);
				int probaBonus = new Random().nextInt(25);
				proba += probaBonus;
				if (proba <= probaCommenter + bonusProbaCommenter) {
					commenter(visionneur, video);
				}
				disliker(visionneur, video);
			}
		}
		else {
			//Test probabilit� de commenter
			proba = new Random().nextInt(100);
			if (proba <= probaCommenter) {
				commenter(visionneur, video);
			}
		}

		visionneur.mettrePause(1000);		
		video.addVue();
		visionneur.addVideoVue(video); //Ajoute la video � la liste des videos vues
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
