package modele;

import java.util.ArrayList;

import java.util.HashMap;


import java.util.Random;



import vue.Fenetre;
import comportement.ComportementLambda;
import comportement.ComportementFan;
import comportement.ComportementObjecteur;
import comportement.ComportementPosteur;
import comportement.ComportementRapide;
import comportement.ComportementSuiveur;
import controleur.Controleur;
import madkit.action.KernelAction;
import madkit.gui.menu.LaunchAgentsMenu;
import madkit.kernel.Agent;
import madkit.kernel.Madkit;
import madkit.kernel.Madkit.Option;
import modele.ListeCentresInteret;

public class Youtube {

	/**
	 * @param args
	 */
	private ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	public  int nbVisiteurs = 0;
	private Controleur controleur;
	private Madkit m;
	
	public Youtube(Controleur controleur,Madkit m,ArrayList<Integer> confUtilisateurs){
		Utilisateur.youtube=this;
		 this.controleur=controleur;
		 this.m=m;
		 //ConfPosteur
		for(int i=0;i<confUtilisateurs.get(0);i++){
			ArrayList<String> centresInteret = new ArrayList<String>();
			int nbCentresInteret = 1 + new Random().nextInt(3);
			for (int j = 1; j <= nbCentresInteret; j++) {
			    centresInteret.add(ListeCentresInteret.getRandomValue());
			}
			int age = 13 + new Random().nextInt(87); //Age entre 13 et 99 ans
			Utilisateur U = new Utilisateur("Posteur"+i, "Joe", age, centresInteret, new ComportementPosteur(confUtilisateurs.get(1),confUtilisateurs.get(2),confUtilisateurs.get(3),confUtilisateurs.get(4),confUtilisateurs.get(5), confUtilisateurs.get(6)));
			U.setName("Posteur"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfLambda
		for(int i=0;i<confUtilisateurs.get(31);i++){
			ArrayList<String> centresInteret = new ArrayList<String>();
			int nbCentresInteret = 1 + new Random().nextInt(3);
			for (int j = 1; j <= nbCentresInteret; j++) {
			    centresInteret.add(ListeCentresInteret.getRandomValue());
			}
			int age = 13 + new Random().nextInt(87); //Age entre 13 et 99 ans
			Utilisateur U = new Utilisateur("Lambda"+i, "Joe", age, centresInteret, new ComportementLambda(confUtilisateurs.get(32),confUtilisateurs.get(33),confUtilisateurs.get(34),confUtilisateurs.get(35),confUtilisateurs.get(36)));
			U.setName("Lambda"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}		
		//ConfFan
		for(int i=0;i<confUtilisateurs.get(7);i++){
			ArrayList<String> centresInteret = new ArrayList<String>();
			int nbCentresInteret = 1 + new Random().nextInt(3);
			for (int j = 1; j <= nbCentresInteret; j++) {
			    centresInteret.add(ListeCentresInteret.getRandomValue());
			}
			int age = 13 + new Random().nextInt(87); //Age entre 13 et 99 ans
			Utilisateur U = new Utilisateur("Fan"+i, "Joe", age, centresInteret, new ComportementFan(confUtilisateurs.get(8),confUtilisateurs.get(9),confUtilisateurs.get(10),confUtilisateurs.get(11),confUtilisateurs.get(12)));
			U.setName("Fan"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfObjecteur
		for(int i=0;i<confUtilisateurs.get(13);i++){
			ArrayList<String> centresInteret = new ArrayList<String>();
			int nbCentresInteret = 1 + new Random().nextInt(3);
			for (int j = 1; j <= nbCentresInteret; j++) {
			    centresInteret.add(ListeCentresInteret.getRandomValue());
			}
			int age = 13 + new Random().nextInt(87); //Age entre 13 et 99 ans
			Utilisateur U = new Utilisateur("Objecteur"+i, "Joe", age, centresInteret, new ComportementObjecteur(confUtilisateurs.get(14),confUtilisateurs.get(15),confUtilisateurs.get(16),confUtilisateurs.get(17),confUtilisateurs.get(18)));
			U.setName("Objecteur"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfRapide
		for(int i=0;i<confUtilisateurs.get(19);i++){
			ArrayList<String> centresInteret = new ArrayList<String>();
			int nbCentresInteret = 1 + new Random().nextInt(3);
			for (int j = 1; j <= nbCentresInteret; j++) {
			    centresInteret.add(ListeCentresInteret.getRandomValue());
			}
			int age = 13 + new Random().nextInt(87); //Age entre 13 et 99 ans
			Utilisateur U = new Utilisateur("Rapide"+i, "Joe", age, centresInteret, new ComportementRapide(confUtilisateurs.get(20),confUtilisateurs.get(21),confUtilisateurs.get(22),confUtilisateurs.get(23),confUtilisateurs.get(24)));
			U.setName("Rapide"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfSuiveur
		for(int i=0;i<confUtilisateurs.get(25);i++){
			ArrayList<String> centresInteret = new ArrayList<String>();
			int nbCentresInteret = 1 + new Random().nextInt(3);
			for (int j = 1; j <= nbCentresInteret; j++) {
			    centresInteret.add(ListeCentresInteret.getRandomValue());
			}
			int age = 13 + new Random().nextInt(87); //Age entre 13 et 99 ans
			Utilisateur U = new Utilisateur("Suiveur"+i, "Joe", age, centresInteret, new ComportementSuiveur(confUtilisateurs.get(26),confUtilisateurs.get(27),confUtilisateurs.get(28),confUtilisateurs.get(29),confUtilisateurs.get(30)));
			U.setName("Suiveur"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
	}
	
	public void lancerVisiteurs(int nbVisiteursMinute) {
		for (int i = 1; i <= nbVisiteursMinute; i++) {
			Visiteur V = new Visiteur();
			nbVisiteurs ++;
			V.setName("Visiteur" + nbVisiteurs);
			m.doAction(KernelAction.LAUNCH_AGENT, V, true); //launch a new agent with a GUI
		}
	}
	
	public int getNbVisiteurs() {
		return nbVisiteurs;
	}

	public ArrayList<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	
	public void addUtilisateur(Utilisateur utilisateur) {
		this.utilisateurs.add(utilisateur);
	}
	
	public HashMap infosPlateformes(Class<?> comportement){
		HashMap<String, Double> infos= new HashMap<String, Double>();
		
		double nbVideos=0,nbVideosAdulte=0,nbCommentaires=0,nbLikes=0,nbDislikes=0,moyenneLikeVideo=0,
				MaxLike=0,MinLike=0,moyenneCommentaireVideo=0,nbVueTotal=0,nbAbonnement=0,nbAbonne=0,moyenneLikeUtilisateur=0,
				moyenneDisLikeUtilisateur=0,moyenneAbonnement,moyenneAbonne,moyenneVueVideo,nbVideoNonVote=0,RatioVideoNonVote,moyenneDisLikeVideo,moyenneCommentaireUtilisateur,
				moyenneVueUtilisateur,nbUtilisateurs=0;
		for(Utilisateur u:this.getUtilisateurs()){
		if(comportement.isAssignableFrom(u.getComportement().getClass()))
				{
					nbUtilisateurs+=1;
					nbVideos+=u.getChaine().getVideos().size();
					nbAbonnement+=u.getAbonnementsChaines().size();
					if(u.getAbonnementsChaines().size()>0)
						nbAbonne+=1;
					for(Video v:u.getChaine().getVideos()){
						if(v.getAgeRequis()>=18)
							nbVideosAdulte+=1;
						nbCommentaires+=v.getNbCommentaires();
						nbLikes+=v.getNbLikes();
						nbDislikes+=v.getNbDislikes();
						if(v.getNbLikes()>MaxLike)
							MaxLike=v.getNbLikes();
						MinLike=MaxLike;
						if(v.getNbLikes()<MinLike)
							MinLike=v.getNbLikes();
						nbVueTotal+=v.getNbVues();
						if(v.getNbLikes()==0 && v.getNbDislikes() ==0)
							nbVideoNonVote+=1;
				}
				}
			}
		moyenneLikeVideo=nbLikes/nbVideos;
		moyenneDisLikeVideo=nbDislikes/nbVideos;
		RatioVideoNonVote=nbVideoNonVote/nbVideos;
		moyenneCommentaireVideo=nbCommentaires/nbVideos;
		moyenneVueVideo=nbVueTotal/nbVideos;
		moyenneLikeUtilisateur=nbLikes/nbUtilisateurs;
		moyenneDisLikeUtilisateur=nbDislikes/nbUtilisateurs;
		moyenneAbonnement=nbAbonnement/nbUtilisateurs;
		moyenneAbonne=nbAbonne/this.getUtilisateurs().size();
		moyenneCommentaireUtilisateur=nbCommentaires/nbUtilisateurs;
		moyenneVueUtilisateur=nbVueTotal/nbUtilisateurs;
		
		infos.put("Nombre Utilisateurs:", nbUtilisateurs);
		infos.put("Nombre Vidéos",nbVideos);
		infos.put("Nombre Abonnements", nbAbonnement);
		infos.put("Nombre Abonnés",nbAbonne);
		infos.put("Nombre vidéos +18", nbVideosAdulte);
		infos.put("Nombre commentaires",nbCommentaires);
		infos.put("Nombre Likes", nbLikes);
		infos.put("Nombre Dislikes", nbDislikes);
		infos.put("Maximum Like Vidéo", MaxLike);
		infos.put("Minimum Like Video", MinLike);
		infos.put("Nombre de vue", nbVueTotal);
		infos.put("Nombre de vidéos non votées", nbVideoNonVote);
		infos.put("Moyenne Like/Vidéo", moyenneLikeVideo);
		infos.put("Moyenne DisLike/Vidéo", moyenneDisLikeVideo);
		infos.put("Ratio vidéos non votées", RatioVideoNonVote);
		infos.put("Moyenne Commentaires/Vidéo", moyenneCommentaireVideo);
		infos.put("Moyenne Vue/Vidéo", moyenneVueVideo);
		infos.put("Moyenne Like/Utilisateur", moyenneLikeUtilisateur);
		infos.put("Moyenne DisLike/Utilisateur", moyenneDisLikeUtilisateur);
		infos.put("Moyenne Abonnement", moyenneAbonnement);
		infos.put("Moyenne Abonne", moyenneAbonne);
		infos.put("Moyenne Commentaires/Utilisateur", moyenneCommentaireUtilisateur);
		infos.put("Moyenne Vue/Utilisateur", moyenneVueUtilisateur);
		return infos;
	}
	
	

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Youtube youtube = new Youtube();
		Utilisateur U = new Utilisateur("Mobile", "Joe", null, new ComportementPosteur(10, 10, 10, 10, 1));
		youtube.addUtilisateur(U);
		U.activate();
		U.live(youtube);
		
	}*/

}
