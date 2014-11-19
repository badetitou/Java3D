package fr.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import fr.model.OutilsBdd;

public class WindowEnregistrer extends JFrame {

	public WindowEnregistrer(JTabbedPane tabbedPane, ArrayList<Onglet> listeOnglets, PanelInformations panelInfos) {
		PanelEnregistrer pE = new PanelEnregistrer(this, tabbedPane, listeOnglets, panelInfos);
		this.setTitle("Enregistrer dans la BDD");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		this.setLocationRelativeTo(null);
		this.setContentPane(pE);
		this.setVisible(true);
	}

	public class PanelEnregistrer extends JPanel implements MouseListener{
		
		public final JButton jbOk;
		public JLabel jlFen;
		public JLabel jlNomAuteur;
		public JLabel jlNomObjet;
		public JLabel jlDateAjout;
		public JLabel jlDerniereModif;
		public JLabel jlNbChargements;
		public JLabel jlNbImages;
		public JLabel jlNbRealisations;	
		private final JFrame windowE;
		private final OutilsBdd obdd;
		private final JTabbedPane tabbedPane;
		private final ArrayList<Onglet> listeOnglets;
		private final JPanel panelInfos;

		public PanelEnregistrer(JFrame windowE, JTabbedPane tabbedPane, ArrayList<Onglet> listeOnglets, PanelInformations panelInfos) {
			this.windowE = windowE;
			this.tabbedPane = tabbedPane;
			this.listeOnglets = listeOnglets;
			this.setPreferredSize(new Dimension(500, 300));
			obdd = new OutilsBdd("Database.db");

			//obdd.addFile(panelInfos.get, linkFile, desc, author, nbrOpen, nbrImg, nbrModif, linkImg, size);

			this.panelInfos = panelInfos;
		
			jbOk = new JButton("Ok");
			jlFen = new JLabel("Vous avez enregistré l'objet " + " dans la BDD avec les informations suivantes:");
			jlNomAuteur = new JLabel("Nom Auteur : " + panelInfos.getNomAuteur());
			jlNomObjet = new JLabel("Nom Objet : " + panelInfos.getNomFichier());
			jlDateAjout = new JLabel("Date d'ajout : " + panelInfos.getDateAjoutt());
			jlDerniereModif = new JLabel(" Dernière modification : " + panelInfos.getDateModiff());
			jlNbChargements = new JLabel("Nombre de chargements : " + panelInfos.getnChargements());
			jlNbRealisations = new JLabel("Nombre de réalisations : " + panelInfos.getnRealisations());
			
		
			this.setLayout(new GridLayout(9, 1));		
			
			this.add(jlFen);
			this.add(jlNomAuteur);
			this.add(jlNomObjet);
			this.add(jlDateAjout);
			this.add(jlDerniereModif);
			this.add(jlNbChargements);
			this.add(jlNbRealisations);
			this.add(jbOk);
						
			jbOk.addMouseListener(this);

		}
		
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(jbOk)){
				windowE.dispose();
			}
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
