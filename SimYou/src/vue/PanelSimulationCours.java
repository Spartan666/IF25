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
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setBounds(644, 52, 115, 25);
		add(btnSauvegarder);
		btnSauvegarder.addActionListener(this);
		
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
						Thread.sleep(6000);
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
			    System.out.println("Save as file: " + file.getAbsolutePath());
				    	  controleur.SauvegarderModele(file);
				
			   
			}
		}
		
	}
	}


