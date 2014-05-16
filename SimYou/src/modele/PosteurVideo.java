package modele;
import java.util.ArrayList;

import madkit.kernel.Agent;
import madkit.kernel.Message;


public class PosteurVideo extends Agent{
	
	private ArrayList<Video> Videos=new ArrayList<Video>();
	
	public ArrayList<Video> getVideos(){
		return Videos;
	}
	
	public void activate() {
		logger.info("Je me connecte");
		pause(2000);
	}
	
	public void live() {
		// TODO Auto-generated method stub
		while(true){
			pause(1000);
			Videos.add(new Video());
			logger.info("nouvelle video");
		}
	}
	
	public void end() {
		// TODO Auto-generated method stub
		System.out.println("Je me deconnecte");
	}
}
