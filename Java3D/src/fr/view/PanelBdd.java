package fr.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelBdd extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel description;
	private final JPanel informations;
	private final JPanel images;
	private final JPanel realisations;
	public PanelBdd(){
		this.setLayout(new CardLayout());
		Toolkit tk=getToolkit();
		this.setPreferredSize(new Dimension(tk.getScreenSize().height,tk.getScreenSize().width/6));
		description=new PanelDescription();
		informations = new PanelInformations();
		images=new PanelImages();
		realisations=new PanelRealisations();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Description",new ImageIcon(new ImageIcon("ressources/icones/description.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), description);
		tabbedPane.addTab("Informations",new ImageIcon(new ImageIcon("ressources/icones/informations.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), informations);
		tabbedPane.addTab("Images/photos",new ImageIcon(new ImageIcon("ressources/icones/galerie.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), images);
		tabbedPane.addTab("Réalisations",new ImageIcon(new ImageIcon("ressources/icones/realisations.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), realisations);
		this.add(tabbedPane);
	}
}
