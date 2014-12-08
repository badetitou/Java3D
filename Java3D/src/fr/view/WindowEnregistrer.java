package fr.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import fr.model.CouplePoint;
import fr.model.Face;
import fr.model.Model;
import fr.model.OutilsBdd;
import fr.model.Point;

public class WindowEnregistrer extends JFrame {

	private final String lienGts;

	public WindowEnregistrer(JTabbedPane tabbedPane,
			ArrayList<Object> listeOnglets, PanelInformations panelInfos,
			boolean nouveau, String lienGts) {
		this.lienGts = lienGts;
		PanelEnregistrer pE = new PanelEnregistrer(this, tabbedPane,
				panelInfos, nouveau);
		this.setTitle("Enregistrer dans la BDD");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		this.setLocationRelativeTo(null);
		this.setContentPane(pE);
		this.setVisible(true);
	}

	public class PanelEnregistrer extends JPanel implements MouseListener {

		private JButton jbOk = null;
		private JLabel jlFen = null;
		private JLabel jlNomAuteur = null;
		private JLabel jlNomObjet = null;
		private JLabel jlDateAjout = null;
		private JLabel jlDerniereModif = null;
		private JLabel jlNbChargements = null;
		private JLabel jlNbImages = null;
		private JLabel jlNbRealisations = null;
		private final JFrame windowE;
		private final OutilsBdd obdd;
		private boolean nouveau;
		private String nomAuteur;
		private String nomFichier;
		private String dateModiff;
		private String dateAjoutt;
		private int nImages;
		private int nRealisations;
		private int nChargements;
		private String description;
		private final ArrayList<String> listeImages;
		private final int nbImages;
		private final Component onglet;
		
		private Model m;
		
