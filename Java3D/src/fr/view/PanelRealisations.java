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

public class PanelRealisations extends JPanel implements MouseListener{

	/**
	 * 
	 */

	private final OutilsBdd obdd;
	private final JPanel galerie;
	private final JButton ajouterRealisation;
	private final JButton supprimerRealisation;
	private final JButton ouvrirRealisation;
	private int nbImagesSelection;
	private int nbImages;
	private final ArrayList<PRealisation> listePanels;
	private PRealisation p;
	private ArrayList<String>listeRealisation;
	private final String lien;
	private static final long serialVersionUID = 1L;
	private final boolean nouveau;

	public PanelRealisations(String nomFichier,boolean nouveau){
		this.setLayout(new FlowLayout(0,20,20));
		this.nouveau=nouveau;
		this.setBackground(new Color(215,215,215));
		obdd=new OutilsBdd("Database.db");
		galerie=new JPanel();
		galerie.setLayout(new FlowLayout(0,15,2));
		if(!this.nouveau){
			lien=obdd.getLinkImg(nomFichier);
			listeRealisation=listerRepertoire(lien);
			listePanels=new ArrayList<PRealisation>();


			if(listeRealisation!=null){
				JLabel label;
				for (int i=0;i< listeRealisation.size();i++){
					dessinerImages(listeRealisation.get(i));
					nbImages++;
				}
			}
			else {
				listeRealisation=new ArrayList<String>();
			}
		}
		else {
			lien=null;
			listePanels=new ArrayList<PRealisation>();
			listeRealisation=new ArrayList<String>();
		}

		JScrollPane scroll = new JScrollPane(galerie);
		scroll.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-(Window.outil.getScreenSize().width/3),Window.outil.getScreenSize().height/6));

		ajouterRealisation=new JButton("Ajouter la réalisation");
		supprimerRealisation=new JButton("Supprimer réalisation(s)");
		ouvrirRealisation=new JButton("Ouvrir la réalisation");
		supprimerRealisation.setEnabled(false);

		JPanel pBoutons =new JPanel();
		pBoutons.setLayout(new GridLayout(3,1,0,50));
		pBoutons.add(ouvrirRealisation);
		pBoutons.add(ajouterRealisation);
		pBoutons.add(supprimerRealisation);
		pBoutons.setBackground(new Color(215,215,215));

		ajouterRealisation.addMouseListener(this);
		supprimerRealisation.addMouseListener(this);
		ouvrirRealisation.addMouseListener(this);

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
					listeImages.add(lien+listefichiers[i]);
				}
			}
			return listeImages;
		}
		return null;
	}

	public void dessinerImages(String path){
		p=new PRealisation(path);
		galerie.add(p);
		galerie.revalidate();
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(ajouterRealisation)){
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
					listeRealisation.add(fichier.getPath());
					nbImages++;
					//System.out.println(nbImages);
				}
			}
		}

		if (e.getSource().equals(supprimerRealisation)){
			for (int i=0;i<listeRealisation.size();i++){
				if(listePanels.get(i).getSelection()){
					galerie.remove(listePanels.get(i));        //bug
					listeRealisation.remove(listePanels.get(i).getPath());
					listePanels.remove(i);
					i--;
					nbImages--;
				}
			}
			nbImagesSelection=0;
			if(nbImages==0 || nbImagesSelection==0){
				this.supprimerRealisation.setEnabled(false);
			}
			this.repaint();
			this.revalidate();
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


	public class PRealisation extends JPanel implements MouseListener{
		private final String path;
		private boolean selection;
		private JLabel l;
		public PRealisation(String path){
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
			//listePanels.add(this);
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
				supprimerRealisation.setEnabled(true);
				this.selection=true;
				nbImagesSelection++;

			}
			else {
				this.setBackground(new Color(215,215,215));
				this.revalidate();
				nbImagesSelection--;
				if(nbImagesSelection==0){
					supprimerRealisation.setEnabled(false);
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
