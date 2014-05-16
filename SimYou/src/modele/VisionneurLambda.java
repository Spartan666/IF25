package modele;

import java.util.ArrayList;

import madkit.kernel.Agent;

public class VisionneurLambda extends Agent {
	
	public void activate() {
		logger.info("Je me connecte pour visionner");
		pause(2000);
	}
	
	public void live() {
		// TODO Auto-generated method stub
		while(true){
			pause(1000);
			Videos.add(new Video());
			logger.info("Je visionne");
		
		}
	}
	
	public void end() {
		// TODO Auto-generated method stub
		System.out.println("Je me deconnecte");
	}
}
