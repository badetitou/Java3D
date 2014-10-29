package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelEdit extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton boutonCentre;
	public PanelEdit(){
		this.setPreferredSize(new Dimension(300,200));
		this.setBackground(new Color(190,190,190));
		this.setLayout(new FlowLayout(0,100,20));
		JPanel panelBouton=new JPanel();
		panelBouton.setLayout(new GridLayout(1,1,0,25));
		panelBouton.setBackground(new Color(190,190,190));

		boutonCentre=new JButton("Recentrer");
		boutonCentre.addMouseListener(this);

		panelBouton.add(boutonCentre);

		this.add(panelBouton);
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(boutonCentre)){
			if(BarreVerticale.bb1 || BarreVerticale.modeEdit ){
				MyDeskTopPane.m.zoomAuto(MyDeskTopPane.dimension);
				Panneau.pan.revalidate();
				Panneau.pan.repaint();
			}
			else{
				MyDeskTopPane.m.zoomAuto(MyDeskTopPane.dimmini);
				Panneau.pan.revalidate();
				Panneau.pan.repaint();
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
