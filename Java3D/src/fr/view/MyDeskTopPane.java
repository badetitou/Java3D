package fr.view;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import fr.model.Model;

public class MyDeskTopPane extends JDesktopPane{

	public JInternalFrame iFrameMain;
	public JInternalFrame iFrameDessus;
	public JInternalFrame iFrameDessous;
	public JInternalFrame iFrameProfil;
	public Model m;
	public static JPanel panel;

	public MyDeskTopPane(){
		m = new Model("ressources/image/x_wing.gts");
		panel=new Panneau(m);

		iFrameMain = new JInternalFrame(
				"Vue principale", // le titre de la fen�tre
				true,    // true si la fen�tre est retaillable
				true,     // true si la fen�tre est fermable
				true,     // true si la fen�tre est maximisable
				false);    // true si la fen�tre est iconifiable
		iFrameMain.setVisible(true); // pour rendre la fen�tre visible
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
		this.add(iFrameMain);

	}
}
