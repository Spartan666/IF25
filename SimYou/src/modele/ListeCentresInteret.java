package modele;

import java.util.Random;

public enum ListeCentresInteret {
	Sport,
	Voyages,
	Cinema,
	Television,
	JeuxVideo,
	Peche,
	Chasse,
	Litterature,
	Musique,
	Art;
	
	public static String getRandomValue() {
        Random random = new Random();
        return values()[random.nextInt(values().length)].toString();
	}
}
