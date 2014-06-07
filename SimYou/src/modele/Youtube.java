package modele;

import java.util.ArrayList;

import comportement.Comportement;
import comportement.ComportementPosteur;
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
	
	public Youtube(){
	}
	
	public ArrayList<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	
	public void addUtilisateur(Utilisateur utilisateur) {
		this.utilisateurs.add(utilisateur);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Youtube youtube = new Youtube();
		Utilisateur.youtube = youtube;
		Utilisateur U1 = new Utilisateur("Mobile", "Joe", null, new ComportementPosteur(10, 10, 10, 10, 10, 1));
		Utilisateur U2 = new Utilisateur("Smith", "Will", null, new ComportementPosteur(10, 10, 10, 10, 10, 1));
		youtube.addUtilisateur(U1);
		youtube.addUtilisateur(U2);

		Madkit m = new Madkit();
		m.doAction(KernelAction.LAUNCH_AGENT, U1, true);
		m.doAction(KernelAction.LAUNCH_AGENT, U2, true);

	}

}
