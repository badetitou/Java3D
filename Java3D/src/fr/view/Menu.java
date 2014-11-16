package fr.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;


public class Menu extends JMenuBar implements ActionListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final	JMenu mFichier;
	private final	JMenu mEdition;
	private final	JMenu mOptions;
	private final 	JMenu mInfos;
	//Items Menu Fichier
	private final	JMenuItem	mIFImporter;
	private final	JMenuItem	mIFOuvrir;
	private final	JMenuItem	mIFRecents;
	private final	JMenuItem	mIFEnregistrer;
	private final	JMenuItem	mIFEnregistrerSous;
	private final 	JMenuItem 	mIFFermer;
	private final 	JMenuItem 	mIFImprimer;
	private final 	JMenuItem 	mIFProprietes;
	private final 	JMenuItem 	mIFQuitter;
	//Items Menu Edition
	private final 	JMenuItem 	mIEDefaire;
	private final 	JMenuItem 	mIERefaire;
	//Items Menu Options
	private final 	JMenuItem 	mIOBoiteOutils;
	private final 	JMenuItem 	mIOPreferences;
	//Items Menu Infos
	private final 	JMenuItem 	mIIaPropos;
	private final 	JMenuItem 	mIIContacts;

	private final JTabbedPane tabbedPane;

	private final String nomFichier="new";

	public Menu(JTabbedPane tabbedPane)
	{
		this.tabbedPane=tabbedPane;
		this.setPreferredSize(new Dimension((int) Window.outil.getScreenSize().getWidth(),30));

		//Cr�ation du Menu
		mFichier = new JMenu("Fichier");
		mEdition = new JMenu("Edition");
		mOptions = new JMenu("Options");
		mInfos = new JMenu("?");

		//Dimensions des boutons
		mFichier.setPreferredSize(new Dimension(60,350));
		mEdition.setPreferredSize(new Dimension(60,350));
		mOptions.setPreferredSize(new Dimension(60,350));
		mInfos.setPreferredSize(new Dimension(60,350));

		//Cr�ation des sous menus
		//Sous menu Fichier
		mIFImporter = new JMenuItem("Importer");
		mIFOuvrir = new JMenuItem("Ouvrir");
		mIFRecents = new JMenuItem("Fichiers r�cents");
		mIFEnregistrer = new JMenuItem("Enregistrer");
		mIFEnregistrerSous = new JMenuItem("Enregistrer sous");
		mIFFermer = new JMenuItem("Fermer");
		mIFImprimer = new JMenuItem("Imprimer");
		mIFProprietes = new JMenuItem("Propri�t�s");
		mIFQuitter = new JMenuItem("Quitter");

		///Action des MenuItems Fichier

		//Robin tu peux m'expliquer ceci
		mIFImporter.setMnemonic(KeyEvent.VK_H);
		mFichier.setMnemonic(KeyEvent.VK_F);
		//stp????

		mIFImporter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mIFOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		//mIFRecents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mIFEnregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mIFEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mIFFermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		mIFImprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mIFProprietes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK));
		mIFQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));


		//Sous menu Edition
		mIEDefaire = new JMenuItem("D�faire");
		mIERefaire = new JMenuItem("Refaire");

		//Action des MenuItems Edition

		mIEDefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mIERefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));

		//Sous menu Options
		mIOBoiteOutils = new JMenuItem("Bo�tes � Outils");
		mIOPreferences = new JMenuItem("Pr�f�rences");

		//Action des MenuItems Options


		//Sous menu Infos
		mIIaPropos = new JMenuItem("A propos");
		mIIContacts = new JMenuItem("Contacts");

		//action des MenuItems Infos

		mIIaPropos.setAccelerator(KeyStroke.getKeyStroke("F1"));
		mIIContacts.setAccelerator(KeyStroke.getKeyStroke("F2"));

		//Ajout de chaque sous menu � son menu
		mFichier.add(mIFImporter);
		mFichier.add(mIFOuvrir);
		mFichier.add(mIFRecents);
		mFichier.addSeparator();
		mFichier.add(mIFEnregistrer);
		mFichier.add(mIFEnregistrerSous);
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

		mIFOuvrir.addActionListener(this);
		mIFQuitter.addActionListener(this);
		mIFImporter.addActionListener(this);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mIFOuvrir)) {
			JFileChooser dialogue = new JFileChooser(new File("."));
			File fichier;
			dialogue.showOpenDialog(null);
			fichier = dialogue.getSelectedFile();
			System.out.println(fichier);
		}
		else if (e.getSource().equals(mIFQuitter)){
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION){
				System.exit(0);
			}

		}
		else if (e.getSource().equals(mIFImporter)){
			//blabla ouverture d'une fenetre pour chercher le .gts
			Onglet onglet=new Onglet(new MyDeskTopPane("ressources/image/head.gts"),Window.nbOnglets+1,this.tabbedPane,nomFichier);
			this.tabbedPane.addTab(nomFichier, onglet);
			onglet.dessineOnglet();
			Window.nbOnglets++;
		}
	}
}
