package fr.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import fr.model.Model;

public class Panneau extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Model m;
	public static Dimension d;

	public Panneau(Model mod) {
		this.m = mod;
		if(m.vue==1){
			m.rotationX(0);
			m.rotationY(-515);
			repaint();
		}
		else if(m.vue==2){
			m.rotationX(0);
			m.rotationY(515);
			repaint();
		}
		else if(m.vue==3){
			m.rotationX(515);
			m.rotationY(0);
			repaint();
		}

		repaint();
		this.addMouseWheelListener (new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				m.zoom((-e.getPreciseWheelRotation() + 15)/15);
				repaint();
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {
			int coordMouseX = 0;
			int coordMouseY = 0;

			public void mouseMoved(MouseEvent e) {
				coordMouseX = e.getX();
				coordMouseY = e.getY();
			}

			public void mouseDragged(MouseEvent e) {
				if(m.vue==0){
					if(Barre.boolButtonX && Barre.boolButtonY){
						m.rotationX(e.getX() - coordMouseX);
						m.rotationY(e.getY() - coordMouseY);
						coordMouseX = e.getX();
						coordMouseY = e.getY();
						repaint();
					}
					else if (Barre.boolButtonX && !Barre.boolButtonY){
						m.rotationX(e.getX() - coordMouseX);
						//m.rotationY(e.getY() - coordMouseY);
						coordMouseX = e.getX();
						coordMouseY = e.getY();
						repaint();
					}
					else if (!Barre.boolButtonX && Barre.boolButtonY){
						//m.rotationX(e.getX() - coordMouseX);
						m.rotationY(e.getY() - coordMouseY);
						coordMouseX = e.getX();
						coordMouseY = e.getY();
						repaint();
					}
					else{
						/*m.rotationZ(e.getY() - coordMouseY);
						coordMouseX = e.getX();
						coordMouseY = e.getY();
						repaint();*/
					}
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < m.getFace().size(); i++) {
			m.getFace().get(i).paint(g);
		}
	}
}