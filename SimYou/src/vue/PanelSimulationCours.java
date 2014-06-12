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
import java.lang.reflect.Array;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;


public class PanelSimulationCours extends JPanel implements ActionListener {
	JLabel lblNewLabel ;
	Controleur controleur;
	private JTable table;
	private JButton btnSauvegarder;
	JButton btnSauvegarder_1;
	private File file;
	private File file2;

	private JButton btnFichiers;

	/**
	 * Create the panel.
	 */
	public PanelSimulationCours(Controleur controleur,Object[][] data) {
		setLayout(null);
			this.controleur=controleur;
			String[] columnNames = {"Key", "Value"};

		
		JLabel lblSimyou = new JLabel("SIMYOU");
		lblSimyou.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSimyou.setBounds(33, 11, 235, 14);
		add(lblSimyou);
		
		btnSauvegarder = new JButton("Statistiques");
		btnSauvegarder.setBounds(644, 48, 115, 23);
		add(btnSauvegarder);
		btnSauvegarder.addActionListener(this);
		
		btnSauvegarder_1 = new JButton("R\u00E9seau chaines");
		btnSauvegarder_1.setBounds(644, 82, 115, 23);
		add(btnSauvegarder_1);
		btnSauvegarder_1.addActionListener(this);
		
		JLabel lblSauvegarder = new JLabel("Sauvegarder");
		lblSauvegarder.setBounds(664, 23, 75, 14);
		add(lblSauvegarder);
		
		btnFichiers = new JButton("Fichiers");
		btnFichiers.addActionListener(this);
		btnFichiers.setBounds(644, 116, 115, 23);
		add(btnFichiers);
		
		table=new JTable(data,columnNames);
		
	
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(33, 52, 605, 412);
		add(scrollPane);
		


		this.actualiserTableaux();
					
	}
	
	public void actualiserTableaux(){
		new Thread(new Runnable(){ 
		      public void run() {
		    	while(true) {
		    		try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		Object[][] data1=controleur.getTableauDonnees();

					for(int i=0;i<data1.length;i++){
						table.setValueAt(data1[i][0], i, 0);
						table.setValueAt(data1[i][1], i, 1);

					}
		    	}
		      }}).start();
	}
	public JTable getTable() {
		return table;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSauvegarder){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");   
			 
			int userSelection = fileChooser.showSaveDialog(this);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			     File file = fileChooser.getSelectedFile();
			     if(!file.getAbsolutePath().toLowerCase().endsWith(".txt"))
			     {
			         file= new File(file.getAbsolutePath() + ".txt");
			     }
			    System.out.println("Save as file: " + file.getAbsolutePath());
				    	  controleur.SauvegarderModele(file);
				
			   
			}
		}
		else if(e.getSource()==btnSauvegarder_1){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");   
			 
			int userSelection = fileChooser.showSaveDialog(this);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			     file = fileChooser.getSelectedFile();
			    System.out.println("Save as file: " + file.getAbsolutePath());
			    if(!file.getAbsolutePath().toLowerCase().endsWith(".gexf"))
			     {
			         file= new File(file.getAbsolutePath() + ".gexf");
			     }
			new Thread(new Runnable() {
			      public void run() {
				    	controleur.SauvegarderGrapheChaines(file);
			      }
			}).start();
			}
		}
		else if(e.getSource()==btnFichiers){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");   
			 
			int userSelection = fileChooser.showSaveDialog(this);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			     file = fileChooser.getSelectedFile();
			    System.out.println("Save as file: " + file.getAbsolutePath());
			    	String chemin=file.getAbsolutePath();
			         file= new File(chemin + ".gexf");
			         file2= new File(chemin + ".txt");
			new Thread(new Runnable() {
			      public void run() {
				    	controleur.SauvegarderGrapheChaines(file);
				    	controleur.SauvegarderModele(file2);
			      }
			}).start();
			
		}
		}
	}
	}


