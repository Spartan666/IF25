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
	private Youtube youtube;
	private Controleur controleur;


	public Fenetre(Youtube youtube, Controleur controleur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(new f2(youtube,controleur,this));
		setVisible(true);
		
	}
	
	


}
