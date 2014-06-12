package comportement;

import java.util.ArrayList;
import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class Comportement extends Thread {
	
	private int probaVote;
	private int probaLike;
	private int probaCommenter;
	private int bonusProbaCommenter;
	private int probaAbonner;
	
	public static int coefficientBonusProbaVote;
	
	public Comportement(int probaVote, int probaLike, int probaCommenter, int bonusProbaCommenter, int probaAbonner) {
		if (probaVote >= 0) {
			if (probaVote <= 70) {
				this.probaVote = probaVote;
			}
			else {
				this.probaVote = 70;
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
		ArrayList<Utilisateur> utilisateurs = youtube.getUtilisateurs();	//Sélection d'un propriétaire (de chaîne) aléatoire
		int index = new Random().nextInt(utilisateurs.size());
		Utilisateur proprietaire = utilisateurs.get(index);
		
		Chaine chaine = proprietaire.getChaine();		//Récupération de la chaîne
		ArrayList<Video> videos = chaine.getVideos();	//Sélection d'une video aléatoire
		if (videos.size() != 0) {
			index = new Random().nextInt(videos.size());
			Video videoVisionnee = videos.get(index);
			visionner(visionneur, videoVisionnee, chaine);
		}
		else {
			selectionnerVideo(visionneur, youtube);
		}
	}
	
	public void visionner(Utilisateur visionneur, Video video, Chaine chaine) {
		//Test si le visionneur a l'age requis
		if (visionneur.getAge() >= video.getAgeRequis()) {
			visionneur.getAgentLogger().info("Visionne une video : " + video.getTitre() + " " + video.getDuree() + "s");		//Simulation visionnage
			visionneur.mettrePause(video.getDuree());
			//Test des centres d'intérêt
			ArrayList<String> centresInteret = visionneur.getCentresInteret();
			ArrayList<String> tags = video.getTags();
			int nbTagsCentresInteret = 0;
			for (int i = 0; i < centresInteret.size(); i++) {
				if (tags.contains(centresInteret.get(i))) {
					nbTagsCentresInteret ++;
				}
			}
			int bonusProbaVote = nbTagsCentresInteret * Comportement.coefficientBonusProbaVote;
			
			int proba = new Random().nextInt(70);
			int probaBonus = 0;
			if (bonusProbaVote > 0) {
				probaBonus = new Random().nextInt(bonusProbaVote);
			}
			proba += probaBonus;
			
			//Test probabilité de voter
			if (proba <= probaVote + bonusProbaVote) {
				proba = new Random().nextInt(100);
				//Test probabilité de liker
				if (proba <= probaLike) {
					liker(visionneur, video);
					//Test probabilité de commenter
					proba = new Random().nextInt(100);
					probaBonus = new Random().nextInt(25);
					proba += probaBonus;
					if (proba <= probaCommenter + bonusProbaCommenter) {
						commenter(visionneur, video);
					}
					//Test probabilité de s'abonner à la chaîne
					proba = new Random().nextInt(100);
					if (proba <= probaAbonner) {
						abonner(visionneur, chaine);
					}
				}
				//Sinon dislike
				else {
					//Test probabilité de commenter
					proba = new Random().nextInt(100);
					probaBonus = new Random().nextInt(25);
					proba += probaBonus;
					if (proba <= probaCommenter + bonusProbaCommenter) {
						commenter(visionneur, video);
					}
					disliker(visionneur, video);
				}
			}
			else {
				//Test probabilité de commenter
				proba = new Random().nextInt(100);
				if (proba <= probaCommenter) {
					commenter(visionneur, video);
				}
			}
	
			visionneur.mettrePause(5);		
			video.addVue();
			visionneur.addVideoVue(video); //Ajoute la video à la liste des videos vues
		}
		else {
			visionneur.getAgentLogger().info("Trop jeune pour la video");
		}
	}
	
	public void liker(Utilisateur visionneur, Video video) {
		video.addLike();
		visionneur.addLikedVideo(video);
		visionneur.getAgentLogger().info("Video likee");
	}
	
	public void disliker(Utilisateur visionneur, Video video) {
		video.addDislike();
		visionneur.addDislikedVideo(video);
		visionneur.getAgentLogger().info("Video dislikee");
	}

	public void commenter(Utilisateur visionneur, Video video) {
		video.addCommentaire();
		visionneur.addVideoCommentee(video);
		visionneur.getAgentLogger().info("Video commentee");
	}
	
	public void abonner(Utilisateur visionneur, Chaine chaine) {
		chaine.addAbonne(visionneur);
		visionneur.addAbonnementChaine(chaine);
		visionneur.getAgentLogger().info("Abonnement a la chaine");
	}
}