		public PanelEnregistrer(JFrame windowE, JTabbedPane tabbedPane,
				PanelInformations panelInfos, boolean nouveau) {
			this.windowE = windowE;
			this.nouveau = nouveau;
			this.setPreferredSize(new Dimension(500, 300));
			obdd = new OutilsBdd("Database.db");
			onglet = tabbedPane.getSelectedComponent();
			listeImages = ((Onglet) onglet).getListeImages();
			description = ((Onglet) onglet).getPbdd().getDescription()
					.getDescription();
			nbImages = ((Onglet) onglet).getNbIm();
			try {
				m = ((Onglet)onglet).getDp().getModel();
				if (this.nouveau) {
					JTextField j1 = new JTextField();
					JTextField j2 = new JTextField();
					// JButton bAnnul=new JButton("Annuler");
					ArrayList list = new ArrayList();
					list.add("Nom objet : \n");
					list.add(j1);
					list.add("Nom auteur : \n");
					list.add(j2);
					// list.add(bAnnul);
					int res = JOptionPane.showOptionDialog(null,
							list.toArray(), "Saisissez les champs",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, null, null);
					if (j2.getText().isEmpty() || j1.getText().isEmpty())
						list.add("les champs sont obligatoires");
					while ((j2.getText().isEmpty() || j1.getText().isEmpty())
							&& res != -1) {
						res = JOptionPane.showOptionDialog(null,
								list.toArray(), "Saisissez les champs",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, null,
								null);
					}
					if (res != -1) {
						this.nomFichier = j1.getText();
						this.nomAuteur = j2.getText();
						this.dateAjoutt = panelInfos.getDateAjoutt();
						this.dateModiff = panelInfos.getDateModiff();
						this.nChargements = panelInfos.getnChargements();
						this.nRealisations = panelInfos.getnRealisations();
						this.nImages = panelInfos.getnImages();
						jbOk = new JButton("Valider la sauvegarde");
						jlFen = new JLabel(
								"Vous avez enregistré l'objet "
										+ this.nomFichier
										+ " dans la BDD avec les informations suivantes:");
						jlNomAuteur = new JLabel("Nom Auteur : "
								+ this.nomAuteur);
						jlNomObjet = new JLabel("Nom Objet : "
								+ this.nomFichier);
						jlDateAjout = new JLabel("Date d'ajout : "
								+ this.dateAjoutt);
						jlDerniereModif = new JLabel("Dernière modification : "
								+ this.dateModiff);
						jlNbChargements = new JLabel("Nombre de chargements : "
								+ this.nChargements);
						jlNbRealisations = new JLabel(
								"Nombre de réalisations : "
										+ this.nRealisations);
						jlNbImages = new JLabel("Nombre d'images : "
								+ listeImages.size());

					}
				} else {
					this.nomFichier = panelInfos.getNomFichier();
					this.nomAuteur = panelInfos.getNomAuteur();
					this.dateAjoutt = panelInfos.getDateAjoutt();
					this.dateModiff = panelInfos.getDateModiff();
					this.nChargements = panelInfos.getnChargements();
					this.nRealisations = panelInfos.getnRealisations();
					this.nImages = panelInfos.getnImages();
					jbOk = new JButton("Valider la sauvegarde");
					jlFen = new JLabel("Vous allez enregistré l'objet "
							+ this.nomFichier
							+ " dans la BDD avec les informations suivantes:");
					jlNomAuteur = new JLabel("Nom Auteur : " + this.nomAuteur);
					jlNomObjet = new JLabel("Nom Objet : " + this.nomFichier);
					jlDateAjout = new JLabel("Date d'ajout : "
							+ this.dateAjoutt);
					jlDerniereModif = new JLabel("Dernière modification : "
							+ this.dateModiff);
					jlNbChargements = new JLabel("Nombre de chargements : "
							+ this.nChargements);
					jlNbRealisations = new JLabel("Nombre de réalisations : "
							+ this.nRealisations);
					jlNbImages = new JLabel("Nombre d'images : "
							+ listeImages.size());
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Copie d'un model courament selectionner à un fichier donner en paramètre
		 * 
		 * @param source Le model
		 * @param destination Le fichier 
		 * @return true si opération réussi false sinon
		 */
		public boolean copier(File destination) {
			boolean resultat = false;

			// Declaration des flux
			java.io.FileOutputStream destinationFile = null;
			try {
				// Création du fichier :
				destination.createNewFile();
				// Ouverture des flux
				destinationFile = new java.io.FileOutputStream(destination);
				PrintWriter pw = new PrintWriter(destination);
				Collections.sort(m.getListPoint());
				
				pw.println(m.getListPoint().size() + " " + m.getSegment().size() + " " + m.getFace().size());
				for (Point p : m.getListPoint()){
					pw.println((float)p.x + " " + (float)p.y + " " + (float)p.z);
				}
				Set<Entry<Integer, CouplePoint>> s = m.getSegment().entrySet();
				Iterator<Entry<Integer, CouplePoint>> it = s.iterator();
				while (it.hasNext()){
					pw.println(it.next().getValue());
				}
				for (Face f : m.getFace()){
					pw.println(f.getSegment1() + " " + f.getSegment2() + " " + f.getSegment3());
				}
				
				// Copie réussie
				resultat = true;
			} catch (java.io.FileNotFoundException f) {
			} catch (java.io.IOException e) {
			} finally {
				// Quoi qu'il arrive, on ferme les flux
				try {
					destinationFile.close();
				} catch (Exception e) {
				}
			}
			return (resultat);
		}

		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(jbOk)){
				if (this.nouveau){
					new File("fichiers"+File.separator+this.nomFichier).mkdirs();
					new File("fichiers"+File.separator+this.nomFichier+File.separator+"images").mkdirs();
					new File("fichiers"+File.separator+this.nomFichier+File.separator+"realisations").mkdirs();
					//copier le point gts
					copier(new File("fichiers"+File.separator+this.nomFichier+File.separator+this.nomFichier+".gts"));
					//enregistrer les images.
					for (int i=0;i<listeImages.size();i++){
						File ff=new File("fichiers"+File.separator+this.nomFichier+File.separator+"images"+File.separator+this.nomFichier+i+".png");
						int j=i;
						while(ff.exists()){
							ff=new File("fichiers"+File.separator+this.nomFichier+File.separator+"images"+File.separator+this.nomFichier+j+".png");
							j++;
						}
					}
					obdd.addFile(this.nomFichier, "fichiers"+File.separator+this.nomFichier+File.separator+this.nomFichier+".gts", this.description, this.nomAuteur, this.nChargements, listeImages.size(), this.nRealisations, "fichiers"+File.separator+this.nomFichier+File.separator+"images"+File.separator, 0);
					this.nouveau=false;
					((Onglet)onglet).setNouveau(false);
					((Onglet)onglet).actualiserOnglet(this.nomFichier);
					((Onglet)onglet).getPbdd().getInformations().setNouveau(false);
					((Onglet)onglet).getPbdd().getPanelDescription().setNouveau(false);
					((Onglet)onglet).getPbdd().getImages().setNouveau(false);
				}
				else {

					String lien=obdd.getLinkImg(nomFichier);
					File repertoire = new File(lien);
					File[] listefichiers;
					File f;
					int i;
					if(repertoire.list()!=null){
						listefichiers=repertoire.listFiles();

						ArrayList<String> fichiers=new ArrayList<String>();
						for (int j =0;j<listefichiers.length;j++){
							fichiers.add(listefichiers[j]+"");
						}

						int g=0;
						for(i=0;i<fichiers.size();i++){
							if(!(listeImages.contains(fichiers.get(i)))){
								listefichiers[g].delete();
								fichiers.remove(i);
								i--;
							}
							g++;
						}

						for(int k=0;k<listeImages.size();k++){
							if(!(fichiers.contains(listeImages.get(k)))){
								File ff=new File("fichiers"+File.separator+this.nomFichier+File.separator+"images"+File.separator+this.nomFichier+k+".png");
								copier(ff);
							}
						}
					}
					this.description=((Onglet)onglet).getPbdd().getDescription().getDescription();
					obdd.updateFile(this.nomFichier,this.description, this.nChargements, listeImages.size(), this.nRealisations, "fichiers"+File.separator+this.nomFichier+File.separator+"images"+File.separator,0);
				}
				((Onglet)onglet).getPbdd().getInformations().actualiserInfos(this.nomFichier, this.nomAuteur, this.nbImages, 0,obdd.getDateLastModif(nomFichier));
				((Onglet)onglet).getPbdd().getPanelDescription().actualiserDesc(this.description);
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
