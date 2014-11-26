package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelEdit extends JPanel implements MouseListener, ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton boutonCentre;
	private final MyDeskTopPane dp;
	private final BarreVerticale bv;
	private final JSlider js1;
	private final JSlider js2;
	private final JSlider js3;
	private final JLabel red;
	private final JLabel blue;
	private final JLabel green;

	public PanelEdit(MyDeskTopPane dp,BarreVerticale bv){
		this.bv=bv;
		this.dp=dp;
		this.setPreferredSize(new Dimension(300,200));
		this.setBackground(new Color(190,190,190));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		/*
		 * JBouton Centre
		 */
		boutonCentre=new JButton("Recentrer");
		boutonCentre.addMouseListener(this);

		/*
		 * JSlider
		 */
		js1 = new JSlider(0, 255);
		js1.setOpaque(false);
		js1.setPaintLabels(true);
		js1.setPaintTicks(true);
		js1.setMinorTickSpacing(1);
		js1.setMajorTickSpacing(50);
		js1.setValue(0);
		js1.addChangeListener(this);

		js2 = new JSlider(0, 255);
		js2.setOpaque(false);
		js2.setPaintLabels(true);
		js2.setPaintTicks(true);
		js2.setMinorTickSpacing(1);
		js2.setMajorTickSpacing(50);
		js2.setValue(0);
		js2.addChangeListener(this);

		js3 = new JSlider(0, 255);
		js3.setPaintLabels(true);
		js3.setOpaque(false);
		js3.setPaintTicks(true);
		js3.setMinorTickSpacing(1);
		js3.setMajorTickSpacing(50);
		js3.setValue(0);
		js3.addChangeListener(this);


		/*
		 * JLabel
		 */
		red = new JLabel("Rouge : " + js1.getValue());
		blue = new JLabel("Bleu : " + js2.getValue());
		green = new JLabel("Vert : " + js3.getValue());


		/*
		 * Grid Bag
		 */
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(boutonCentre,c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(red,c);
		c.gridx = 0;
		c.gridy = 2;
		this.add(js1, c);
		c.gridx = 0;
		c.gridy = 3;
		this.add(blue,c);
		c.gridx = 0;
		c.gridy = 4;
		this.add(js2,c);
		c.gridx = 0;
		c.gridy = 5;
		this.add(green,c);
		c.gridx = 0;
		c.gridy = 6;
		this.add(js3,c);
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(boutonCentre)){
			if(bv.isBb1() || bv.isModeEdit() ){
				this.dp.getModel().zoomAuto();
			}
			else{
				this.dp.getModel().zoomAuto();
			}
			this.dp.repaint();
		}

	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0){}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void stateChanged(ChangeEvent arg0) {
		if (arg0.getSource() == js1){
			red.setText("Rouge : " + js1.getValue());
		} else if (arg0.getSource() == js2){
			blue.setText("Bleu : " + js2.getValue());
		} else {
			green.setText("Vert : " + js3.getValue());
		}
		if(bv.isModeEdit()){
			dp.getModel().changeColor(new Color(js1.getValue(), js3.getValue(), js2.getValue()));
			dp.getPanel().repaint();
		}
	}

}
