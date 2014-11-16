package fr.view;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;


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
	private Onglet onglet;

	private final ArrayList<Onglet>listeOnglets;

	public Menu(JTabbedPane tabbedPane,ArrayList<Onglet> listeOnglets)
	{
		this.tabbedPane=tabbedPane;
		this.listeOnglets=listeOnglets;
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
		mIFImporter = new JMenuItem("Importer");
		mIFOuvrir = new JMenuItem("Ouvrir");
		mIFRecents = new JMenuItem("Fichiers récents");
		mIFEnregistrer = new JMenuItem("Enregistrer");
		mIFEnregistrerSous = new JMenuItem("Enregistrer sous");
		mIFFermer = new JMenuItem("Fermer");
		mIFImprimer = new JMenuItem("Imprimer");
		mIFProprietes = new JMenuItem("Propriétés");
		mIFQuitter = new JMenuItem("Quitter");

		///Action des MenuItems Fichier


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
		mIEDefaire = new JMenuItem("Défaire");
		mIERefaire = new JMenuItem("Refaire");

		//Action des MenuItems Edition

		mIEDefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mIERefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));

		//Sous menu Options
		mIOBoiteOutils = new JMenuItem("Boîtes à Outils");
		mIOPreferences = new JMenuItem("Préférences");

		//Action des MenuItems Options


		//Sous menu Infos
		mIIaPropos = new JMenuItem("A propos");
		mIIContacts = new JMenuItem("Contacts");

		//action des MenuItems Infos

		mIIaPropos.setAccelerator(KeyStroke.getKeyStroke("F1"));
		mIIContacts.setAccelerator(KeyStroke.getKeyStroke("F2"));

		//Ajout de chaque sous menu à son menu
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
			WindowOuvrir windowO = new WindowOuvrir(tabbedPane, listeOnglets);

		}
		else if (e.getSource().equals(mIFQuitter)){
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION){
				System.exit(0);
			}

		}
		else if (e.getSource().equals(mIFImporter)){
			//blabla ouverture d'une fenetre pour chercher le .gts
			JFileChooser dialogue = new JFileChooser(new File("ressources/image"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier gts","gts");
			dialogue.setFileFilter(filter);
			final File fichier;
			dialogue.showOpenDialog(null);
			fichier = dialogue.getSelectedFile();
			String name = fichier.getName();
			name=name.substring(name.length()-4, name.length());
			int comparaison = name.compareToIgnoreCase(".gts");
			if(comparaison!=0){
				JOptionPane.showMessageDialog(null, "Le fichier que vous avez choisi n'est pas compatible !", "Attention", JOptionPane.ERROR_MESSAGE);
			}
			if(fichier!=null && name.substring(name.length()-4, name.length()).equals(".gts")){
				final Thread t = new Thread() {
					@Override
					public void run() {
						onglet=new Onglet(new MyDeskTopPane(fichier.getAbsolutePath()),tabbedPane,nomFichier,listeOnglets);
					}
				};
				Thread t2=new Thread(){
					@Override
					public void run() {
						while(t.isAlive()){
							try {
								this.sleep(10);
							} catch (InterruptedException e) {}
						}
						System.out.println("cc3");
						try {
							this.sleep(50);
						} catch (InterruptedException e) {}
						BufferedImage screen=null;
						try {
							screen = new Robot().createScreenCapture(new Rectangle((int)Window.outil.getScreenSize().getWidth()/3-35,(int)Window.outil.getScreenSize().getHeight()/5-2,(int)MyDeskTopPane.dimension.getWidth()-35,(int)MyDeskTopPane.dimension.getHeight()-35));
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						tabbedPane.addTab(nomFichier, onglet);
						onglet.dessineOnglet();
						Window.nbOnglets++;
					}
				};
				t.start();
				t2.start();
			}
		}
	}
}
