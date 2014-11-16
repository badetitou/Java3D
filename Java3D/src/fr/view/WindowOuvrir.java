package fr.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WindowOuvrir extends JFrame {

	PanelOuvrir pO = new PanelOuvrir();

	public WindowOuvrir() {
		this.setTitle("Ouvrir");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		this.setLocationRelativeTo(null);
		this.setContentPane(pO);
		this.setVisible(true);
	}

	public class PanelOuvrir extends JPanel implements MouseListener{
		private final JTextArea rTag;
		private final JTextArea nFichier;
		private final JButton rAvancee;
		private final JButton ouvrir;
		private final JButton annuler;
		private JList bdd;
		private final JLabel jlb1;
		private final JLabel jlb2;

		public PanelOuvrir() {
			this.setPreferredSize(new Dimension(500, 300));
			rTag = new JTextArea();
			rTag.setPreferredSize(new Dimension(20, 70));
			rTag.setEditable(false);
			nFichier = new JTextArea();
			nFichier.setPreferredSize(new Dimension(20, 70));
			nFichier.setEditable(false);
			rAvancee = new JButton("Recherche Avancée");
			ouvrir = new JButton("Ouvrir");
			annuler = new JButton("Annuler");
			bdd = new JList();
			jlb1 = new JLabel();
			jlb1.setText("Recherche par mots clés: ");
			jlb2 = new JLabel();
			jlb2.setText("Nom du fichier: ");

		//	this.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			this.add(jlb1);
			gbc.gridx = 1;
			this.add(rTag);
			gbc.gridx = 2;	
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(rAvancee);
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(bdd);
			gbc.gridx = 0;
			gbc.gridy = 4;
			this.add(jlb2);
			gbc.gridx = 1;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(nFichier);
			gbc.gridx = 0;
			gbc.gridy = 5;
			this.add(ouvrir);
			gbc.gridx = 1;
			this.add(annuler);

		}

		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
