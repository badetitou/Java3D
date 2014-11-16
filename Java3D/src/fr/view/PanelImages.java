package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.model.OutilsBdd;

public class PanelImages extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int columns=25;
	private final JPanel galerie;
	private final ArrayList<JLabel> images;
	private String url="";
	private final JButton ajouterImage;
	private final JButton supprimerImage;
	public static boolean boutonAjouterImage;
	public static boolean boutonSupprimerImage;
	private final OutilsBdd obdd;
	public PanelImages(String nomFichier){
		this.setLayout(new FlowLayout(0,20,20));
		this.setBackground(new Color(215,215,215));
		obdd=new OutilsBdd("Database.db");
		galerie=new JPanel();
		galerie.setLayout(new GridLayout((columns/7)+1,7,8,8));
		images = new ArrayList<JLabel>();
		JLabel label;
		for (int i=0;i< columns;i++){
			label=new JLabel();
			url=obdd.getLinkImg(nomFichier);
			label.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(Window.outil.getScreenSize().width/10, Window.outil.getScreenSize().width/10, Image.SCALE_DEFAULT)));
			images.add(label);
			galerie.add(images.get(i));
		}
		JScrollPane scroll = new JScrollPane(galerie);
		scroll.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-500,Window.outil.getScreenSize().height/6));

		ajouterImage=new JButton("Ajouter une image");
		supprimerImage=new JButton("Supprimer une image");

		JPanel pBoutons =new JPanel();
		pBoutons.setLayout(new GridLayout(2,1,0,50));
		pBoutons.add(ajouterImage);
		pBoutons.add(supprimerImage);
		pBoutons.setBackground(new Color(215,215,215));

		ajouterImage.addMouseListener(this);

		this.add(scroll);
		this.add(pBoutons);

	}
	public void mouseClicked(MouseEvent e) {
		JFileChooser dialogue = new JFileChooser(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Image","png","jpg","jpeg");
		dialogue.setFileFilter(filter);
		final File fichier;
		dialogue.showOpenDialog(null);
		fichier = dialogue.getSelectedFile();
		String name = fichier.getName();
		name=name.substring(name.length()-4, name.length());
		if(name.compareToIgnoreCase(".png")!=0 || name.compareToIgnoreCase(".jpg")!=0){
			JOptionPane.showMessageDialog(null, "Le fichier que vous avez choisi n'est pas compatible !\nLes formats supportés sont le JPEG et le PNG.", "Attention", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
