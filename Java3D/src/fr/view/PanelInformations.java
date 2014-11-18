package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.model.OutilsBdd;

public class PanelInformations extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel nomObjet;
	private final JLabel auteur;
	private final JLabel dateAjout;
	private final JLabel derniereModif;
	private final JLabel nbChargements;
	private final JLabel nbImages;
	private final JLabel nbRealisations;
	private final int size=Window.outil.getScreenSize().height/27;
	private final OutilsBdd obdd;

	private Font font;

	public PanelInformations(String nomFichier,String nomAuteur,boolean nouveau){
		this.setBackground(new Color(215,215,215));
		this.setLayout(new FlowLayout(0,30,20));
		obdd=new OutilsBdd("Database.db");

		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new GridLayout(3,3,15,0));
		panelInfo.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-(Window.outil.getScreenSize().width/3),Window.outil.getScreenSize().height/6));

		nomObjet=new JLabel("Nom de l'objet : "+nomFichier);
		font = nomObjet.getFont();
		nomObjet.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		nomObjet.setIcon(new ImageIcon(new ImageIcon("ressources/icones/iconesInformations/nomObjet.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));

		auteur=new JLabel("Nom auteur : "+nomAuteur);
		font = auteur.getFont();
		auteur.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		auteur.setIcon(new ImageIcon(new ImageIcon("ressources/icones/iconesInformations/auteur.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));

		String dateModiff="";
		String dateAjoutt="";
		if(nouveau){
			String format = "yyyy-dd-MM";
			SimpleDateFormat formater = new SimpleDateFormat( format );
			dateAjoutt=formater.format(new Date());
			dateModiff=dateAjoutt;
		}
		else{
			dateAjoutt=obdd.getDateAdd(nomFichier);
			dateModiff=obdd.getDateLastModif(nomFichier);
		}

		dateAjout=new JLabel("Date d'ajout : "+dateAjoutt);
		font = dateAjout.getFont();
		dateAjout.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		dateAjout.setIcon(new ImageIcon(new ImageIcon("ressources/icones/iconesInformations/dateAjout.png").getImage().getScaledInstance(size,size, Image.SCALE_DEFAULT)));

		derniereModif=new JLabel("Dernière modification : "+dateModiff);
		font = derniereModif.getFont();
		derniereModif.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		derniereModif.setIcon(new ImageIcon(new ImageIcon("ressources/icones/iconesInformations/derniereModif.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));

		nbChargements=new JLabel("Nombre de chargements : "+obdd.getnbrOpen(nomFichier)+1);
		font = nbChargements.getFont();
		nbChargements.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		nbChargements.setIcon(new ImageIcon(new ImageIcon("ressources/icones/iconesInformations/nbChargements.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));

		nbImages=new JLabel("Nombre d'images : "+obdd.getnbrImg(nomFichier));
		font = nbImages.getFont();
		nbImages.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		nbImages.setIcon(new ImageIcon(new ImageIcon("ressources/icones/iconesInformations/nbImages.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));

		nbRealisations=new JLabel("Nombre de réalisations : 0");
		font = nbRealisations.getFont();
		nbRealisations.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		nbRealisations.setIcon(new ImageIcon(new ImageIcon("ressources/icones/iconesInformations/nbRealisations.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));



		panelInfo.add(nomObjet);
		panelInfo.add(auteur);
		panelInfo.add(dateAjout);
		panelInfo.add(derniereModif);
		panelInfo.add(nbChargements);
		panelInfo.add(nbImages);
		panelInfo.add(nbRealisations);

		this.add(panelInfo);


	}

}
