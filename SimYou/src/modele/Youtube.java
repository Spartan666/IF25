package modele;

import java.util.ArrayList;

import madkit.gui.menu.LaunchAgentsMenu;
import madkit.kernel.Agent;
import madkit.kernel.Madkit;
import madkit.kernel.Madkit.Option;

public class Youtube {

	/**
	 * @param args
	 */
	ArrayList<Utilisateur> Utilisateurs;
	
	public Youtube(){
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	new Madkit(Option.launchAgents.toString(),PosteurVideo.class.getName()+",true,3;");
		Utilisateur U=new Utilisateur("Joe","Mobile",null,"BASIQUE");
		U.activate();
		U.live();
		
	}

}
