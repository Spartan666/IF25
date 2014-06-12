package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.Controleur;


public class PanelAccueil extends JPanel implements ActionListener {
	private Image arrierePlan;
	private JButton start;
	private Controleur controleur;
	/**
	 * Create the panel.
	 */
	public PanelAccueil(Controleur controleur)  {
		this.controleur=controleur;
		arrierePlan = new ImageIcon("ressources/YouTubeLogo.png").getImage();
		start = new JButton("Démarrer");
		
		start.addActionListener(this);
		
		JPanel grilleBoutons = new JPanel();
		grilleBoutons.add(start);
		grilleBoutons.setOpaque(false);
		this.add(grilleBoutons);
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = (this.getParent().getWidth() - arrierePlan.getWidth(null)) / 2;
		int y = (this.getParent().getHeight() - arrierePlan.getHeight(null)) / 2;
		g.drawImage(arrierePlan, x, y, this.getParent());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			this.controleur.getFenetre().setContentPane(new PanelConfGenerale(this.controleur));
			this.controleur.getFenetre().validate();
			this.controleur.getFenetre().repaint();
		}
	}
	}

