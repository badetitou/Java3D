package fr.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import fr.model.Model;

/**
 * 
 * @author Loïc
 * Classe regroupant les 4 panels qui vont contenir les 4 vues possible de l'objet!
 *
 */

public class MyDeskTopPane extends JDesktopPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JInternalFrame iFrameMain;
	public static JInternalFrame iFrameDessus;
	public static JInternalFrame iFrameDessous;
	public static JInternalFrame iFrameProfil;
	public Model m;
	public static JPanel panel;
	public static int hauteur=800;
	public static int largeur=800;

	public MyDeskTopPane(){
		m = new Model("ressources/image/cube.gts");
		this.setPreferredSize(new Dimension(hauteur,largeur));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		panel=new Panneau(m);

		iFrameMain = new JInternalFrame(
				"Vue principale", // le titre de la fenêtre
				true,    // true si la fenêtre est retaillable
				false,     // true si la fenêtre est fermable
				true,     // true si la fenêtre est maximisable
				false);    // true si la fenêtre est iconifiable
		iFrameMain.setVisible(true); // pour rendre la fenêtre visible
		iFrameMain.setPreferredSize(new Dimension(800,800));
		iFrameMain.setLocation(0,0);
		//iFrameMain.setPreferredSize(new Dimension(hauteur,largeur));

		iFrameDessus = new JInternalFrame("Vue du Dessus", true,    false,     true,    false);
		iFrameDessus.setVisible(false);
		//iFrameDessus.setBounds(0, 0, 300, 300);
		iFrameDessus.setPreferredSize(new Dimension(400,400));

		iFrameDessous = new JInternalFrame("Vue du Dessous", true,    false,     true,    false);
		iFrameDessous.setVisible(false);
		//iFrameDessous.setBounds(0, 0, 300, 300);
		iFrameDessous.setPreferredSize(new Dimension(400,400));

		iFrameProfil = new JInternalFrame("Vue de profil", true,    false,     true,    false);
		iFrameProfil.setVisible(false);
		//iFrameProfil.setBounds(0, 0, 300, 300);
		iFrameProfil.setPreferredSize(new Dimension(400,400));

		iFrameMain.add(panel);
		iFrameDessus.add(new Panneau(m));
		iFrameDessous.add(new Panneau(m));
		iFrameProfil.add(new Panneau(m));

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(iFrameMain, gbc);

		gbc.gridx = 1;
		this.add(iFrameDessus, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(iFrameDessous, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(iFrameProfil, gbc);
		/*
		this.add(iFrameMain);
		this.add(iFrameDessus);
		this.add(iFrameDessous);
		this.add(iFrameProfil);
		 */
	}
}
