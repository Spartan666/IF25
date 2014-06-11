package controleur;

import java.util.ArrayList;

import vue.Fenetre;
import modele.Youtube;

public class Controleur {
	
	private Youtube youtube;
	private Fenetre fenetre;

	
	
	public Controleur(){
		this.fenetre = new Fenetre(youtube, this);	
	}
	
	
	public void configurerSimulation(ArrayList<Integer> confUtilisateurs) {
			youtube = new Youtube(this,confUtilisateurs);
	}
	
	//public void AfficherInformations(int nbvideo, int nbcommentaire, int nblike, int nbdislike, int moyenneLike, int MaxLike, int MinLike,int moyenneCommentaire, int MaxCommentaire, int MinCommentaire, int nbVueTotal,int nbAbonnementMoyen,)
	
	public static void main(String[] args) {
		new Controleur();
	}


}
