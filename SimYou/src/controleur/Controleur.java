package controleur;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import vue.Fenetre;
import vue.PanelSimulationCours;
import madkit.action.KernelAction;
import madkit.kernel.Madkit;
import modele.Youtube;

public class Controleur {
	
	private Youtube youtube;
	private Fenetre fenetre;
	private Madkit m;

	
	public static int vitesse = 1;

	
	
	public Controleur(){
		this.fenetre = new Fenetre(this);	
		 m = new Madkit();
	}
	
	
	public void configurerSimulation(ArrayList<Integer> confUtilisateurs,String vitesse) {
		if(vitesse.equals("Rapide (ms)"))
		Controleur.vitesse=1000;
		System.out.println(vitesse.equals("Rapide (ms)"));
		youtube = new Youtube(this,m,confUtilisateurs);
		
		while(true){
		this.afficherModele(youtube.infosPlateformes());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void afficherModele(HashMap donnees){
		this.fenetre.setContentPane(new PanelSimulationCours(donnees));
		this.fenetre.validate();
		this.fenetre.repaint();
	}
	
	public void SauvegarderModele(String chemin){
		m.doAction(KernelAction.KILL_AGENT);
		try
		{ FileWriter lu = new FileWriter(chemin);
		   BufferedWriter out = new BufferedWriter(lu);
		   out.write(youtube.infosPlateformes().toString()); //
		   out.close(); 
		 } catch (IOException er) {;}
	}
	
	
	//public void AfficherInformations(int nbvideo, int nbcommentaire, int nblike, int nbdislike, int moyenneLike, int MaxLike, int MinLike,int moyenneCommentaire, int MaxCommentaire, int MinCommentaire, int nbVueTotal,int nbAbonnementMoyen,)
	
	public HashMap getYoutubeInfos() {
		return youtube.infosPlateformes();
	}


	public static void main(String[] args) {
		new Controleur();
	}


}
