package fr.view;

import java.awt.Component;
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

	private final String lienGts;
	public WindowEnregistrer(JTabbedPane tabbedPane, ArrayList<Onglet> listeOnglets, PanelInformations panelInfos,boolean nouveau,String lienGts) {
		this.lienGts=lienGts;
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
		private final String description;
		private final ArrayList<String>listeImages;
		private final int nbImages;

		public PanelEnregistrer(JFrame windowE, JTabbedPane tabbedPane, ArrayList<Onglet> listeOnglets, PanelInformations panelInfos,boolean nouveau) {
			this.windowE = windowE;
			this.nouveau=nouveau;
			this.setPreferredSize(new Dimension(500, 300));
			obdd = new OutilsBdd("Database.db");
			Component onglet = tabbedPane.getSelectedComponent();
			listeImages=((Onglet) onglet).getListeImages();
			description=((Onglet)onglet).getPbdd().getDescription().getDescription();
			nbImages=((Onglet)onglet).getNbIm();
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
					jbOk = new JButton("Valider la sauvegarde");
					jlFen = new JLabel("Vous avez enregistré l'objet " +this.nomFichier+ " dans la BDD avec les informations suivantes:");
					jlNomAuteur = new JLabel("Nom Auteur : " + this.nomAuteur);
					jlNomObjet = new JLabel("Nom Objet : " + this.nomFichier);
					jlDateAjout = new JLabel("Date d'ajout : " + this.dateAjoutt);
					jlDerniereModif = new JLabel("Dernière modification : " + this.dateModiff);
					jlNbChargements = new JLabel("Nombre de chargements : " + this.nChargements);
					jlNbRealisations = new JLabel("Nombre de réalisations : " + this.nRealisations);
					jlNbImages=new JLabel("Nombre d'images : "+listeImages.size());

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
				jbOk = new JButton("Valider la sauvegarde");
				jlFen = new JLabel("Vous allez enregistré l'objet " +this.nomFichier+ " dans la BDD avec les informations suivantes:");
				jlNomAuteur = new JLabel("Nom Auteur : " + this.nomAuteur);
				jlNomObjet = new JLabel("Nom Objet : " + this.nomFichier);
				jlDateAjout = new JLabel("Date d'ajout : " + this.dateAjoutt);
				jlDerniereModif = new JLabel("Dernière modification : " + this.dateModiff);
				jlNbChargements = new JLabel("Nombre de chargements : " + this.nChargements);
				jlNbRealisations = new JLabel("Nombre de réalisations : " + this.nRealisations);
				jlNbImages=new JLabel("Nombre d'images : "+listeImages.size());
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

		public boolean copier( File source, File destination ){ //Methode permettant la copie d'un fichier
			boolean resultat = false;

			// Declaration des flux
			java.io.FileInputStream sourceFile=null;
			java.io.FileOutputStream destinationFile=null;
			try {
				// Création du fichier :
				destination.createNewFile();
				// Ouverture des flux
				sourceFile = new java.io.FileInputStream(source);
				destinationFile = new java.io.FileOutputStream(destination);
				// Lecture par segment de 0.5Mo
				byte buffer[]=new byte[512*1024];
				int nbLecture;
				while( (nbLecture = sourceFile.read(buffer)) != -1 ) {
					destinationFile.write(buffer, 0, nbLecture);
				}

				// Copie réussie
				resultat = true;
			} catch( java.io.FileNotFoundException f ) {
			} catch( java.io.IOException e ) {
			} finally {
				// Quoi qu'il arrive, on ferme les flux
				try {
					sourceFile.close();
				} catch(Exception e) { }
				try {
					destinationFile.close();
				} catch(Exception e) { }
			}
			return( resultat );
		}

		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(jbOk)){
				if (nouveau){
					new File("fichiers/"+this.nomFichier).mkdirs();
					new File("fichiers/"+this.nomFichier+"/images").mkdirs();
					new File("fichiers/"+this.nomFichier+"/realisations").mkdirs();
					//copier le point gts
					copier( new File(lienGts), new File("fichiers/"+this.nomFichier+"/"+this.nomFichier+".gts"));
					//enregistrer les images.
					for (int i=0;i<listeImages.size();i++){
						if (copier( new File(listeImages.get(i)), new File("fichiers/"+this.nomFichier+"/images/"+this.nomFichier+listeImages.size()+".png") )){
							System.out.println("Sauvegarde réussie");
						}
					}
					obdd.addFile(this.nomFichier, "fichiers/"+this.nomFichier+"/"+this.nomFichier+".gts", this.description, this.nomAuteur, this.nChargements, listeImages.size(), this.nRealisations, "fichiers/"+this.nomFichier+"/images", 0);
				}
				else {

					String lien=obdd.getLinkImg(nomFichier);
					File repertoire = new File(lien);
					String[] listefichiers;
					File f;
					int i;
					if(repertoire.list()!=null){
						listefichiers=repertoire.list();
						//System.out.println(listefichiers.toString());
						for(i=0;i<listefichiers.length;i++){
							if(!(listeImages.contains(listefichiers[i]))){
								f = new File(listefichiers[i]);
								f.delete();
							}
						}
						ArrayList<String> fichiers=new ArrayList<String>();
						for (int j =0;j<listefichiers.length;j++){
							fichiers.add(listefichiers[j]);
						}
						for(int k=0;k<listeImages.size();k++){
							if(!(fichiers.contains(listeImages.get(k)))){
								copier( new File(listeImages.get(k)), new File("fichiers/"+this.nomFichier+"/images/"+this.nomFichier+listeImages.size()+".png"));
								System.out.println("Sauvegarde réussie");
							}
						}
					}


					System.out.println("cc");
					obdd.updateFile(this.nomFichier,this.description, this.nChargements, listeImages.size(), this.nRealisations, "fichiers/"+this.nomFichier+"/images/",0);
				}
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
