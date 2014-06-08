package modele;

import java.util.ArrayList;

import vue.Fenetre;

import comportement.Comportement;
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

public class Youtube {

	/**
	 * @param args
	 */
	private ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	
	public Youtube(Controleur controleur, Fenetre fenetre,ArrayList<Integer> confPosteur){
		 Madkit m = new Madkit();
		 
		 //ConfPosteur
		for(int i=0;i<confPosteur.get(0);i++){
			Utilisateur U = new Utilisateur("Mobile"+i, "Joe", null, new ComportementPosteur(confPosteur.get(1),confPosteur.get(2),confPosteur.get(3),confPosteur.get(4),confPosteur.get(5), confPosteur.get(6)));
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfFan
		for(int i=0;i<confPosteur.get(7);i++){
			Utilisateur U = new Utilisateur("Fan"+i, "Joe", null, new ComportementFan(confPosteur.get(8),confPosteur.get(9),confPosteur.get(10),confPosteur.get(11),confPosteur.get(12)));
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfObjecteur
		for(int i=0;i<confPosteur.get(13);i++){
			Utilisateur U = new Utilisateur("Objecteur"+i, "Joe", null, new ComportementObjecteur(confPosteur.get(14),confPosteur.get(15),confPosteur.get(16),confPosteur.get(17),confPosteur.get(18)));
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfRapide
		for(int i=0;i<confPosteur.get(19);i++){
			Utilisateur U = new Utilisateur("Rapide"+i, "Joe", null, new ComportementRapide(confPosteur.get(20),confPosteur.get(21),confPosteur.get(22),confPosteur.get(23),confPosteur.get(24)));
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfSuiveur
		for(int i=0;i<confPosteur.get(25);i++){
			Utilisateur U = new Utilisateur("Suiveur"+i, "Joe", null, new ComportementSuiveur(confPosteur.get(26),confPosteur.get(27),confPosteur.get(28),confPosteur.get(29),confPosteur.get(30)));
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		
	}
	
	public ArrayList<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	
	public void addUtilisateur(Utilisateur utilisateur) {
		this.utilisateurs.add(utilisateur);
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
