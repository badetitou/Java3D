package fr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import fr.model.Connexion;

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
	public MyDeskTopPane dp;
	private final Connexion co;
	public JTabbedPane tabbedPane;

	public Window(MyDeskTopPane dp) {
		super("3D Lib");
		Window.frame=this;
		outil = getToolkit();
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());
		this.setVisible(true);
		this.setSize(outil.getScreenSize());
		this.setResizable(false);
		this.setState(Frame.NORMAL);


		//Connection bdd
		co = new Connexion("Database.db");
		co.connect();
		co.close();


		this.dp=dp;
		//onglets
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Icosa",new ImageIcon(new ImageIcon("ressources/icones/description.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), new Onglet(dp));
		tabbedPane.addTab("Head",new ImageIcon(new ImageIcon("ressources/icones/description.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), new Onglet(new MyDeskTopPane("ressources/image/head.gts")));
		JPanel jp2=new JPanel();
		jp2.add(new BarreVerticale(this.dp));
		jp2.setBackground(new Color(190,190,190));

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(new Barre(),BorderLayout.NORTH);
		container.add(tabbedPane,BorderLayout.CENTER);



		this.setJMenuBar(new Menu());
		this.getContentPane().add(container);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

}
