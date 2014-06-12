package controleur;

import it.uniroma1.dis.wiserver.gexf4j.core.Edge;
import it.uniroma1.dis.wiserver.gexf4j.core.EdgeType;
import it.uniroma1.dis.wiserver.gexf4j.core.Gexf;
import it.uniroma1.dis.wiserver.gexf4j.core.Graph;
import it.uniroma1.dis.wiserver.gexf4j.core.Mode;
import it.uniroma1.dis.wiserver.gexf4j.core.Node;
import it.uniroma1.dis.wiserver.gexf4j.core.data.Attribute;
import it.uniroma1.dis.wiserver.gexf4j.core.data.AttributeClass;
import it.uniroma1.dis.wiserver.gexf4j.core.data.AttributeList;
import it.uniroma1.dis.wiserver.gexf4j.core.data.AttributeType;
import it.uniroma1.dis.wiserver.gexf4j.core.impl.GexfImpl;
import it.uniroma1.dis.wiserver.gexf4j.core.impl.StaxGraphWriter;
import it.uniroma1.dis.wiserver.gexf4j.core.impl.data.AttributeListImpl;
import it.uniroma1.dis.wiserver.gexf4j.core.impl.viz.ColorImpl;
import it.uniroma1.dis.wiserver.gexf4j.core.viz.NodeShape;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JPanel;



import comportement.Comportement;
import comportement.ComportementFan;
import comportement.ComportementLambda;
import comportement.ComportementObjecteur;
import comportement.ComportementPosteur;
import comportement.ComportementRapide;
import comportement.ComportementSuiveur;

import vue.Fenetre;
import vue.PanelSimulationCours;
import madkit.action.AgentAction;
import madkit.action.KernelAction;
import madkit.kernel.Madkit;
import modele.Chaine;
import modele.Utilisateur;
import modele.Visiteur;
import modele.Youtube;

public class Controleur {
	
	private Youtube youtube;
	private Fenetre fenetre;
	private Madkit m;
	public static int nbVisiteursMinute = 50;
	private Object[][] data;


	public static int vitesse = 1;

	
	
