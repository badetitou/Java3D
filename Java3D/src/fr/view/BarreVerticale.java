package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BarreVerticale extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton b1;
	public JButton b2;
	public JButton b3;

	public ImageIcon ic1;
	public ImageIcon ic2;
	public ImageIcon ic3;


	public ImageIcon ic6;
	public ImageIcon ic7;
	public ImageIcon ic8;


	public boolean bb1=false;
	public boolean bb2=false;
	public boolean bb3=false;


	public BarreVerticale(){
		this.setLayout(new GridLayout(3,1,0,4));
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.setBackground(new Color(190,190,190));

		b1=new JButton();
		b2=new JButton();
		b3=new JButton();

		b1.setToolTipText("Mode 1 vue");
		b2.setToolTipText("Mode 4 vues");
		b3.setToolTipText("Mode edit");


		ic1=new ImageIcon(new ImageIcon("ressources/icones/oneview.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		ic2=new ImageIcon(new ImageIcon("ressources/icones/multipanel.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		ic3=new ImageIcon(new ImageIcon("ressources/icones/edit.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));


		ic6=new ImageIcon(new ImageIcon("ressources/icones/oneviewclic.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		ic7=new ImageIcon(new ImageIcon("ressources/icones/multipanelclic.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		ic8=new ImageIcon(new ImageIcon("ressources/icones/editclic.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));


		b1.setMargin(new Insets(0, 0, 0, 0));
		b2.setMargin(new Insets(0, 0, 0, 0));
		b3.setMargin(new Insets(0, 0, 0, 0));


		b1.setBorder(null);
		b2.setBorder(null);
		b3.setBorder(null);


		b1.addMouseListener(this);
		b2.addMouseListener(this);
		b3.addMouseListener(this);



		b1.setIcon(ic6);
		bb1=true;
		b2.setIcon(ic2);
		b3.setIcon(ic3);


		this.add(b1);
		this.add(b2);
		this.add(b3);

	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(b1) && !bb1){
			b1.setIcon(ic6);
			b2.setIcon(ic2);
			b3.setIcon(ic3);
			bb1=true;
			bb2=false;
			bb3=false;
			Panneau.d = new Dimension(800,800);
			MyDeskTopPane.iFrameMain.setLocation(0, 0);
			MyDeskTopPane.iFrameMain.setPreferredSize(new Dimension(800, 800));
			MyDeskTopPane.iFrameDessous.setVisible(false);
			MyDeskTopPane.iFrameDessus.setVisible(false);
			MyDeskTopPane.iFrameProfil.setVisible(false);
		}
		else if(e.getSource().equals(b2) && !bb2){
			b2.setIcon(ic7);
			b1.setIcon(ic1);
			b3.setIcon(ic3);
			bb2=true;
			bb1=false;
			bb3=false;
			Panneau.d = new Dimension(400,400); // NE PAS TOUCHER CECI PERMET L'AFFICHAGE CENTRE
			MyDeskTopPane.iFrameMain.setPreferredSize(new Dimension( 400, 400));
			MyDeskTopPane.iFrameDessous.setVisible(true);
			MyDeskTopPane.iFrameDessus.setVisible(true);
			MyDeskTopPane.iFrameProfil.setVisible(true);

		}
		else if(e.getSource().equals(b3) && !bb3){
			b3.setIcon(ic8);
			b2.setIcon(ic2);
			b1.setIcon(ic1);
			bb3=true;
			bb1=false;
			bb2=false;
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
