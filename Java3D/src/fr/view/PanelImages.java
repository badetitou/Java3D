package fr.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelImages extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int columns=35;
	private final JPanel galerie;
	private final ArrayList<JLabel> images;
	private String url="";
	public PanelImages(){
		this.setLayout(new FlowLayout(0,20,20));
		galerie=new JPanel();
		galerie.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-500,Window.outil.getScreenSize().height/5));
		galerie.setLayout(new FlowLayout(0,5,5));
		//galerie.setLayout(new GridLayout(1,columns,7,0));
		images = new ArrayList<JLabel>();
		JScrollPane scroll = new JScrollPane(galerie);
		scroll.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-500,Window.outil.getScreenSize().height/5));
		JLabel label;
		for (int i=0;i< columns;i++){
			label=new JLabel();
			url="ressources/icones/oneview.png";
			label.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
			images.add(label);
			galerie.add(images.get(i));
		}
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scroll);


	}

}