	public Controleur(){
		this.fenetre = new Fenetre(this);	
		 m = new Madkit();
	}
	
	
	public void configurerSimulation(ArrayList<Integer> confUtilisateurs,String vitesse) {
		if(vitesse.equals("Rapide (ms)"))
		Controleur.vitesse=1000;
		Controleur.nbVisiteursMinute=confUtilisateurs.get(31);
		System.out.println(vitesse.equals("Rapide (ms)"));
		youtube = new Youtube(this,m,confUtilisateurs);
		Visiteur.youtube = youtube;
		
		new Thread(new Runnable() {
		      public void run() {
		    	while(true) {
			    	youtube.lancerVisiteurs(nbVisiteursMinute);
			  		try {
						Thread.sleep(60 * 1000 / Controleur.vitesse);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		      }
		}).start();
		this.afficherModele(this.getTableauDonnees());
	/*	while(true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}*/
	}
	

	
	public void afficherModele(Object[][] tableauxDonnees){
		this.fenetre.setContentPane(new PanelSimulationCours(this, tableauxDonnees));
		this.fenetre.validate();
		this.fenetre.setSize(800, 600);
		this.fenetre.repaint();
	}
	
	public Fenetre getFenetre() {
		return fenetre;
	}





	public void SauvegarderModele(File f){
		 try
		 {
			 
			 Object[][] data= getTableauDonnees();
		     FileWriter fw = new FileWriter (f);
		     String resultat="";
		     for(int i=0;i<data.length;i++)
		     resultat+=data[i][0]+" "+data[i][1]+"\n";
		     fw.write(resultat);
		     fw.close();
		 }
		 catch(Exception e){};
	}
	
	public void SauvegarderGrapheChaines(File f){
		Gexf gexf = new GexfImpl();
		Calendar date = Calendar.getInstance();
		Hashtable<Utilisateur, Node> hash= new Hashtable<Utilisateur, Node>();
		
		gexf.getMetadata()
			.setLastModified(date.getTime())
			.setCreator("IF25")
			.setDescription("Graphe des chaines influentes");
		gexf.setVisualization(true);

		Graph graph = gexf.getGraph();
		graph.setDefaultEdgeType(EdgeType.DIRECTED).setMode(Mode.STATIC);
		
		AttributeList attrList = new AttributeListImpl(AttributeClass.NODE);
		graph.getAttributeLists().add(attrList);
		
		Attribute attNom = (Attribute) attrList.createAttribute("0",AttributeType.STRING, "Nom");
		Attribute attNbAbonne = (Attribute) attrList.createAttribute("1", AttributeType.INTEGER, "Nombre abonnes");
		int max=1;
		for(Utilisateur U:this.youtube.getUtilisateurs()){
			if(U.getChaine().getAbonnes().size()>max)
				max=U.getChaine().getAbonnes().size();
		}
		Node gephi = null;
		for(Utilisateur U:this.youtube.getUtilisateurs()){
			gephi = graph.createNode(U.getNom());
			gephi
			.setLabel(U.getNom())
			.setSize((U.getChaine().getAbonnes().size()*100)/max)
			.getAttributeValues()
				.addValue((it.uniroma1.dis.wiserver.gexf4j.core.data.Attribute) attNom, U.getNom())
				.addValue((it.uniroma1.dis.wiserver.gexf4j.core.data.Attribute) attNbAbonne, ""+U.getChaine().getAbonnes().size());
			if(U.getComportement() instanceof ComportementFan)
				gephi.setColor(new ColorImpl(249, 0, 0));
			if((U.getComportement() instanceof ComportementLambda))
				gephi.setColor(new ColorImpl(158, 1, 249));
			if(U.getComportement() instanceof ComportementObjecteur)
				gephi.setColor(new ColorImpl(230, 100, 98));
			if(U.getComportement() instanceof ComportementPosteur)
				gephi.setColor(new ColorImpl(1, 249, 1));
			if(U.getComportement() instanceof ComportementRapide)
				gephi.setColor(new ColorImpl(249, 232, 1));
			if(U.getComportement() instanceof ComportementSuiveur)
				gephi.setColor(new ColorImpl(0, 0, 0));
			hash.put(U, gephi);
		}
		
		
		for(Utilisateur U: hash.keySet()){
			for(Chaine c:U.getAbonnementsChaines()){
				System.out.println(hash.get(U));
				Edge E=hash.get(U).connectTo(hash.get(c.getProprietaire()));
				E.setEdgeType(EdgeType.DIRECTED);
			}
		}
		
		
		//
	

		StaxGraphWriter graphWriter = new StaxGraphWriter();
		 Writer out;
			try {
				out =  new FileWriter(f, false);
				graphWriter.writeToStream(gexf, out, "UTF-8");
				System.out.println(f.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		 

		    	     
	}
	
	public Object[][] formaterDonneesTableaux(HashMap donneesGenerale, HashMap donneesComportementLambda, HashMap donneesComportementObjecteur,
			HashMap donneesComportementPosteur, HashMap donneesComportementFan, HashMap donneesComportementRapide,
			HashMap donneesComportementSuiveur){
		Object[][] data = new Object[donneesGenerale.size()+2+donneesComportementLambda.size()+2+donneesComportementObjecteur.size()+2+donneesComportementPosteur.size()+2+donneesComportementFan.size()+2+donneesComportementRapide.size()+2+donneesComportementSuiveur.size()+3][2];
		int i=0;
		data[i][0]="Nombre Utilisateurs:";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Nombre Vidéos";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vue";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Nombre Likes";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Nombre Dislikes";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Nombre commentaires";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnés";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnements";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Maximum Like Vidéo";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Minimum Like Video";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Nombre vidéos +18";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vidéos non votées";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Vidéo";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Vidéo";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Vidéo";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Vidéo";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Utilisateur";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Utilisateur";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Utilisateur";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Utilisateur";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Ratio vidéos non votées";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonne";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonnement";data[i][1]=donneesGenerale.get(data[i][0]); i+=1;
		
		data[i][0]="";data[i][1]=""; i+=1;
		data[i][0]="Comportement Lambda";data[i][1]=""; i+=1;
		data[i][0]="Nombre Utilisateurs:";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Nombre Vidéos";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vue";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Nombre Likes";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Nombre Dislikes";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Nombre commentaires";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnés";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnements";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Maximum Like Vidéo";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Minimum Like Video";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Nombre vidéos +18";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vidéos non votées";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Vidéo";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Vidéo";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Vidéo";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Vidéo";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Utilisateur";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Utilisateur";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Utilisateur";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Utilisateur";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Ratio vidéos non votées";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonne";data[i][1]=donneesComportementLambda.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonnement";data[i][1]=donneesComportementLambda.get(data[i][0]);i+=1;
		
		data[i][0]="";data[i][1]=""; i+=1;
		data[i][0]="Comportement Fan";data[i][1]=""; i+=1;
		data[i][0]="Nombre Utilisateurs:";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Nombre Vidéos";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vue";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Nombre Likes";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Nombre Dislikes";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Nombre commentaires";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnés";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnements";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Maximum Like Vidéo";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Minimum Like Video";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Nombre vidéos +18";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vidéos non votées";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Vidéo";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Vidéo";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Vidéo";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Vidéo";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Utilisateur";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Utilisateur";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Utilisateur";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Utilisateur";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Ratio vidéos non votées";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonne";data[i][1]=donneesComportementFan.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonnement";data[i][1]=donneesComportementFan.get(data[i][0]);i+=1;

		data[i][0]="";data[i][1]=""; i+=1;
		data[i][0]="Comportement Objecteur";data[i][1]=""; i+=1;
		data[i][0]="Nombre Utilisateurs:";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Vidéos";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vue";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Likes";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Dislikes";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre commentaires";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnés";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnements";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Maximum Like Vidéo";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Minimum Like Video";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre vidéos +18";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vidéos non votées";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Vidéo";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Vidéo";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Vidéo";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Vidéo";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Utilisateur";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Utilisateur";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Utilisateur";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Utilisateur";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Ratio vidéos non votées";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonne";data[i][1]=donneesComportementObjecteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonnement";data[i][1]=donneesComportementObjecteur.get(data[i][0]);i+=1;

		data[i][0]="";data[i][1]=""; i+=1;
		data[i][0]="Comportement Posteur";data[i][1]=""; i+=1;
		data[i][0]="Nombre Utilisateurs:";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Vidéos";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vue";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Likes";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Dislikes";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre commentaires";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnés";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnements";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Maximum Like Vidéo";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Minimum Like Video";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre vidéos +18";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vidéos non votées";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Vidéo";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Vidéo";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Vidéo";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Vidéo";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Utilisateur";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Utilisateur";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Utilisateur";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Utilisateur";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Ratio vidéos non votées";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonne";data[i][1]=donneesComportementPosteur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonnement";data[i][1]=donneesComportementPosteur.get(data[i][0]);i+=1;

		data[i][0]="";data[i][1]=""; i+=1;
		data[i][0]="Comportement Suiveur";data[i][1]=""; i+=1;
		data[i][0]="Nombre Utilisateurs:";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Vidéos";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vue";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Likes";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Dislikes";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Nombre commentaires";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnés";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnements";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Maximum Like Vidéo";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Minimum Like Video";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Nombre vidéos +18";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vidéos non votées";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Vidéo";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Vidéo";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Vidéo";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Vidéo";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Utilisateur";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Utilisateur";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Utilisateur";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Utilisateur";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Ratio vidéos non votées";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonne";data[i][1]=donneesComportementSuiveur.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonnement";data[i][1]=donneesComportementSuiveur.get(data[i][0]);i+=1;
		
		data[i][0]="";data[i][1]=""; i+=1;
		data[i][0]="Comportement Rapide";data[i][1]=""; i+=1;
		data[i][0]="Nombre Utilisateurs:";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Nombre Vidéos";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vue";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Nombre Likes";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Nombre Dislikes";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Nombre commentaires";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnés";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Nombre Abonnements";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Maximum Like Vidéo";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Minimum Like Video";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Nombre vidéos +18";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Nombre de vidéos non votées";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Vidéo";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Vidéo";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Vidéo";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Vidéo";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Vue/Utilisateur";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Like/Utilisateur";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne DisLike/Utilisateur";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Commentaires/Utilisateur";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Ratio vidéos non votées";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonne";data[i][1]=donneesComportementRapide.get(data[i][0]); i+=1;
		data[i][0]="Moyenne Abonnement";data[i][1]=donneesComportementRapide.get(data[i][0]);
		
		data[i][0]="";data[i][1]=""; i+=1;
		data[i][0]="Visiteurs";data[i][1]=""; i+=1;
		data[i][0]="Nombre visiteurs";data[i][1]=this.youtube.getNbVisiteurs();
		this.data=data;
		return data;
	}
	
	
	
	//public void AfficherInformations(int nbvideo, int nbcommentaire, int nblike, int nbdislike, int moyenneLike, int MaxLike, int MinLike,int moyenneCommentaire, int MaxCommentaire, int MinCommentaire, int nbVueTotal,int nbAbonnementMoyen,)
	

	public Object[][] getTableauDonnees(){
		return formaterDonneesTableaux(youtube.infosPlateformes(Comportement.class),youtube.infosPlateformes(ComportementLambda.class),youtube.infosPlateformes(ComportementObjecteur.class),
				youtube.infosPlateformes(ComportementPosteur.class),youtube.infosPlateformes(ComportementFan.class),youtube.infosPlateformes(ComportementRapide.class),
				youtube.infosPlateformes(ComportementSuiveur.class));
	}


	public static void main(String[] args) {
		new Controleur();
	}


}
