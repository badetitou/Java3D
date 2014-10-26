package fr.view;

import java.awt.Dimension;
import java.awt.GridLayout;

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
	public JInternalFrame iFrameMain;
	public JInternalFrame iFrameDessus;
	public JInternalFrame iFrameDessous;
	public JInternalFrame iFrameProfil;
	public Model m;
	public static JPanel panel;
	public static int hauteur=800;
	public static int largeur=800;

	public MyDeskTopPane(){
		m = new Model("ressources/image/cube.gts");
		this.setPreferredSize(new Dimension(hauteur,largeur));
		this.setLayout(new GridLayout(2,2));
		panel=new Panneau(m);

		iFrameMain = new JInternalFrame(
				"Vue principale", // le titre de la fenêtre
				true,    // true si la fenêtre est retaillable
				false,     // true si la fenêtre est fermable
				true,     // true si la fenêtre est maximisable
				false);    // true si la fenêtre est iconifiable
		iFrameMain.setVisible(true); // pour rendre la fenêtre visible
		iFrameMain.setBounds(0, 0, hauteur, largeur);

		iFrameDessus = new JInternalFrame("Vue du Dessus", false,    true,     true,    false);
		iFrameDessus.setVisible(false);
		iFrameDessus.setBounds(0, 0, 300, 300);

		iFrameDessous = new JInternalFrame("Vue du Dessous", false,    true,     true,    false);
		iFrameDessous.setVisible(false);
		iFrameDessous.setBounds(0, 0, 300, 300);

		iFrameProfil = new JInternalFrame("Vue de profil", false,    true,     true,    false);
		iFrameProfil.setVisible(false);
		iFrameProfil.setBounds(0, 0, 300, 300);

		iFrameMain.add(panel);
		//iFrameDessus.add(panel);
		//iFrameDessous.add(panel);
		//iFrameProfil.add(panel);
		this.add(iFrameMain);
		this.add(iFrameDessus);
		this.add(iFrameDessous);
		this.add(iFrameProfil);

	}
}
