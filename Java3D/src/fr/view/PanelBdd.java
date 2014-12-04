package fr.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelBdd extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final PanelDescription description;
	private final PanelInformations informations;
	private final JTabbedPane tabbedPane;
	public PanelDescription getDescription() {
		return description;
	}
	private final ArrayList<String>listeImages;

	public PanelInformations getInformations() {
		return informations;
	}
	private final int nbImages;
	public int getNbImages() {
		return nbImages;
	}
	public PanelDescription getPanelDescription(){
		return this.description;
	}
	private PanelImages images;
	public PanelImages getImages() {
		return images;
	}
	private final JPanel realisations;
	public PanelBdd(String nomFichier,String nomAuteur,boolean nouveau){
		this.setLayout(new CardLayout());
		Toolkit tk=getToolkit();
		this.setPreferredSize(new Dimension(tk.getScreenSize().height,tk.getScreenSize().width/6));
		description=new PanelDescription(nomFichier,nouveau);
		informations = new PanelInformations(nomFichier,nomAuteur,nouveau);
		images=new PanelImages(nomFichier,nouveau);
		listeImages=images.getListeImages();
		nbImages=images.getNbImages();
		realisations=new PanelRealisations(nomFichier);
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Description",new ImageIcon(new ImageIcon("ressources/icones/description.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), description);
		tabbedPane.addTab("Informations",new ImageIcon(new ImageIcon("ressources/icones/informations.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), informations);
		tabbedPane.addTab("Images/photos",new ImageIcon(new ImageIcon("ressources/icones/galerie.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), images);
		tabbedPane.addTab("R�alisations",new ImageIcon(new ImageIcon("ressources/icones/realisations.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), realisations);
		this.add(tabbedPane);
	}
	public ArrayList<String> getListeImages() {
		return listeImages;
	}
}
