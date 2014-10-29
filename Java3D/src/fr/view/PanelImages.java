package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelImages extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int columns=55;
	private final JPanel galerie;
	private final ArrayList<JLabel> images;
	private String url="";
	private final JButton ajouterImage;
	private final JButton supprimerImage;
	public static boolean boutonAjouterImage;
	public static boolean boutonSupprimerImage;
	public PanelImages(){
		this.setLayout(new FlowLayout(0,20,20));
		this.setBackground(new Color(215,215,215));
		galerie=new JPanel();
		galerie.setLayout(new GridLayout((columns/7)+1,7,10,10));
		images = new ArrayList<JLabel>();
		JLabel label;
		for (int i=0;i< columns;i++){
			label=new JLabel();
			url="ressources/icones/iconerota.png";
			label.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(Window.outil.getScreenSize().width/10, Window.outil.getScreenSize().width/10, Image.SCALE_DEFAULT)));
			images.add(label);
			galerie.add(images.get(i));
		}
		JScrollPane scroll = new JScrollPane(galerie);
		scroll.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-500,Window.outil.getScreenSize().height/5));

		ajouterImage=new JButton("Ajouter une image");
		supprimerImage=new JButton("Supprimer une image");

		JPanel pBoutons =new JPanel();
		pBoutons.setLayout(new GridLayout(2,1,0,50));
		pBoutons.add(ajouterImage);
		pBoutons.add(supprimerImage);
		pBoutons.setBackground(new Color(215,215,215));

		this.add(scroll);
		this.add(pBoutons);

	}

}
