package fr.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import fr.model.OutilsBdd;


/**
 * 
 * @author Loic
 * Fen�tre du logiciel apr�s l'�cran du chargement. Elle contient tous les JPanel.
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements ComponentListener,MouseListener{
	// JOYEUX NOEL :D
	Panneau panel;
	public static Toolkit outil;
	private final JPanel container;
	public static JFrame frame;
	private final JTabbedPane tabbedPane;
	private final ArrayList<Object> listeOnglets;
	private final OngletMenu menu;
	public Window(ArrayList<MyDeskTopPane> listeFichiersRecents) {
		super("3D Lib");
		outil = getToolkit();
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());

		OutilsBdd obdd=new OutilsBdd("Database.db");

		//precharger fichier recent

		//onglets
		listeOnglets=new ArrayList<Object>();
		tabbedPane = new JTabbedPane();
		//String objet=obdd.getLastFiles();
		//Onglet onglet=new Onglet(dp,tabbedPane,objet,obdd.getAuthor(objet),false,listeOnglets);
		//PanelInformations panelInfos = onglet.getPinfos();
		menu=new OngletMenu(tabbedPane,listeOnglets);
		tabbedPane.addTab("Menu", menu);
		tabbedPane.addMouseListener(this);
		menu.dessineOnglet();
		//tabbedPane.addTab("Onglet",onglet);
		//onglet.dessineOnglet();

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(new Barre(),BorderLayout.NORTH);
		container.add(tabbedPane,BorderLayout.CENTER);
		addComponentListener(this);

		this.setJMenuBar(new Menu(tabbedPane,listeOnglets,listeFichiersRecents));
		this.getContentPane().add(container);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(this.getSize().width,this.getSize().height));
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent e) {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i< listeOnglets.size();++i){
			try{
				((Onglet)listeOnglets.get(i)).getDp().getPanel().setVisibilitePopMenuToFalse();
			} catch (Exception e1){
				e1.printStackTrace();
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
