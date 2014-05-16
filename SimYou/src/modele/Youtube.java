package modele;

import madkit.gui.menu.LaunchAgentsMenu;
import madkit.kernel.Agent;
import madkit.kernel.Madkit;
import madkit.kernel.Madkit.Option;

public class Youtube extends Agent {

	/**
	 * @param args
	 */
	
	
	public Youtube(){
		AjouterPosteurVideo();
	}
	
	public void AjouterPosteurVideo(){
		new PosteurVideo();
	}
	
	public void live() {
		launchAgent(PosteurVideo.class.getName());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	new Madkit(Option.launchAgents.toString(),PosteurVideo.class.getName()+",true,3;");
		executeThisAgent(1,false,args);

	}

}
