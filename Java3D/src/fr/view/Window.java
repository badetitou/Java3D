package fr.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
public class Window extends JFrame implements ComponentListener{

	Panneau panel;
	public static Toolkit outil;
	private final JPanel container;
	public static JFrame frame;
	private final JTabbedPane tabbedPane;
	private final ArrayList<Onglet> listeOnglets;


	public Window(MyDeskTopPane dp) {
		super("3D Lib");
		this.setVisible(true);
		Window.frame=this;
		outil = getToolkit();
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(this.getSize().width,this.getSize().height));
		//this.setResizable(false);
		//this.setState(JFrame.MAXIMIZED_BOTH);
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
		addComponentListener(this);

		//this.setMinimumSize(new Dimension((int)outil.getScreenSize().getWidth()/2,(int)outil.getScreenSize().getHeight()/2));
		this.setJMenuBar(new Menu(tabbedPane,listeOnglets, panelInfos));
		this.getContentPane().add(container);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
	}

	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentResized(ComponentEvent e) {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}
}
