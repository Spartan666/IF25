package modele;

import java.util.ArrayList;

import comportement.Comportement;

import madkit.gui.menu.LaunchAgentsMenu;
import madkit.kernel.Agent;
import madkit.kernel.Madkit;
import madkit.kernel.Madkit.Option;

public class Youtube {

	/**
	 * @param args
	 */
	private ArrayList<Utilisateur> utilisateurs;
	
	public Youtube(){
	}
	
	public ArrayList<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utilisateur U=new Utilisateur("Joe","Mobile",null, new Comportement(10, 10, 10, 10));
		U.activate();
		U.live();
		
	}

}
