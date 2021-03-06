package comportement;

import java.util.ArrayList;
import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public abstract class Comportement {
	
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
		ArrayList<Utilisateur> utilisateurs = youtube.getUtilisateurs();	//S�lection d'un propri�taire (de cha�ne) al�atoire
		int index = new Random().nextInt(utilisateurs.size());
		Utilisateur proprietaire = utilisateurs.get(index);
		
		Chaine chaine = proprietaire.getChaine();		//R�cup�ration de la cha�ne
		ArrayList<Video> videos = chaine.getVideos();	//S�lection d'une video al�atoire
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
			visionneur.mettrePause(video.getDuree()); //Pause de l'agent le temps de la vid�o
			//Test des centres d'int�r�t
			ArrayList<String> centresInteret = visionneur.getCentresInteret();
			ArrayList<String> tags = video.getTags();
			int nbTagsCentresInteret = 0;
			//Compte les centres d'int�r�t communs avec les tags de la vid�o
			for (int i = 0; i < centresInteret.size(); i++) {
				if (tags.contains(centresInteret.get(i))) {
					nbTagsCentresInteret ++;
				}
			}
			int bonusProbaVote = nbTagsCentresInteret * ComportementLambda.coefficientBonusProbaVote;	//Pourcentage bonus pond�r� de voter en fonction des centres d'int�r�t 
			
			int proba = new Random().nextInt(70);
			int probaBonus = 0;
			if (bonusProbaVote > 0) {
				probaBonus = new Random().nextInt(bonusProbaVote);
			}
			proba += probaBonus;
			
			//Test probabilit� de voter
			if (proba <= probaVote + bonusProbaVote) {
				proba = new Random().nextInt(100);
				//Test probabilit� de liker
				if (proba <= probaLike) {
					liker(visionneur, video);
					//Test probabilit� de commenter
					proba = new Random().nextInt(100);
					probaBonus = new Random().nextInt(25);
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
					probaBonus = new Random().nextInt(25);
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
	
			visionneur.mettrePause(5);		
			video.addVue();
			visionneur.addVideoVue(video); //Ajoute la video � la liste des videos vues
		}
		else {
			visionneur.getAgentLogger().info("Trop jeune pour la video " + video.getTitre() + " Age requis : " + video.getAgeRequis());
			visionneur.mettrePause(30);	
		}
	}
	
	public void liker(Utilisateur visionneur, Video video) {
		//Test si vid�o d�j� lik�e
		if (!visionneur.getLikedVideos().contains(video)) {	
			video.addLike();
			visionneur.addLikedVideo(video);
			visionneur.getAgentLogger().info("Video likee");
		}
	}
	
	public void disliker(Utilisateur visionneur, Video video) {
		//Test si vid�o d�j� dislik�e
		if (!visionneur.getDislikedVideos().contains(video)) {	
			video.addDislike();
			visionneur.addDislikedVideo(video);
			visionneur.getAgentLogger().info("Video dislikee");
		}
	}

	public void commenter(Utilisateur visionneur, Video video) {
		video.addCommentaire();
		visionneur.addVideoCommentee(video);
		visionneur.getAgentLogger().info("Video commentee");
	}
	
	public void abonner(Utilisateur visionneur, Chaine chaine) {
		//Test si cha�ne differente de celle du visionneur
		if (!visionneur.getChaine().equals(chaine)) {
		//Test si pas encore abonn�
			if (!visionneur.getAbonnementsChaines().contains(chaine)) {	
				chaine.addAbonne(visionneur);
				visionneur.addAbonnementChaine(chaine);
				visionneur.getAgentLogger().info("Abonnement a la chaine");
			}
		}
	}
}
