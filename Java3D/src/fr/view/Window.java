package fr.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import fr.model.OutilsBdd;


/**
 * 
 * @author Loic
 * Fenêtre du logiciel après l'écran du chargement. Elle contient tous les JPanel.
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame{

	Panneau panel;
	public static Toolkit outil;
	private final JPanel container;
	public static JFrame frame;
	private final JTabbedPane tabbedPane;
	private final ArrayList<Onglet> listeOnglets;

	public Window(MyDeskTopPane dp) {
		super("3D Lib");
		Window.frame=this;
		outil = getToolkit();
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setSize(outil.getScreenSize());
		this.setResizable(false);
		this.setState(Frame.NORMAL);
		OutilsBdd obdd=new OutilsBdd("Database.db");

		//onglets
		listeOnglets=new ArrayList<Onglet>();
		tabbedPane = new JTabbedPane();
		String objet=obdd.getLastFiles();
		Onglet onglet=new Onglet(dp,tabbedPane,objet,obdd.getAuthor(objet),false,listeOnglets);
		PanelInformations panelInfos = onglet.getPinfos();
		tabbedPane.addTab("Icosa", onglet);
		onglet.dessineOnglet();

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(new Barre(),BorderLayout.NORTH);
		container.add(tabbedPane,BorderLayout.CENTER);


		this.setJMenuBar(new Menu(tabbedPane,listeOnglets, panelInfos));
		this.getContentPane().add(container);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
	}
}
