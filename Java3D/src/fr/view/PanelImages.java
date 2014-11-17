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
	private final JPanel galerie;
	private final ArrayList<JLabel> images;
	private final JButton ajouterImage;
	private final JButton supprimerImage;
	public static boolean boutonAjouterImage;
	public static boolean boutonSupprimerImage;
	private final OutilsBdd obdd;
	private final String lien;
	private ArrayList<String>listeImages;
	public PanelImages(String nomFichier){
		this.setLayout(new FlowLayout(0,20,20));
		this.setBackground(new Color(215,215,215));
		obdd=new OutilsBdd("Database.db");
		galerie=new JPanel();
		galerie.setLayout(new GridLayout(8,7,8,8));
		images = new ArrayList<JLabel>();
		lien=obdd.getLinkImg(nomFichier);
		listeImages=listerRepertoire(lien);
		if(listeImages!=null){
			JLabel label;
			for (int i=0;i< listeImages.size();i++){
				label=new JLabel();
				label.setIcon(new ImageIcon(new ImageIcon(listeImages.get(i)).getImage().getScaledInstance(Window.outil.getScreenSize().width/10, Window.outil.getScreenSize().width/10, Image.SCALE_DEFAULT)));
				images.add(label);
				galerie.add(images.get(i));
			}
		}
		else {
			listeImages=new ArrayList<String>();
		}
		JScrollPane scroll = new JScrollPane(galerie);
		scroll.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-(Window.outil.getScreenSize().width/3),Window.outil.getScreenSize().height/6));

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


	public ArrayList<String> listerRepertoire(String lien){
		File repertoire=new File(lien);
		String [] listefichiers;
		ArrayList<String> listeImages=new ArrayList<String>();
		int i;
		if(repertoire.list()!=null){
			listefichiers=repertoire.list();
			for(i=0;i<listefichiers.length;i++){
				if(listefichiers[i].endsWith(".jpg") || listefichiers[i].endsWith(".jpeg") || listefichiers[i].endsWith(".png")){
					listeImages.add(listefichiers[i]);
				}
			}
			return listeImages;
		}
		return null;
	}

	public void dessinerImages(String path){
		JLabel l=new JLabel();
		l.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(Window.outil.getScreenSize().width/10, Window.outil.getScreenSize().width/10, Image.SCALE_DEFAULT)));
		images.add(l);
		galerie.add(images.get(images.size()-1));
		this.revalidate();
	}

	public void mouseClicked(MouseEvent e) {
		JFileChooser dialogue = new JFileChooser(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Image","png","jpg","jpeg");
		dialogue.setFileFilter(filter);
		final File fichier;
		dialogue.showOpenDialog(null);
		fichier = dialogue.getSelectedFile();
		if(fichier!=null){
			String name = fichier.getName();
			name=name.substring(name.length()-4, name.length());
			if(name.compareTo(".png")!=0 && name.compareTo(".jpg")!=0 && name.compareTo(".jpeg")!=0){
				JOptionPane.showMessageDialog(null, "Le fichier que vous avez choisi n'est pas compatible !\nLes formats supportés sont le JPEG et le PNG.", "Attention", JOptionPane.ERROR_MESSAGE);
			}
			else {
				listeImages.add(fichier.getPath());
				dessinerImages(fichier.getPath());
			}
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
