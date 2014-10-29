package fr.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelBdd extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel description;
	JPanel informations;
	JPanel images;
	JPanel realisations;
	public PanelBdd(){
		this.setLayout(new CardLayout());
		Toolkit tk=getToolkit();
		this.setPreferredSize(new Dimension(tk.getScreenSize().height,tk.getScreenSize().width/6));
		description=new PanelDescription();
		informations = new PanelInformations();
		images=new PanelImages();
		realisations=new PanelRealisations();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Description", description);
		tabbedPane.addTab("Informations", informations);
		tabbedPane.addTab("Images/photos", images);
		tabbedPane.addTab("Réalisations", realisations);
		this.add(tabbedPane);
	}
}
