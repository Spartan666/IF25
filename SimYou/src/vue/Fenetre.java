package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Youtube;

import controleur.Controleur;

public class Fenetre extends JFrame {
	
	

	private JPanel contentPane;
	private Controleur controleur;




	public Fenetre(Controleur controleur) {
		this.controleur=controleur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(new PanelAccueil(this.controleur));
		setVisible(true);
		
	}




	


}
