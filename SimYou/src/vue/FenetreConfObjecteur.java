package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class FenetreConfObjecteur extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField ProbaCommenter;
	private JTextField ProbaLike;
	private JTextField ProbaDislike;
	private JTextField BonusProbaCommenter;
	private JButton okButton;
	private JTextField ProbaAbonner;
	private JLabel lblProbaAbonner_1;



	/**
	 * Create the dialog.
	 */
	public FenetreConfObjecteur() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			ProbaLike = new JTextField();
			ProbaLike.setText("10");
			ProbaLike.setBounds(37, 10, 86, 20);
			contentPanel.add(ProbaLike);
			ProbaLike.setColumns(10);
		}
		{
			ProbaDislike = new JTextField();
			ProbaDislike.setText("20");
			ProbaDislike.setBounds(37, 47, 86, 20);
			contentPanel.add(ProbaDislike);
			ProbaDislike.setColumns(10);
		}
		{
			ProbaCommenter = new JTextField();
			ProbaCommenter.setText("20");
			ProbaCommenter.setBounds(37, 78, 86, 20);
			contentPanel.add(ProbaCommenter);
			ProbaCommenter.setColumns(10);
		}
		{
			BonusProbaCommenter = new JTextField();
			BonusProbaCommenter.setText("20");
			BonusProbaCommenter.setBounds(37, 141, 86, 20);
			contentPanel.add(BonusProbaCommenter);
			BonusProbaCommenter.setColumns(10);
		}
		{
			JLabel lblProbalike = new JLabel("Proba Like");
			lblProbalike.setBounds(133, 13, 156, 14);
			contentPanel.add(lblProbalike);
		}
		{
			JLabel lblProbaDislike = new JLabel("Proba Dislike");
			lblProbaDislike.setBounds(133, 50, 156, 14);
			contentPanel.add(lblProbaDislike);
		}
		{
			JLabel lblProbaCommenter = new JLabel("Proba Commenter");
			lblProbaCommenter.setBounds(133, 82, 156, 14);
			contentPanel.add(lblProbaCommenter);
		}
		{
			JLabel lblProbaAbonner = new JLabel("Bonus Proba Commenter");
			lblProbaAbonner.setBounds(133, 144, 156, 14);
			contentPanel.add(lblProbaAbonner);
		}
		{
			ProbaAbonner = new JTextField();
			ProbaAbonner.setText("20");
			ProbaAbonner.setColumns(10);
			ProbaAbonner.setBounds(37, 110, 86, 20);
			contentPanel.add(ProbaAbonner);
		}
		{
			lblProbaAbonner_1 = new JLabel("Proba Abonner");
			lblProbaAbonner_1.setBounds(133, 113, 156, 14);
			contentPanel.add(lblProbaAbonner_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(this);
			}
		}
	}

	public JTextField getBonusProbaCommenter() {
		return BonusProbaCommenter;
	}

	public void setBonusProbaCommenter(JTextField bonusProbaCommenter) {
		BonusProbaCommenter = bonusProbaCommenter;
	}



	public JTextField getProbaCommenter() {
		return ProbaCommenter;
	}

	public void setProbaCommenter(JTextField probaCommenter) {
		ProbaCommenter = probaCommenter;
	}

	public JTextField getProbaLike() {
		return ProbaLike;
	}

	public void setProbaLike(JTextField probaLike) {
		ProbaLike = probaLike;
	}

	public JTextField getProbaDislike() {
		return ProbaDislike;
	}

	public void setProbaDislike(JTextField probaDislike) {
		ProbaDislike = probaDislike;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}
	
	public JTextField getProbaAbonner() {
		return ProbaAbonner;
	}

	public void setProbaAbonner(JTextField probaAbonner) {
		ProbaAbonner = probaAbonner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==okButton){
			this.setVisible(false);
		}
	}

}
