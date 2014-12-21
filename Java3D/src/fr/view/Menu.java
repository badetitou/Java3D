package fr.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.model.Face;
import fr.model.Model;
import fr.model.OutilsBdd;

public class Menu extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PanelInformations panelInfos;

	private final JMenu mFichier;
	private final JMenu mEdition;
	private final JMenu mOptions;
	private final JMenu mInfos;
	// Items Menu Fichier
	private final JMenuItem mIFImporter;
	private final JMenuItem mIFOuvrir;
	private final JMenu mIFRecents;
	private final JMenuItem mIFEnregistrer;
	private final JMenuItem mIFExporter;
	private final JMenuItem mIFFermer;
	private final JMenuItem mIFImprimer;
	private final JMenuItem mIFProprietes;
	private final JMenuItem mIFQuitter;
	// Items Menu Edition
	private final JMenuItem mIEDefaire;
	private final JMenuItem mIERefaire;
	// Items Menu Options
	private final JMenuItem mIOBoiteOutils;
	private final JMenuItem mIOPreferences;
	// Items Menu Infos
	private final JMenuItem mIIaPropos;
	private final JMenuItem mIIContacts;
	private final JMenuItem recent1;
	private final JMenuItem recent2;
	private final JMenuItem recent3;
	private final JMenuItem recent4;
	private final JMenuItem recent5;
	private String nameRecent1;
	private String nameRecent2;
	private String nameRecent3;
	private String nameRecent4;
	private String nameRecent5;
	private final JTabbedPane tabbedPane;

	private static int nbOngletsImport=0;

	private String nomFichier;
	private Onglet onglet;

	private String lienGts;
	private final OutilsBdd obdd;

	private final ArrayList<Object> listeOnglets;
	ArrayList<MyDeskTopPane> listeFichiersRecents;

	public Menu(final JTabbedPane tabbedPane, ArrayList<Object> listeOnglets,ArrayList<MyDeskTopPane> listeFichiersRecents) {
		this.tabbedPane = tabbedPane;
		this.listeOnglets = listeOnglets;
		this.panelInfos = panelInfos;
		this.listeFichiersRecents=listeFichiersRecents;
		this.setPreferredSize(new Dimension((int) Window.outil.getScreenSize().getWidth(), 30));
		obdd=new OutilsBdd("Database.db");

		// Cr�ation du Menu
		mFichier = new JMenu("Fichier");
		mEdition = new JMenu("Edition");
		mOptions = new JMenu("Options");
		mInfos = new JMenu("?");

		// Dimensions des boutons
		mFichier.setPreferredSize(new Dimension(60, 350));
		mEdition.setPreferredSize(new Dimension(60, 350));
		mOptions.setPreferredSize(new Dimension(60, 350));
		mInfos.setPreferredSize(new Dimension(60, 350));

		// Cr�ation des sous menus
		// Sous menu Fichier
		mIFImporter = new JMenuItem("Importer");
		mIFOuvrir = new JMenuItem("Ouvrir");
		mIFRecents = new JMenu("Fichiers recents");
		mIFEnregistrer = new JMenuItem("Enregistrer dans la BDD");
		mIFExporter = new JMenuItem("Exporter");

		mIFFermer = new JMenuItem("Fermer");
		mIFImprimer = new JMenuItem("Imprimer");
		mIFProprietes = new JMenuItem("Propri�t�s");
		mIFQuitter = new JMenuItem("Quitter");

		// /Action des MenuItems Fichier

		mIFImporter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mIFOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		// mIFRecents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
		// InputEvent.CTRL_MASK));
		mIFEnregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		// mIFEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
		// InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mIFExporter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		mIFFermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				InputEvent.CTRL_MASK));
		mIFImprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_MASK));
		mIFProprietes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,
				InputEvent.CTRL_MASK));
		mIFQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));

		// Sous menu Edition
		mIEDefaire = new JMenuItem("D�faire");
		mIERefaire = new JMenuItem("Refaire");

		// Action des MenuItems Edition

		mIEDefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				InputEvent.CTRL_MASK));
		mIERefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				InputEvent.CTRL_MASK));

		// Sous menu Options
		mIOBoiteOutils = new JMenuItem("Bo�te � Outils");
		mIOPreferences = new JMenuItem("Pr�f�rences");

		// Action des MenuItems Options

		// Sous menu Infos
		mIIaPropos = new JMenuItem("A propos");
		mIIContacts = new JMenuItem("Contacts");

		// action des MenuItems Infos

		mIIaPropos.setAccelerator(KeyStroke.getKeyStroke("F1"));
		mIIContacts.setAccelerator(KeyStroke.getKeyStroke("F2"));


		//recents
		/*
		nameRecent1=obdd.getFile(listeFichiersRecents.get(0).getUrl());
		nameRecent2=obdd.getFile(listeFichiersRecents.get(1).getUrl());
		nameRecent3=obdd.getFile(listeFichiersRecents.get(2).getUrl());
		nameRecent4=obdd.getFile(listeFichiersRecents.get(3).getUrl());
		nameRecent5=obdd.getFile(listeFichiersRecents.get(4).getUrl());
		 */
		recent1=new JMenuItem(listeFichiersRecents.get(0).getUrl());
		recent2=new JMenuItem(listeFichiersRecents.get(1).getUrl());
		recent3=new JMenuItem(listeFichiersRecents.get(2).getUrl());
		recent4=new JMenuItem(listeFichiersRecents.get(3).getUrl());
		recent5=new JMenuItem(listeFichiersRecents.get(4).getUrl());

		mIFRecents.add(recent1);
		mIFRecents.add(recent2);
		mIFRecents.add(recent3);
		mIFRecents.add(recent4);
		mIFRecents.add(recent5);

		// Ajout de chaque sous menu � son menu
		mFichier.add(mIFImporter);
		mFichier.add(mIFOuvrir);
		mFichier.add(mIFRecents);
		mFichier.addSeparator();
		mFichier.add(mIFEnregistrer);
		mFichier.add(mIFExporter);
		mFichier.add(mIFFermer);
		mFichier.addSeparator();
		mFichier.add(mIFImprimer);
		mFichier.addSeparator();
		mFichier.add(mIFProprietes);
		mFichier.addSeparator();
		mFichier.add(mIFQuitter);
		mEdition.add(mIEDefaire);
		mEdition.add(mIERefaire);
		mOptions.add(mIOBoiteOutils);
		mOptions.add(mIOPreferences);
		mInfos.add(mIIaPropos);
		mInfos.add(mIIContacts);

		this.add(mFichier);
		this.add(mEdition);
		this.add(mOptions);
		this.add(mInfos);

		mFichier.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				if(e.getSource().equals(mFichier) && tabbedPane.getSelectedComponent() instanceof OngletMenu){
					mIFEnregistrer.setEnabled(false);
					mIFExporter.setEnabled(false);
					mIFProprietes.setEnabled(false);
				}

				if(e.getSource().equals(mFichier) && tabbedPane.getSelectedComponent() instanceof Onglet){
					mIFEnregistrer.setEnabled(true);
					mIFExporter.setEnabled(true);
					mIFProprietes.setEnabled(true);
				}

			}

			@Override
			public void menuDeselected(MenuEvent e) {
				if(e.getSource().equals(mFichier) && tabbedPane.getSelectedComponent() instanceof Onglet){
					mIFEnregistrer.setEnabled(true);
					mIFExporter.setEnabled(true);
					mIFProprietes.setEnabled(true);
				}
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				if(e.getSource().equals(mFichier) && tabbedPane.getSelectedComponent() instanceof Onglet){
					mIFEnregistrer.setEnabled(true);
					mIFExporter.setEnabled(true);
					mIFProprietes.setEnabled(true);
				}
			}
		});
		recent1.addActionListener(this);
		recent2.addActionListener(this);
		recent3.addActionListener(this);
		recent4.addActionListener(this);
		recent5.addActionListener(this);
		mIFOuvrir.addActionListener(this);
		mIFEnregistrer.addActionListener(this);
		mIFQuitter.addActionListener(this);
		mIFImporter.addActionListener(this);
		mIFFermer.addActionListener(this);
		mIFExporter.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(recent1)){
			onglet = new Onglet(listeFichiersRecents.get(0), tabbedPane, nameRecent1,obdd.getAuthor(nameRecent1), false, listeOnglets);
			panelInfos = onglet.getPinfos();
			tabbedPane.addTab(nameRecent1, onglet);
			onglet.dessineOnglet();
			tabbedPane.setSelectedComponent(onglet);
			mIFEnregistrer.setEnabled(true);
			mIFExporter.setEnabled(true);
			mIFProprietes.setEnabled(true);
		}

		else if(e.getSource().equals(recent2)){
			onglet = new Onglet(listeFichiersRecents.get(1), tabbedPane, nameRecent2,obdd.getAuthor(nameRecent2), false, listeOnglets);
			panelInfos = onglet.getPinfos();
			tabbedPane.addTab(nameRecent2, onglet);
			onglet.dessineOnglet();
			tabbedPane.setSelectedComponent(onglet);
			mIFEnregistrer.setEnabled(true);
			mIFExporter.setEnabled(true);
			mIFProprietes.setEnabled(true);
		}

		if(e.getSource().equals(recent3)){
			onglet = new Onglet(listeFichiersRecents.get(2), tabbedPane, nameRecent3,obdd.getAuthor(nameRecent3), false, listeOnglets);
			panelInfos = onglet.getPinfos();
			tabbedPane.addTab(nameRecent3, onglet);
			onglet.dessineOnglet();
			tabbedPane.setSelectedComponent(onglet);
			mIFEnregistrer.setEnabled(true);
			mIFExporter.setEnabled(true);
			mIFProprietes.setEnabled(true);
		}


		if(e.getSource().equals(recent4)){
			onglet = new Onglet(listeFichiersRecents.get(3), tabbedPane, nameRecent4,obdd.getAuthor(nameRecent4), false, listeOnglets);
			panelInfos = onglet.getPinfos();
			tabbedPane.addTab(nameRecent4, onglet);
			onglet.dessineOnglet();
			tabbedPane.setSelectedComponent(onglet);
			mIFEnregistrer.setEnabled(true);
			mIFExporter.setEnabled(true);
			mIFProprietes.setEnabled(true);
		}


		if(e.getSource().equals(recent5)){
			onglet = new Onglet(listeFichiersRecents.get(4), tabbedPane, nameRecent5,obdd.getAuthor(nameRecent5), false, listeOnglets);
			panelInfos = onglet.getPinfos();
			tabbedPane.addTab(nameRecent5, onglet);
			onglet.dessineOnglet();
			tabbedPane.setSelectedComponent(onglet);
			mIFEnregistrer.setEnabled(true);
			mIFExporter.setEnabled(true);
			mIFProprietes.setEnabled(true);
		}

		else if (e.getSource().equals(mIFOuvrir)) {
			WindowOuvrir windowO = new WindowOuvrir(tabbedPane, listeOnglets);
			panelInfos = windowO.getPanelInfos();
			mIFEnregistrer.setEnabled(true);
			mIFExporter.setEnabled(true);
			mIFProprietes.setEnabled(true);
		}
		else if (e.getSource().equals(mIFEnregistrer)) {
			Component onglet = tabbedPane.getSelectedComponent();
			int res = 0;
			String nFile="";
			String nAutor="";

			if(((Onglet) onglet).isNouveau()){
				JTextField j1 = new JTextField();
				JTextField j2 = new JTextField();
				//JButton bAnnul=new JButton("Annuler");
				ArrayList list = new ArrayList();
				list.add("Nom objet : \n");
				list.add(j1);
				list.add("Nom auteur : \n");
				list.add(j2);
				//list.add(bAnnul);
				res = JOptionPane.showOptionDialog(null, list.toArray(), "Saisissez les champs", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (j2.getText().isEmpty() || j1.getText().isEmpty())
					list.add("les champs sont obligatoires");
				while ((j2.getText().isEmpty() || j1.getText().isEmpty()) && res!=-1) {
					res=JOptionPane.showOptionDialog(null, list.toArray(), "Saisissez les champs", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}
				if(res!=-1 && res!=JOptionPane.CLOSED_OPTION){
					nFile=j1.getText();
					nAutor=j2.getText();
				}
			}
			else {
				nFile=((Onglet) onglet).getPinfos().getNomFichier();
				nAutor=((Onglet) onglet).getPinfos().getNomAuteur();
			}
			WindowEnregistrer windowE;
			if(res!=-1)
				windowE = new WindowEnregistrer(tabbedPane,listeOnglets, ((Onglet) onglet).getPinfos(),((Onglet) onglet).isNouveau(),nFile,nAutor,((Onglet) onglet).getDp().getUrl());
		}

		else if (e.getSource().equals(mIFQuitter)) {
			int option = JOptionPane.showConfirmDialog(null,
					"Voulez-vous vraiment quitter?", "Quitter",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				System.exit(0);
			}

		}

		else if (e.getSource().equals(mIFImporter)) {
			// blabla ouverture d'une fenetre pour chercher le .gts
			JFileChooser dialogue = new JFileChooser(new File("ressources/GTS"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier gts", "gts");
			dialogue.setFileFilter(filter);
			File fichier=null;
			int a =dialogue.showOpenDialog(null);
			fichier = dialogue.getSelectedFile();
			if(fichier !=null && a == JFileChooser.APPROVE_OPTION){
				String name = fichier.getName();
				lienGts=fichier.getAbsolutePath();
				name = name.substring(name.length() - 4, name.length());
				int comparaison = name.compareToIgnoreCase(".gts");
				if (comparaison != 0) {
					JOptionPane.showMessageDialog(null,"Le fichier que vous avez choisi n'est pas compatible !","Attention", JOptionPane.ERROR_MESSAGE);
				}
				if (fichier != null && name.substring(name.length() - 4, name.length()).equals(".gts")) {
					if (listeOnglets.size() + 1 <= 5) {
						String nomAuteur = "Non renseign�";
						if(nbOngletsImport==0)
							nomFichier="New";
						else
							nomFichier="New("+nbOngletsImport+")";
						onglet = new Onglet(new MyDeskTopPane(fichier.getAbsolutePath()), tabbedPane, nomFichier,nomAuteur, true, listeOnglets);
						panelInfos = onglet.getPinfos();
						nbOngletsImport++;
						tabbedPane.addTab(nomFichier, onglet);
						onglet.dessineOnglet();
						tabbedPane.setSelectedComponent(onglet);
					} else {
						JOptionPane.showMessageDialog(null,"Trop d'onglets sont ouverts, fermez des onglets puis reessayer !","Attention", JOptionPane.ERROR_MESSAGE);
					}
				}
				mIFEnregistrer.setEnabled(true);
				mIFExporter.setEnabled(true);
				mIFProprietes.setEnabled(true);
			}


		}else if (e.getSource().equals(mIFExporter)){
			JFileChooser filechoose = new JFileChooser("");
			//filechoose.setCurrentDirectory(new File(File.separator+"new"));
			filechoose.setDialogTitle("Exporter la r�alisation");

			filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			String approve = new String("Enregistrer");
			int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
			if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){
				Component onglet = tabbedPane.getSelectedComponent();
				String chemin = filechoose.getSelectedFile().getAbsolutePath()+File.separator+((Onglet) onglet).getNomFichier()+".gts";
				String urlSource=((Onglet) onglet).getDp().getUrl();
				if(this.copieGTS(new File(urlSource), new File(chemin))){
					JOptionPane.showMessageDialog(null,"L'exportation de : "+urlSource+" a fonctionn� !","Exportation r�ussie", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,"L'exportation de : "+urlSource+" a �chou� !","Exportation �chou�e", JOptionPane.OK_OPTION);
				}
			}
		} else if (e.getSource().equals(mIFFermer)) {
			Component onglet = tabbedPane.getSelectedComponent();
			if(onglet instanceof Onglet){
				tabbedPane.remove(onglet);
				listeOnglets.remove(onglet);
			}
		}
	}


	public boolean copieGTS(File source, File destination){
		boolean resultat = false;
		Onglet onglet = (Onglet) tabbedPane.getSelectedComponent();
		Model mod = onglet.getDp().getPanel().getM();
		// Declaration des flux
		Scanner sourceFile= null;
		PrintWriter destinationFile=null;
		try {
			// Cr�ation du fichier :
			destination.createNewFile();
			// Ouverture des flux
			sourceFile = new Scanner(source);
			destinationFile = new PrintWriter(destination);
			// Lecture par segment de 0.5Mo
			destinationFile.println(sourceFile.nextLine());
			for(int i = 0;i<mod.getListPoint().size() + mod.getSegment().size();++i){
				destinationFile.println(sourceFile.nextLine());
			}
			for(Face f : mod.getFace()){
				destinationFile.println(f.getSegment1() + " " + f.getSegment2()+ " " + f.getSegment3() +" "+ f.getColor().getRed() + " "+f.getColor().getGreen()+" "+f.getColor().getBlue());
			}

			// Copie r�ussie
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
}
