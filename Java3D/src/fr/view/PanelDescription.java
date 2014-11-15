package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class PanelDescription extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextArea textArea;

	private final JPanel panelBouton;
	private final JButton valider;
	private final JButton modifier;

	private final JLabel label;

	private final String nomObjet="";
	public PanelDescription(){
		this.setLayout(new FlowLayout(0,30,30));
		this.setBackground(new Color(215,215,215));


		textArea=new JTextArea();
		textArea.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-500,Window.outil.getScreenSize().height/6));
		textArea.setEditable(false);
		textArea.setBackground(new Color(230,230,230));
		Border border = BorderFactory.createLineBorder(new Color(190,190,190));
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textArea.setForeground(new Color(130,130,130));

		label=new JLabel("Description de l'objet "+nomObjet+ " : ");
		label.setPreferredSize(new Dimension(70,30));

		panelBouton=new JPanel();
		panelBouton.setLayout(new GridLayout(3,1,0,20));

		valider=new JButton("Valider");
		valider.setEnabled(false);
		modifier=new JButton("Modifier");

		valider.addMouseListener(this);
		modifier.addMouseListener(this);


		panelBouton.add(modifier);
		panelBouton.add(valider);
		panelBouton.setBackground(new Color(215,215,215));
		/*
		panelDescription=new JPanel();
		panelDescription.setLayout(new GridLayout(2,1));
		panelDescription.add(label);
		panelDescription.add(textArea);
		 */

		this.add(textArea);
		this.add(panelBouton);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(modifier)){
			textArea.setEditable(true);
			valider.setEnabled(true);
			modifier.setEnabled(false);
			textArea.setBackground(new Color(255,255,255));
			textArea.setForeground(new Color(0,0,0));
		}


		if(e.getSource().equals(valider)){
			textArea.setEditable(false);
			valider.setEnabled(false);
			modifier.setEnabled(true);
			textArea.setBackground(new Color(230,230,230));
			textArea.setForeground(new Color(130,130,130));
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
