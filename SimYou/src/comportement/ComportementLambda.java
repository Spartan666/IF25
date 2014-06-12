package comportement;

import java.util.ArrayList;
import java.util.Random;

import modele.Chaine;
import modele.Utilisateur;
import modele.Video;
import modele.Youtube;

public class ComportementLambda extends Comportement {

	static int nbinstance=0;

	public ComportementLambda(int probaVote, int probaLike,
			int probaCommenter, int bonusProbaCommenter, int probaAbonner) {
		super(probaVote, probaLike, probaCommenter, bonusProbaCommenter, probaAbonner);
		// TODO Auto-generated constructor stub
		nbinstance+=1;

	}

	public static int getNbinstance() {
		return nbinstance;
	}
	

	
}
