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
	ArrayList<PosteurVideo> PosteursVideo;
	
	public Youtube(){
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	new Madkit(Option.launchAgents.toString(),PosteurVideo.class.getName()+",true,3;");
		new PosteurVideo().activate();
		new PosteurVideo().live();
		
	}

}
