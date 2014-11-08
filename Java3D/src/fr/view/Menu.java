package fr.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.model.Model;


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
	private final	JMenuItem	mIFNouveau;
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

	public Menu()
	{
		this.setPreferredSize(new Dimension((int) Window.outil.getScreenSize().getWidth(),30));

		//Création du Menu
		mFichier = new JMenu("Fichier");
		mEdition = new JMenu("Edition");
		mOptions = new JMenu("Options");
		mInfos = new JMenu("?");
		
		//Dimensions des boutons
		mFichier.setPreferredSize(new Dimension(60,350));
		mEdition.setPreferredSize(new Dimension(60,350));
		mOptions.setPreferredSize(new Dimension(60,350));
		mInfos.setPreferredSize(new Dimension(60,350));
		
		//Création des sous menus
		//Sous menu Fichier
		mIFNouveau = new JMenuItem("Nouveau");
		mIFOuvrir = new JMenuItem("Ouvrir");
		mIFRecents = new JMenuItem("Fichiers récents");
		mIFEnregistrer = new JMenuItem("Enregistrer");
		mIFEnregistrerSous = new JMenuItem("Enregistrer sous");
		mIFFermer = new JMenuItem("Fermer");
		mIFImprimer = new JMenuItem("Imprimer");
		mIFProprietes = new JMenuItem("Propriétés");
		mIFQuitter = new JMenuItem("Quitter");
		
		///à définir, étudier, Robin?
		mIFNouveau.setMnemonic(KeyEvent.VK_H);
		mFichier.setMnemonic(KeyEvent.VK_F);

		//Sous menu Edition
		mIEDefaire = new JMenuItem("Défaire");
		mIERefaire = new JMenuItem("Refaire");

		//Sous menu Options
		mIOBoiteOutils = new JMenuItem("Boîtes à Outils");
		mIOPreferences = new JMenuItem("Préférences");
		//Sous menu Infos
		mIIaPropos = new JMenuItem("A propos");
		mIIContacts = new JMenuItem("Contacts");
		
		//Ajout de chaque sous menu à son menu
		mFichier.add(mIFNouveau);
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

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mIFOuvrir)) {
			JFileChooser dialogue = new JFileChooser(new File("."));
			File fichier;
			dialogue.showOpenDialog(null);
			fichier = dialogue.getSelectedFile();
			System.out.println(fichier);
		}
	}
}
