package fr.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.model.Model;

/**
 * 
 * @author Loic
 * Fenêtre du logiciel après l'écran du chargement. Elle contient tous les JPanel.
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame{

	Panneau panel;
	public Model m;
	public static Toolkit outil;
	JPanel container;

	public Window() {
		super("3D Lib");
		outil = getToolkit();
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());
		m = new Model("ressources/image/tie.gts");
		container = new JPanel();
		container.setLayout(new BorderLayout());
		panel=new Panneau(m);
		container.add(panel,BorderLayout.CENTER);
		this.setJMenuBar(new Menu());
		this.getContentPane().add(container);
		this.setSize(outil.getScreenSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400,400));
		this.setMaximumSize(new Dimension(400,400));
		this.setResizable(false);
		this.setVisible(true);
		Panneau.d = panel.getSize(); // NE PAS TOUCHER CECI PERMET L'AFFICHAGE CENTRE
	}

}
