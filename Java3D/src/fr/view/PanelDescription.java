package fr.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelDescription extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextArea textArea;

	public JPanel panelBouton;
	public JPanel panelDescription;

	private final JButton valider;
	private final JButton supprimer;
	private final JButton annuler;

	private final JLabel label;

	private final String nomObjet="";

	public PanelDescription(){
		this.setLayout(new FlowLayout(0,30,30));


		textArea=new JTextArea();
		textArea.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-500,Window.outil.getScreenSize().height/5));

		label=new JLabel("Description de l'objet "+nomObjet+ " : ");
		label.setPreferredSize(new Dimension(70,30));

		panelBouton=new JPanel();
		panelBouton.setLayout(new GridLayout(3,1,0,20));

		valider=new JButton("Valider");
		supprimer=new JButton("Supprimer");
		annuler=new JButton("Annuler");

		panelBouton.add(valider);
		panelBouton.add(supprimer);
		panelBouton.add(annuler);
		/*
		panelDescription=new JPanel();
		panelDescription.setLayout(new GridLayout(2,1));
		panelDescription.add(label);
		panelDescription.add(textArea);
		 */

		this.add(textArea);
		this.add(panelBouton);
	}

}
