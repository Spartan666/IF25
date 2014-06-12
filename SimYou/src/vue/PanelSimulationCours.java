package vue;

import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;

import controleur.Controleur;
import javax.swing.JTable;
import javax.swing.plaf.FileChooserUI;
import javax.swing.text.html.HTMLDocument.Iterator;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class PanelSimulationCours extends JPanel implements ActionListener {
	JLabel lblNewLabel ;
	Controleur controleur;
	private JTable table;
	private JButton btnSauvegarder;
	/**
	 * Create the panel.
	 */
	public PanelSimulationCours(Controleur controleur,HashMap donnees) {
		setLayout(null);
			this.controleur=controleur;
			//A: Codify map in a two dimensional array:
			String[] columnNames = {"Key", "Value"};
			Object[][] data = new Object[donnees.size()][2];
			java.util.Iterator iterator = donnees.keySet().iterator();
			int row = 0;
			while ( iterator.hasNext() ) {
			Object key = iterator.next();
			data[row][0] = key;
			data[row][1] = donnees.get(key);
			row = row + 1;
			}
			table=new JTable(data,columnNames);
		table.setBounds(10, 36, 561, 425);
		add(table);
		
		JLabel lblSimyou = new JLabel("SIMYOU");
		lblSimyou.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSimyou.setBounds(33, 11, 235, 14);
		add(lblSimyou);
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setBounds(583, 46, 115, 25);
		add(btnSauvegarder);
		btnSauvegarder.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSauvegarder){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");   
			 
			int userSelection = fileChooser.showSaveDialog(this);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			     File file = fileChooser.getSelectedFile();
			    System.out.println("Save as file: " + file.getAbsolutePath());
				    	  controleur.SauvegarderModele(file);
				
			   
			}
		}
		
	}
	}


