package modele;

public class Video {
	
	private int nbVues = 0;
	private int ageRequis = 0;
	private String titre;
	private String description;
	private int nbLikes = 0;
	private int nbDislikes = 0;
	private int nbCommentaires = 0;
	private int duree;
	
	public Video(String titre, String description, int duree) {
		titre = titre;
		duree = duree;
		description = description;
	}
	
	public int getDuree() {
		return duree;
	}
	
}
