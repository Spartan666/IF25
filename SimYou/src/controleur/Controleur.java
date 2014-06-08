package controleur;

import java.util.ArrayList;

import vue.Fenetre;
import modele.Youtube;

public class Controleur {
	
	private Youtube youtube;
	private Fenetre fenetre;

	
	
	public Controleur(){
		this.fenetre = new Fenetre(youtube, this);

		
	}
	
	
	public void configurerSimulation(ArrayList<Integer> confPosteur) {
			youtube = new Youtube(this, fenetre,confPosteur);
			//fenetre.setSimulation(youtube);
	}
	
	public static void main(String[] args) {
		new Controleur();
	}


}
