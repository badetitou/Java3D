package fr.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
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
	private final JMenuItem mIFRecents;
	private final JMenuItem mIFEnregistrer;
	// private final JMenuItem mIFEnregistrerSous;
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

	private final JTabbedPane tabbedPane;

	private static int nbOngletsImport=0;

	private String nomFichier;
	private Onglet onglet;

	private final ArrayList<Onglet> listeOnglets;

	public Menu(JTabbedPane tabbedPane, ArrayList<Onglet> listeOnglets, PanelInformations panelInfos) {
		this.tabbedPane = tabbedPane;
		this.listeOnglets = listeOnglets;
		this.panelInfos = panelInfos;
		this.setPreferredSize(new Dimension((int) Window.outil.getScreenSize().getWidth(), 30));

		// Création du Menu
		mFichier = new JMenu("Fichier");
		mEdition = new JMenu("Edition");
		mOptions = new JMenu("Options");
		mInfos = new JMenu("?");

		// Dimensions des boutons
		mFichier.setPreferredSize(new Dimension(60, 350));
		mEdition.setPreferredSize(new Dimension(60, 350));
		mOptions.setPreferredSize(new Dimension(60, 350));
		mInfos.setPreferredSize(new Dimension(60, 350));

		// Création des sous menus
		// Sous menu Fichier
		mIFImporter = new JMenuItem("Importer");
		mIFOuvrir = new JMenuItem("Ouvrir");
		mIFRecents = new JMenuItem("Fichiers récents");
		mIFEnregistrer = new JMenuItem("Enregistrer dans la BDD");
		// mIFEnregistrerSous = new JMenuItem("Enregistrer sous");
		mIFFermer = new JMenuItem("Fermer");
		mIFImprimer = new JMenuItem("Imprimer");
		mIFProprietes = new JMenuItem("Propriétés");
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
		mIFFermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				InputEvent.CTRL_MASK));
		mIFImprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_MASK));
		mIFProprietes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,
				InputEvent.CTRL_MASK));
		mIFQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));

		// Sous menu Edition
		mIEDefaire = new JMenuItem("Défaire");
		mIERefaire = new JMenuItem("Refaire");

		// Action des MenuItems Edition

		mIEDefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				InputEvent.CTRL_MASK));
		mIERefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				InputEvent.CTRL_MASK));

		// Sous menu Options
		mIOBoiteOutils = new JMenuItem("Boîtes à Outils");
		mIOPreferences = new JMenuItem("Préférences");

		// Action des MenuItems Options

		// Sous menu Infos
		mIIaPropos = new JMenuItem("A propos");
		mIIContacts = new JMenuItem("Contacts");

		// action des MenuItems Infos

		mIIaPropos.setAccelerator(KeyStroke.getKeyStroke("F1"));
		mIIContacts.setAccelerator(KeyStroke.getKeyStroke("F2"));

		// Ajout de chaque sous menu à son menu
		mFichier.add(mIFImporter);
		mFichier.add(mIFOuvrir);
		mFichier.add(mIFRecents);
		mFichier.addSeparator();
		mFichier.add(mIFEnregistrer);
		// mFichier.add(mIFEnregistrerSous);
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
		mIFEnregistrer.addActionListener(this);
		mIFQuitter.addActionListener(this);
		mIFImporter.addActionListener(this);
		mIFFermer.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mIFOuvrir)) {
			WindowOuvrir windowO = new WindowOuvrir(tabbedPane, listeOnglets);
			panelInfos = windowO.getPanelInfos();
		}
		else if (e.getSource().equals(mIFEnregistrer)) {
			Component onglet = tabbedPane.getSelectedComponent();
			WindowEnregistrer windowE = new WindowEnregistrer(tabbedPane,listeOnglets, ((Onglet) onglet).getPinfos());
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
			JFileChooser dialogue = new JFileChooser(new File("ressources/image"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier gts", "gts");
			dialogue.setFileFilter(filter);
			File fichier=null;
			int a =dialogue.showOpenDialog(null);
			fichier = dialogue.getSelectedFile();
			if(fichier !=null && a == JFileChooser.APPROVE_OPTION){
				String name = fichier.getName();
				name = name.substring(name.length() - 4, name.length());
				int comparaison = name.compareToIgnoreCase(".gts");
				if (comparaison != 0) {
					JOptionPane.showMessageDialog(null,"Le fichier que vous avez choisi n'est pas compatible !","Attention", JOptionPane.ERROR_MESSAGE);
				}
				if (fichier != null && name.substring(name.length() - 4, name.length()).equals(".gts")) {
					if (listeOnglets.size() + 1 <= 5) {
						/*JTextField j1 = new JTextField();
					JTextField j2 = new JTextField();
					ArrayList list = new ArrayList();
					list.add("Nom auteur : \n");
					list.add(j1);
					list.add("Nom objet : \n");
					list.add(j2);
					int res = JOptionPane.showOptionDialog(null, list.toArray(), "Saisissez les champs", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, null, null);
					if (j2.getText().isEmpty() || j1.getText().isEmpty())
						list.add("les champs sont obligatoires");
					while ((j2.getText().isEmpty() || j1.getText().isEmpty()) && res!=-1) {
						res=JOptionPane.showOptionDialog(null, list.toArray(), "Saisissez les champs", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, null, null);
					}
					if(res!=-1){*/
						String nomAuteur = "Non renseigné";
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
						//}
					} else {
						JOptionPane.showMessageDialog(null,"Trop d'onglets sont ouverts, fermez des onglets puis reessayer !","Attention", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else if (e.getSource().equals(mIFFermer)) {
			Component onglet = tabbedPane.getSelectedComponent();
			tabbedPane.remove(onglet);
			listeOnglets.remove(onglet);
		}
	}
}
