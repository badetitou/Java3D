package fr.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import fr.model.Face;
import fr.model.Model;

public class Panneau extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Model m;
	private int coordMouseX = 0;
	private int coordMouseY = 0;





	public Model getM() {
		return m;
	}

	public Panneau(Model mod) {
		this.m = mod;
		if (m.vue == 1) {
			m.rotationX(0);
			m.rotationY(515);
			repaint();
		} else if (m.vue == 2) {
			m.rotationX(0);
			m.rotationY(-515);
			repaint();
		} else if (m.vue == 3) {
			m.rotationX(515);
			m.rotationY(0);
			repaint();
		}

		this.addMouseListener(new MouseListener(){
			Face f;
			
			public void mouseClicked(MouseEvent arg0) {
				if (f!= null){
					f.setSelected(false);
				}
				f = m.getParticularFace(coordMouseX,coordMouseY);
				f.setSelected(true);
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

		});

		repaint();
		this.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				m.zoom((-e.getPreciseWheelRotation() + 15) / 15);
				repaint();
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {


			public void mouseMoved(MouseEvent e) {
				coordMouseX = e.getX();
				coordMouseY = e.getY();
			}

			public void mouseDragged(MouseEvent e) {
				if (m.vue == 0) {
					if (Barre.boolButtonRotation) {
						if (Barre.boolButtonX && Barre.boolButtonY) {
							m.rotationX(e.getX() - coordMouseX);
							m.rotationY(e.getY() - coordMouseY);
							coordMouseX = e.getX();
							coordMouseY = e.getY();
							repaint();
						} else if (Barre.boolButtonX && !Barre.boolButtonY) {
							m.rotationY(e.getY() - coordMouseY);
							coordMouseX = e.getX();
							coordMouseY = e.getY();
							repaint();
						} else if (!Barre.boolButtonX && Barre.boolButtonY) {;
						m.rotationX(e.getX() - coordMouseX);
						coordMouseX = e.getX();
						coordMouseY = e.getY();
						repaint();
						}
					}
					else if (Barre.boolButtonTranslation){
						if(Barre.boolButtonX && Barre.boolButtonY){
							m.translation(e.getX() - coordMouseX, e.getY() - coordMouseY);
							coordMouseX = e.getX();
							coordMouseY = e.getY();
							repaint();
						}
						else if (Barre.boolButtonX){
							m.translation(e.getX() - coordMouseX,0);
							coordMouseX = e.getX();
							coordMouseY = e.getY();
							repaint();
						}
						else if (Barre.boolButtonY){
							m.translation(0, e.getY() - coordMouseY);
							coordMouseX = e.getX();
							coordMouseY = e.getY();
							repaint();
						}
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

	public void setD(Dimension dimension) {
		this.m.setD(dimension);
	}
}