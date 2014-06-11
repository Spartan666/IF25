package modele;

import java.util.ArrayList;

import madkit.kernel.Agent;
import madkit.kernel.AgentLogger;
import comportement.Comportement;

public class Visiteur extends Agent {

	public static Youtube youtube;
	
	public void activate() {
		this.logger.info("Visiteur en ligne");
		pause(2000);
	}
	
	public void live() {
		// TODO Auto-generated method stub
		while(true){
			
		}
	}
	
	public void end() {
		// TODO Auto-generated method stub
		this.logger.info("Visiteur hors ligne");
	}
}
