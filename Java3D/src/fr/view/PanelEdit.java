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
	private final MyDeskTopPane dp;
	private final BarreVerticale bv;
	public PanelEdit(MyDeskTopPane dp,BarreVerticale bv){
		this.bv=bv;
		this.dp=dp;
		this.setPreferredSize(new Dimension(300,200));
		this.setBackground(new Color(190,190,190));
		this.setLayout(new FlowLayout(0,100,20));
		JPanel panelBouton=new JPanel();
		panelBouton.setLayout(new GridLayout(1,1,0,25));
		panelBouton.setBackground(new Color(190,190,190));

		boutonCentre=new JButton("Recentrer");
		//iboutonRecentrer=new ImageIcon(new ImageIcon("ressources/icones/boutons/recentrer.png").getImage().getScaledInstance(80, 30, Image.SCALE_DEFAULT));
		//boutonCentre.setMargin(new Insets(0, 0, 0, 0));
		//boutonCentre.setBorder(null);
		//boutonCentre.setIcon(iboutonRecentrer);
		boutonCentre.addMouseListener(this);

		panelBouton.add(boutonCentre);

		this.add(panelBouton);
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(boutonCentre)){
			if(bv.bb1 || bv.modeEdit ){
				this.dp.getModel().zoomAuto(MyDeskTopPane.dimension);
				this.dp.panel.repaint();
			}
			else{
				this.dp.getModel().zoomAuto(MyDeskTopPane.dimmini);
				this.dp.getModel().recentrer();
				this.dp.panel.repaint();
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
