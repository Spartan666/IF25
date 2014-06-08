package vue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Action;

import modele.Youtube;

import controleur.Controleur;

public class f2 extends JPanel implements ActionListener {
	private JTextField textField;
	private Youtube youtube;
	private Controleur controleur;
	private JLabel lblNewLabel;
	private JLabel lblNombreUtilisateur;
	
	private JButton btnDmarrerSimulation;
	private JButton btnEditerPosteur;
	private JButton btnEditerFan;
	private JButton btnEditerSuiveur;
	private JButton btnEditerObjecteur;
	private JButton btnEditerRapid;
	
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private FenetreConfPoste FconfPosteur;
	private FenetreConfFan FconfFan;
	private FenetreConfObjecteur FconfObjecteur;
	private FenetreConfRapide FconfRapide;
	private FenetreConfSuiveur FconfSuiveur;

	/**
	 * Create the panel.
	 */
	public f2(Youtube youtube, Controleur controleur,Fenetre fenetre) {
		
		FconfPosteur=new FenetreConfPoste();
		FconfFan=new FenetreConfFan();
		FconfObjecteur=new FenetreConfObjecteur();
		FconfRapide=new FenetreConfRapide();
		FconfSuiveur=new FenetreConfSuiveur();

		
		this.youtube=youtube;
		this.controleur=controleur;
		setLayout(null);
		
		lblNewLabel = new JLabel("Comportement Posteur");
		lblNewLabel.setBounds(10, 46, 172, 14);
		add(lblNewLabel);
		
		lblNombreUtilisateur = new JLabel("Nombre de posteur");
		lblNombreUtilisateur.setBounds(314, 38, 176, 14);
		add(lblNombreUtilisateur);
		
		textField = new JTextField();
		textField.setText("5");
		textField.setBounds(460, 35, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		btnEditerPosteur = new JButton("Editer");
		btnEditerPosteur.setBounds(192, 32, 89, 23);
		add(btnEditerPosteur);
		btnEditerPosteur.addActionListener(this);
		
		btnDmarrerSimulation = new JButton("D\u00E9marrer simulation");
		btnDmarrerSimulation.setBounds(262, 197, 164, 23);
		add(btnDmarrerSimulation);
		btnDmarrerSimulation.addActionListener(this);
		
		JLabel lblComportementFan = new JLabel("Comportement Fan");
		lblComportementFan.setBounds(10, 75, 172, 14);
		add(lblComportementFan);
		
		btnEditerFan = new JButton("Editer");
		btnEditerFan.setBounds(192, 61, 89, 23);
		add(btnEditerFan);
		btnEditerFan.addActionListener(this);

		
		JLabel lblNombreDeFan = new JLabel("Nombre de fan");
		lblNombreDeFan.setBounds(314, 67, 176, 14);
		add(lblNombreDeFan);
		
		textField_1 = new JTextField();
		textField_1.setText("5");
		textField_1.setColumns(10);
		textField_1.setBounds(460, 63, 86, 20);
		add(textField_1);
		
		JLabel lblComportementObjecteur = new JLabel("Comportement Objecteur");
		lblComportementObjecteur.setBounds(10, 104, 172, 14);
		add(lblComportementObjecteur);
		
		btnEditerObjecteur = new JButton("Editer");
		btnEditerObjecteur.setBounds(192, 90, 89, 23);
		add(btnEditerObjecteur);
		btnEditerObjecteur.addActionListener(this);

		
		JLabel lblNombreDobjecteur = new JLabel("Nombre d'objecteur");
		lblNombreDobjecteur.setBounds(314, 96, 176, 14);
		add(lblNombreDobjecteur);
		
		textField_2 = new JTextField();
		textField_2.setText("5");
		textField_2.setColumns(10);
		textField_2.setBounds(460, 92, 86, 20);
		add(textField_2);
		
		JLabel lblComportementRapide = new JLabel("Comportement Rapide");
		lblComportementRapide.setBounds(10, 133, 172, 14);
		add(lblComportementRapide);
		
		btnEditerRapid = new JButton("Editer");
		btnEditerRapid.setBounds(192, 119, 89, 23);
		add(btnEditerRapid);
		btnEditerRapid.addActionListener(this);

		
		JLabel lblNombreDeRapide = new JLabel("Nombre de rapide");
		lblNombreDeRapide.setBounds(314, 125, 176, 14);
		add(lblNombreDeRapide);
		
		textField_3 = new JTextField();
		textField_3.setText("5");
		textField_3.setColumns(10);
		textField_3.setBounds(460, 121, 86, 20);
		add(textField_3);
		
		JLabel lblComportementSuiveur = new JLabel("Comportement Suiveur");
		lblComportementSuiveur.setBounds(10, 162, 172, 14);
		add(lblComportementSuiveur);
		
		btnEditerSuiveur = new JButton("Editer");
		btnEditerSuiveur.setBounds(192, 153, 89, 23);
		add(btnEditerSuiveur);
		btnEditerSuiveur.addActionListener(this);

		
		JLabel lblNombreDeSuiveur = new JLabel("Nombre de suiveur");
		lblNombreDeSuiveur.setBounds(314, 159, 176, 14);
		add(lblNombreDeSuiveur);
		
		textField_4 = new JTextField();
		textField_4.setText("5");
		textField_4.setColumns(10);
		textField_4.setBounds(460, 150, 86, 20);
		add(textField_4);


	}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnDmarrerSimulation){
				new Thread(new Runnable() {
				      public void run() {
							controleur.configurerSimulation(genererDonnees());
				      }
				  }).start();
			}
			else if(e.getSource()==btnEditerPosteur){
				FconfPosteur.setVisible(true);
				System.out.println("ok");
			}
	else if(e.getSource()==btnEditerFan){
		FconfFan.setVisible(true);

			}
	else if(e.getSource()==btnEditerObjecteur){
		FconfObjecteur.setVisible(true);

	}
	else if(e.getSource()==btnEditerSuiveur){
		FconfSuiveur.setVisible(true);

	}
	else if(e.getSource()==btnEditerRapid){
		FconfRapide.setVisible(true);
	}
		}
		
	public ArrayList<Integer> genererDonnees(){
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		//Confposteur
		a.add(Integer.parseInt(textField.getText()));
		a.add(Integer.parseInt(FconfPosteur.getProbaLike().getText()));
		a.add(Integer.parseInt(FconfPosteur.getProbaDislike().getText()));
		a.add(Integer.parseInt(FconfPosteur.getProbaCommenter().getText()));
		a.add(Integer.parseInt(FconfPosteur.getProbaAbonner().getText()));
		a.add(Integer.parseInt(FconfPosteur.getBonusProbaCommenter().getText()));
		a.add(Integer.parseInt(FconfPosteur.getFrequencePostage().getText()));
		
		//ConfFan
		a.add(Integer.parseInt(textField_1.getText()));
		a.add(Integer.parseInt(FconfFan.getProbaLike().getText()));
		a.add(Integer.parseInt(FconfFan.getProbaDislike().getText()));
		a.add(Integer.parseInt(FconfFan.getProbaCommenter().getText()));
		a.add(Integer.parseInt(FconfFan.getProbaAbonner().getText()));
		a.add(Integer.parseInt(FconfFan.getBonusProbaCommenter().getText()));
		
		//ConfObjecteur
		a.add(Integer.parseInt(textField_1.getText()));
		a.add(Integer.parseInt(FconfObjecteur.getProbaLike().getText()));
		a.add(Integer.parseInt(FconfObjecteur.getProbaDislike().getText()));
		a.add(Integer.parseInt(FconfObjecteur.getProbaCommenter().getText()));
		a.add(Integer.parseInt(FconfObjecteur.getProbaAbonner().getText()));
		a.add(Integer.parseInt(FconfObjecteur.getBonusProbaCommenter().getText()));
		
		//ConfRapide
		a.add(Integer.parseInt(textField_1.getText()));
		a.add(Integer.parseInt(FconfRapide.getProbaLike().getText()));
		a.add(Integer.parseInt(FconfRapide.getProbaDislike().getText()));
		a.add(Integer.parseInt(FconfRapide.getProbaCommenter().getText()));
		a.add(Integer.parseInt(FconfRapide.getProbaAbonner().getText()));
		a.add(Integer.parseInt(FconfRapide.getBonusProbaCommenter().getText()));
		
		//ConfSuiveur
		a.add(Integer.parseInt(textField_1.getText()));
		a.add(Integer.parseInt(FconfSuiveur.getProbaLike().getText()));
		a.add(Integer.parseInt(FconfSuiveur.getProbaDislike().getText()));
		a.add(Integer.parseInt(FconfSuiveur.getProbaCommenter().getText()));
		a.add(Integer.parseInt(FconfSuiveur.getProbaAbonner().getText()));
		a.add(Integer.parseInt(FconfSuiveur.getBonusProbaCommenter().getText()));
		return a;		
	}
	}

