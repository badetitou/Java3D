package fr.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import fr.model.Face;
import fr.model.Model;

/**
 * 
 * @author Lo�c Classe regroupant les 4 panels qui vont contenir les 4 vues
 *         possible de l'objet!
 *
 */

public class MyDeskTopPane extends JDesktopPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JInternalFrame iFrameMain;
	private final JInternalFrame iFrameDessus;
	private final JInternalFrame iFrameDessous;
	private final JInternalFrame iFrameProfil;
	private final Panneau panel;
	private BarreVerticale bv;
	private final String url;

	public String getUrl() {
		return url;
	}

	public void setBV(BarreVerticale bv){
		this.bv = bv;
	}

	public BarreVerticale getBarreVerticale(){
		return bv;
	}

	public JInternalFrame getiFrameMain() {
		return iFrameMain;
	}

	public JInternalFrame getiFrameDessus() {
		return iFrameDessus;
	}

	public JInternalFrame getiFrameDessous() {
		return iFrameDessous;
	}

	public JInternalFrame getiFrameProfil() {
		return iFrameProfil;
	}

	public Panneau getPanel() {
		return panel;
	}

	public List<Face> getListeFaceSelectionne(){
		return this.panel.getAllSelectedFace();
	}

	public static Dimension dimension;
	public static Dimension dimmini;

	public MyDeskTopPane(String url) {
		this.url=url;
		Toolkit tk = getToolkit();
		dimension = new Dimension(tk.getScreenSize().width / 2-50,tk.getScreenSize().height / 2-50);
		dimmini = new Dimension(dimension.height / 2, dimension.height / 2);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		panel = new Panneau(new Model(url, 0, dimension), this);

		iFrameMain = new JInternalFrame("Vue principale", // le titre de la
				// fen�tre
				true, // true si la fen�tre est retaillable
				false, // true si la fen�tre est fermable
				true, // true si la fen�tre est maximisable
				false); // true si la fen�tre est iconifiable
		iFrameMain.setVisible(true); // pour rendre la fen�tre visible
		iFrameMain.setPreferredSize(dimension);
		// iFrameMain.setPreferredSize(new Dimension(hauteur,largeur));

		iFrameDessus = new JInternalFrame("Vue du Dessus", true, false, true,
				false);
		iFrameDessus.setVisible(false);
		// iFrameDessus.setBounds(0, 0, 300, 300);
		iFrameDessus.setPreferredSize(dimmini);

		iFrameDessous = new JInternalFrame("Vue du Dessous", true, false, true,
				false);
		iFrameDessous.setVisible(false);
		// iFrameDessous.setBounds(0, 0, 300, 300);
		iFrameDessous.setPreferredSize(dimmini);

		iFrameProfil = new JInternalFrame("Vue de profil", true, false, true,
				false);
		iFrameProfil.setVisible(false);
		// iFrameProfil.setBounds(0, 0, 300, 300);
		iFrameProfil.setPreferredSize(dimmini);

		iFrameMain.add(panel);
		iFrameDessus.add(new Panneau(new Model(url, 1, dimmini), this));
		iFrameDessous.add(new Panneau(new Model(url, 2, dimmini), this));
		iFrameProfil.add(new Panneau(new Model(url, 3, dimmini), this));

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
		 * try { screen = new Robot().createScreenCapture(new
		 * Rectangle(150,150,(
		 * int)dimension.getWidth(),(int)dimension.getHeight())); } catch
		 * (AWTException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } try { ImageIO.write(screen, "png", new
		 * File("ressources/screenshot.png")); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public Model getModel() {
		return this.panel.getM();
	}
}
