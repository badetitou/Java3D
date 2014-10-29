package fr.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Barre extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton b1;
	public JButton b2;
	public JButton b3;
	public JButton b4;
	public JButton b5;

	public ImageIcon ic1;
	public ImageIcon ic2;
	public ImageIcon ic3;
	public ImageIcon ic4;
	public ImageIcon ic5;

	public ImageIcon ic6;
	public ImageIcon ic7;
	public ImageIcon ic8;
	public ImageIcon ic9;
	public ImageIcon ic10;

	public static boolean boolButtonRotation=false;
	public static boolean boolButtonTranslation=false;
	public static boolean boolButtonX=false;
	public static boolean boolButtonY=false;
	public static boolean boolButtonZ=false;

	public Barre(){
		this.setLayout(new FlowLayout(0,2,0));
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.setBackground(new Color(190,190,190));


		b1=new JButton();
		b2=new JButton();
		b3=new JButton();
		b4=new JButton();
		b5=new JButton();

		b1.setToolTipText("Rotation");
		b2.setToolTipText("Deplacement");
		b3.setToolTipText("x");
		b4.setToolTipText("y");
		b5.setToolTipText("z");


		ic1=new ImageIcon(new ImageIcon("ressources/icones/iconerota.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ic2=new ImageIcon(new ImageIcon("ressources/icones/iconedepla.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ic3=new ImageIcon(new ImageIcon("ressources/icones/x.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ic4=new ImageIcon(new ImageIcon("ressources/icones/y.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ic5=new ImageIcon(new ImageIcon("ressources/icones/z.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

		ic6=new ImageIcon(new ImageIcon("ressources/icones/iconerotaclic.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ic7=new ImageIcon(new ImageIcon("ressources/icones/iconedeplaclic.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ic8=new ImageIcon(new ImageIcon("ressources/icones/xclic.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ic9=new ImageIcon(new ImageIcon("ressources/icones/yclic.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ic10=new ImageIcon(new ImageIcon("ressources/icones/zclic.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

		b1.setMargin(new Insets(0, 0, 0, 0));
		b2.setMargin(new Insets(0, 0, 0, 0));
		b3.setMargin(new Insets(0, 0, 0, 0));
		b4.setMargin(new Insets(0, 0, 0, 0));
		b5.setMargin(new Insets(0, 0, 0, 0));

		b1.setBorder(null);
		b2.setBorder(null);
		b3.setBorder(null);
		b4.setBorder(null);
		b5.setBorder(null);

		b1.addMouseListener(this);
		b2.addMouseListener(this);
		b3.addMouseListener(this);
		b4.addMouseListener(this);
		b5.addMouseListener(this);


		b1.setIcon(ic6);
		boolButtonRotation=true;
		b2.setIcon(ic2);
		b3.setIcon(ic8);
		boolButtonX=true;
		b4.setIcon(ic9);
		boolButtonY=true;
		b5.setIcon(ic10);
		boolButtonZ=true;

		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(b1) && !boolButtonRotation){
			if (boolButtonTranslation){
				b2.setIcon(ic2);
				boolButtonTranslation=false;
			}
			b1.setIcon(ic6);
			boolButtonRotation=true;
			if(!b5.isEnabled())
				b5.setEnabled(true);
		}
		else if(e.getSource().equals(b2) && !boolButtonTranslation){
			if (boolButtonRotation){
				b1.setIcon(ic1);
				boolButtonRotation=false;
			}
			b5.setEnabled(false);
			b5.setIcon(ic5);
			b2.setIcon(ic7);
			boolButtonTranslation=true;
		}
		else if(e.getSource().equals(b3) && !boolButtonX){
			b3.setIcon(ic8);
			boolButtonX=true;
		}
		else if(e.getSource().equals(b4) && !boolButtonY){
			b4.setIcon(ic9);
			boolButtonY=true;
		}
		else if(e.getSource().equals(b5) && !boolButtonZ){
			b5.setIcon(ic10);
			boolButtonZ=true;
		}

		else if(e.getSource().equals(b3) && boolButtonX){
			b3.setIcon(ic3);
			boolButtonX=false;
		}
		else if(e.getSource().equals(b4) && boolButtonY){
			b4.setIcon(ic4);
			boolButtonY=false;
		}
		else if(e.getSource().equals(b5) && boolButtonZ){
			b5.setIcon(ic5);
			boolButtonZ=false;
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
