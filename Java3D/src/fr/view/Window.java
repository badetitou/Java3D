package fr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	public JPanel container;
	public static JFrame frame;
	public JDesktopPane dp;


	public Window() {
		super("3D Lib");
		Window.frame=this;
		outil = getToolkit();
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());


		dp = new MyDeskTopPane();

		JPanel jp=new JPanel();
		jp.setLayout(new GridLayout(1,1));
		jp.add(dp);

		JPanel jp2=new JPanel();
		jp2.add(new BarreVerticale());
		jp2.setBackground(new Color(190,190,190));

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(jp,BorderLayout.CENTER);
		container.add(new Barre(),BorderLayout.NORTH);
		container.add(jp2,BorderLayout.WEST);

		this.setJMenuBar(new Menu());
		this.getContentPane().add(container);
		this.setSize(outil.getScreenSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

		Panneau.d = MyDeskTopPane.panel.getSize(); // NE PAS TOUCHER CECI PERMET L'AFFICHAGE CENTRE
	}

}
