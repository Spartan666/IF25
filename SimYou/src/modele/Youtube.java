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
	
	public Youtube(Controleur controleur, Fenetre fenetre,ArrayList<Integer> confUtilisateurs){
		Utilisateur.youtube=this;
		 Madkit m = new Madkit();
		 
		 //ConfPosteur
		for(int i=0;i<confUtilisateurs.get(0);i++){
			Utilisateur U = new Utilisateur("Posteur"+i, "Joe", 30, null, new ComportementPosteur(confUtilisateurs.get(1),confUtilisateurs.get(2),confUtilisateurs.get(3),confUtilisateurs.get(4),confUtilisateurs.get(5), confUtilisateurs.get(6)));
			U.setName("Posteur"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfFan
		for(int i=0;i<confUtilisateurs.get(7);i++){
			Utilisateur U = new Utilisateur("Fan"+i, "Joe", 16, null, new ComportementFan(confUtilisateurs.get(8),confUtilisateurs.get(9),confUtilisateurs.get(10),confUtilisateurs.get(11),confUtilisateurs.get(12)));
			U.setName("Fan"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfObjecteur
		for(int i=0;i<confUtilisateurs.get(13);i++){
			Utilisateur U = new Utilisateur("Objecteur"+i, "Joe", 25, null, new ComportementObjecteur(confUtilisateurs.get(14),confUtilisateurs.get(15),confUtilisateurs.get(16),confUtilisateurs.get(17),confUtilisateurs.get(18)));
			U.setName("Objecteur"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfRapide
		for(int i=0;i<confUtilisateurs.get(19);i++){
			Utilisateur U = new Utilisateur("Rapide"+i, "Joe", 14, null, new ComportementRapide(confUtilisateurs.get(20),confUtilisateurs.get(21),confUtilisateurs.get(22),confUtilisateurs.get(23),confUtilisateurs.get(24)));
			U.setName("Rapide"+i);
			this.addUtilisateur(U);
             m.doAction(KernelAction.LAUNCH_AGENT,U, true); //launch a new agent with a GUI
		}
		//ConfSuiveur
		for(int i=0;i<confUtilisateurs.get(25);i++){
			Utilisateur U = new Utilisateur("Suiveur"+i, "Joe", 40, null, new ComportementSuiveur(confUtilisateurs.get(26),confUtilisateurs.get(27),confUtilisateurs.get(28),confUtilisateurs.get(29),confUtilisateurs.get(30)));
			U.setName("Suiveur"+i);
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
