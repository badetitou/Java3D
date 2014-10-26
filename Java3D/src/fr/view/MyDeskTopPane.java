package fr.view;

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

	public MyDeskTopPane(){
		m = new Model("ressources/image/tie.gts");
		this.setLayout(new GridLayout(2,2));
		panel=new Panneau(m);

		iFrameMain = new JInternalFrame(
				"Vue principale", // le titre de la fenêtre
				true,    // true si la fenêtre est retaillable
				true,     // true si la fenêtre est fermable
				true,     // true si la fenêtre est maximisable
				false);    // true si la fenêtre est iconifiable
		iFrameMain.setVisible(true); // pour rendre la fenêtre visible
		iFrameMain.setBounds(0, 0, 300, 300);

		iFrameDessus = new JInternalFrame("Vue du Dessus", true,    true,     true,    false);
		iFrameDessus.setVisible(true);
		iFrameDessus.setBounds(0, 0, 300, 300);

		iFrameDessous = new JInternalFrame("Vue du Dessous", true,    true,     true,    false);
		iFrameDessous.setVisible(true);
		iFrameDessous.setBounds(0, 0, 300, 300);

		iFrameProfil = new JInternalFrame("Vue de profil", true,    true,     true,    false);
		iFrameProfil.setVisible(true);
		iFrameProfil.setBounds(0, 0, 300, 300);

		iFrameMain.add(panel);
		iFrameDessus.add(panel);
		iFrameDessous.add(panel);
		iFrameProfil.add(panel);
		this.add(iFrameMain);
		this.add(iFrameDessus);
		this.add(iFrameDessous);
		this.add(iFrameProfil);

	}
}
