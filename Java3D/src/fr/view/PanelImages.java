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

import javax.swing.BorderFactory;
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
	private final JButton ajouterImage;
	private final JButton supprimerImage;
	public static boolean boutonAjouterImage;
	public static boolean boutonSupprimerImage;
	private final OutilsBdd obdd;
	private final String lien;
	private ArrayList<String>listeImages;
	public ArrayList<String> getListeImages() {
		return listeImages;
	}


	private final ArrayList<PImage> listePanels;
	private PImage p;
	private int nbImages;
	private int nbImagesSelection;
	public PanelImages(String nomFichier,boolean nouveau){
		this.setLayout(new FlowLayout(0,20,20));
		this.setBackground(new Color(215,215,215));
		obdd=new OutilsBdd("Database.db");
		galerie=new JPanel();
		galerie.setLayout(new FlowLayout(0,15,2));
		if(!nouveau){
			lien=obdd.getLinkImg(nomFichier);
			listeImages=listerRepertoire(lien);
			listePanels=new ArrayList<PImage>();
			if(listeImages!=null){
				JLabel label;
				for (int i=0;i< listeImages.size();i++){
					dessinerImages(listeImages.get(i));
					nbImages++;
					System.out.println(nbImages);
				}
			}
			else {
				listeImages=new ArrayList<String>();
			}
		}
		else {
			lien=null;
			listePanels=new ArrayList<PImage>();
			listeImages=new ArrayList<String>();
		}
		JScrollPane scroll = new JScrollPane(galerie);
		scroll.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-(Window.outil.getScreenSize().width/3),Window.outil.getScreenSize().height/6));

		ajouterImage=new JButton("Ajouter une image");
		supprimerImage=new JButton("Supprimer image(s)");
		supprimerImage.setEnabled(false);

		JPanel pBoutons =new JPanel();
		pBoutons.setLayout(new GridLayout(2,1,0,50));
		pBoutons.add(ajouterImage);
		pBoutons.add(supprimerImage);
		pBoutons.setBackground(new Color(215,215,215));

		ajouterImage.addMouseListener(this);
		supprimerImage.addMouseListener(this);

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
					listeImages.add(lien+"/"+listefichiers[i]);
				}
			}
			return listeImages;
		}
		return null;
	}

	public void dessinerImages(String path){
		p=new PImage(path);
		galerie.add(p);
		galerie.revalidate();
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(ajouterImage)){
			JFileChooser dialogue = new JFileChooser(new File("."));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Image","png","jpg","jpeg");
			dialogue.setFileFilter(filter);
			final File fichier;
			int a =dialogue.showOpenDialog(null);
			fichier = dialogue.getSelectedFile();
			if(fichier!=null && a == JFileChooser.APPROVE_OPTION){
				String name = fichier.getName();
				name=name.substring(name.length()-4, name.length());
				if(name.compareTo(".png")!=0 && name.compareTo(".jpg")!=0 && name.compareTo(".jpeg")!=0){
					JOptionPane.showMessageDialog(null, "Le fichier que vous avez choisi n'est pas compatible !\nLes formats supportés sont le JPEG et le PNG.", "Attention", JOptionPane.ERROR_MESSAGE);
				}
				else {
					dessinerImages(fichier.getPath());
					listeImages.add(fichier.getPath());
					nbImages++;
					System.out.println(nbImages);
				}
			}
		}

		if (e.getSource().equals(supprimerImage)){
			for (int i=0;i<listeImages.size();i++){
				if(listePanels.get(i).getSelection()){
					galerie.remove(listePanels.get(i));        //bug
					listeImages.remove(listePanels.get(i));
					listePanels.get(i).setSelection(false);
					nbImages--;
				}
			}
			nbImagesSelection=0;
			System.out.println(nbImages);
			if(nbImages==0 || nbImagesSelection==0){
				this.supprimerImage.setEnabled(false);
			}
			this.repaint();
			this.revalidate();
		}
	}
	public int getNbImages() {
		return nbImages;
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


	public class PImage extends JPanel implements MouseListener{
		private final String path;
		private boolean selection;
		private JLabel l;
		public PImage(String path){
			this.path=path;
			this.dessinerP();
			this.setBackground(new Color(215,215,215));
		}

		public void dessinerP(){
			l=new JLabel();
			this.addMouseListener(this);
			l.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(Window.outil.getScreenSize().width/13, Window.outil.getScreenSize().width/13, Image.SCALE_SMOOTH)));
			this.add(l);
			this.setBorder(BorderFactory.createLineBorder(new Color(190,190,190)));
			listePanels.add(this);
		}

		public String getPath(){
			return this.path;
		}

		public boolean getSelection(){
			return this.selection;
		}

		public void setSelection(boolean selection){
			this.selection=selection;
		}

		public void mouseClicked(MouseEvent arg0) {
			if(!this.selection){
				this.setBackground(new Color(119,181,254));
				supprimerImage.setEnabled(true);
				this.selection=true;
				nbImagesSelection++;

			}
			else {
				this.setBackground(new Color(215,215,215));
				this.revalidate();
				nbImagesSelection--;
				if(nbImagesSelection==0){
					supprimerImage.setEnabled(false);
				}
				this.selection=false;
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

}
