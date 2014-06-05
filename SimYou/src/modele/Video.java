package modele;

import java.util.ArrayList;

public class Video {
	
	private int nbVues = 0;
	private String titre;
	private String description;
	private int nbLikes = 0;
	private int nbDislikes = 0;
	private int nbCommentaires = 0;
	private int duree;
	private ArrayList<String> tags = new ArrayList<String>();
	
	public Video(String titre, String description, int duree, ArrayList<String> tags) {
		this.titre = titre;
		this.duree = duree;
		this.description = description;
		this.tags = tags;
	}
	
	public int getDuree() {
		return duree;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public String getDescription() {
		return description;
	}

	public int getNbLikes() {
		return nbLikes;
	}

	public void addLike() {
		this.nbLikes++;
	}

	public int getNbDislikes() {
		return nbDislikes;
	}

	public void addDislike() {
		this.nbDislikes++;
	}

	public int getNbCommentaires() {
		return nbCommentaires;
	}

	public void addCommentaire() {
		this.nbCommentaires++;
	}

	public int getNbVues() {
		return nbVues;
	}

	public void addVue() {
		this.nbVues++;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

}
