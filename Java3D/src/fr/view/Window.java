package fr.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 * 
 * @author Loic
 * Fen�tre du logiciel apr�s l'�cran du chargement. Elle contient tous les JPanel.
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame{

	Panneau panel;
	public static Toolkit outil;
	private final JPanel container;
	public static JFrame frame;
	private final JTabbedPane tabbedPane;
	public static int nbOnglets=0;

	public Window(MyDeskTopPane dp) {
		super("3D Lib");
		Window.frame=this;
		outil = getToolkit();
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());
		this.setVisible(true);
		this.setSize(outil.getScreenSize());
		this.setResizable(false);
		this.setState(Frame.NORMAL);

		//onglets
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Icosa", new Onglet(dp,0,tabbedPane,"icosa"));
		//tabbedPane.addTab("Head",new ImageIcon(new ImageIcon("ressources/icones/description.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), new Onglet(new MyDeskTopPane("ressources/image/head.gts")));

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(new Barre(),BorderLayout.NORTH);
		container.add(tabbedPane,BorderLayout.CENTER);


		this.setJMenuBar(new Menu(tabbedPane));
		this.getContentPane().add(container);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}
}
