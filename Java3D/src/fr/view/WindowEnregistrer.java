package fr.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import fr.model.OutilsBdd;

public class WindowEnregistrer extends JFrame {

	public WindowEnregistrer(JTabbedPane tabbedPane, ArrayList<Onglet> listeOnglets, PanelInformations panelInfos,boolean nouveau) {
		PanelEnregistrer pE = new PanelEnregistrer(this, tabbedPane, listeOnglets, panelInfos,nouveau);
		this.setTitle("Enregistrer dans la BDD");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		this.setLocationRelativeTo(null);
		this.setContentPane(pE);
		this.setVisible(true);
	}

	public class PanelEnregistrer extends JPanel implements MouseListener{

		private JButton jbOk=null;
		private JLabel jlFen=null;
		private JLabel jlNomAuteur=null;
		private JLabel jlNomObjet=null;
		private JLabel jlDateAjout=null;
		private JLabel jlDerniereModif=null;
		private JLabel jlNbChargements=null;
		private JLabel jlNbImages=null;
		private JLabel jlNbRealisations=null;
		private final JFrame windowE;
		private final OutilsBdd obdd;
		private final boolean nouveau;
		private String nomAuteur;
		private String nomFichier;
		private String dateModiff;
		private String dateAjoutt;
		private int nImages;
		private int nRealisations;
		private int nChargements;

		public PanelEnregistrer(JFrame windowE, JTabbedPane tabbedPane, ArrayList<Onglet> listeOnglets, PanelInformations panelInfos,boolean nouveau) {
			this.windowE = windowE;
			this.nouveau=nouveau;
			this.setPreferredSize(new Dimension(500, 300));
			obdd = new OutilsBdd("Database.db");
			if(nouveau){
				JTextField j1 = new JTextField();
				JTextField j2 = new JTextField();
				ArrayList list = new ArrayList();
				list.add("Nom objet : \n");
				list.add(j1);
				list.add("Nom auteur : \n");
				list.add(j2);
				int res = JOptionPane.showOptionDialog(null, list.toArray(), "Saisissez les champs", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (j2.getText().isEmpty() || j1.getText().isEmpty())
					list.add("les champs sont obligatoires");
				while ((j2.getText().isEmpty() || j1.getText().isEmpty()) && res!=-1) {
					res=JOptionPane.showOptionDialog(null, list.toArray(), "Saisissez les champs", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}
				if(res!=-1){
					this.nomFichier=j1.getText();
					this.nomAuteur=j2.getText();
					this.dateAjoutt=panelInfos.getDateAjoutt();
					this.dateModiff=panelInfos.getDateModiff();
					this.nChargements=panelInfos.getnChargements();
					this.nRealisations=panelInfos.getnRealisations();
					this.nImages=panelInfos.getnImages();
					jbOk = new JButton("Ok");
					jlFen = new JLabel("Vous avez enregistré l'objet " +this.nomFichier+ " dans la BDD avec les informations suivantes:");
					jlNomAuteur = new JLabel("Nom Auteur : " + this.nomAuteur);
					jlNomObjet = new JLabel("Nom Objet : " + this.nomFichier);
					jlDateAjout = new JLabel("Date d'ajout : " + this.dateAjoutt);
					jlDerniereModif = new JLabel("Dernière modification : " + this.dateModiff);
					jlNbChargements = new JLabel("Nombre de chargements : " + this.nChargements);
					jlNbRealisations = new JLabel("Nombre de réalisations : " + this.nRealisations);
					jlNbImages=new JLabel("Nombre d'images : "+this.nImages);

				}
			}
			else {
				this.nomFichier=panelInfos.getNomFichier();
				this.nomAuteur=panelInfos.getNomAuteur();
				this.dateAjoutt=panelInfos.getDateAjoutt();
				this.dateModiff=panelInfos.getDateModiff();
				this.nChargements=panelInfos.getnChargements();
				this.nRealisations=panelInfos.getnRealisations();
				this.nImages=panelInfos.getnImages();
				jbOk = new JButton("Ok");
				jlFen = new JLabel("Vous allez enregistré l'objet " +this.nomFichier+ " dans la BDD avec les informations suivantes:");
				jlNomAuteur = new JLabel("Nom Auteur : " + this.nomAuteur);
				jlNomObjet = new JLabel("Nom Objet : " + this.nomFichier);
				jlDateAjout = new JLabel("Date d'ajout : " + this.dateAjoutt);
				jlDerniereModif = new JLabel("Dernière modification : " + this.dateModiff);
				jlNbChargements = new JLabel("Nombre de chargements : " + this.nChargements);
				jlNbRealisations = new JLabel("Nombre de réalisations : " + this.nRealisations);
				jlNbImages=new JLabel("Nombre d'images : "+this.nImages);
			}
			this.setLayout(new GridLayout(10, 1));

			this.add(jlFen);
			this.add(jlNomAuteur);
			this.add(jlNomObjet);
			this.add(jlDateAjout);
			this.add(jlDerniereModif);
			this.add(jlNbImages);
			this.add(jlNbChargements);
			this.add(jlNbRealisations);
			this.add(jbOk);

			jbOk.addMouseListener(this);

		}

		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(jbOk)){
				if (nouveau){
					new File("fichiers/"+this.nomFichier+"/images").mkdirs();
					new File("fichiers/"+this.nomFichier+"/realisations").mkdirs();
				}
				obdd.addFile(this.nomFichier, "fichiers/"+this.nomFichier, "", this.nomAuteur, this.nChargements, this.nImages, this.nRealisations, "fichiers/"+this.nomFichier+"/images", 0);
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
