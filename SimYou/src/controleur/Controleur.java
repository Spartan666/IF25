package controleur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import vue.Fenetre;
import vue.PanelSimulationCours;
import madkit.action.KernelAction;
import madkit.kernel.Madkit;
import modele.Visiteur;
import modele.Youtube;

public class Controleur {
	
	private Youtube youtube;
	private Fenetre fenetre;
	private Madkit m;

	public static int nbVisiteurs = 0;
	public static int nbVisiteursMinute = 50;
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
		Visiteur.youtube = youtube;
		
		new Thread(new Runnable() {
		      public void run() {
		    	while(true) {
			    	lancerVisiteurs(youtube);
			  		try {
						Thread.sleep(60 * 1000 / Controleur.vitesse);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		      }
		}).start();
		
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
	
	public void lancerVisiteurs(Youtube youtube) {
		for (int i = 1; i <= this.nbVisiteursMinute; i++) {
			Visiteur V = new Visiteur();
			nbVisiteurs ++;
			V.setName("Visiteur" + nbVisiteurs);
			m.doAction(KernelAction.LAUNCH_AGENT, V, true); //launch a new agent with a GUI
		}
	}
	
	public void afficherModele(HashMap donnees){
		this.fenetre.setContentPane(new PanelSimulationCours(this,donnees));
		this.fenetre.validate();
		this.fenetre.repaint();
	}
	
	public Fenetre getFenetre() {
		return fenetre;
	}




	public void SauvegarderModele(File f){
		 try
		 {
		     FileWriter fw = new FileWriter (f);
		     fw.write(getYoutubeInfos().toString());
		     fw.close();
		 }
		 catch(Exception e){};
		 int code = 0;
		 System.exit(code);
	}
	
	
	//public void AfficherInformations(int nbvideo, int nbcommentaire, int nblike, int nbdislike, int moyenneLike, int MaxLike, int MinLike,int moyenneCommentaire, int MaxCommentaire, int MinCommentaire, int nbVueTotal,int nbAbonnementMoyen,)
	
	public HashMap getYoutubeInfos() {
		return youtube.infosPlateformes();
	}


	public static void main(String[] args) {
		new Controleur();
	}


}
