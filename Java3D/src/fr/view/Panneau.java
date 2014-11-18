package fr.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import fr.model.Face;
import fr.model.Model;

public class Panneau extends JPanel {
	/*
	 * Model necessaire info
	 */
	private static final long serialVersionUID = 1L;
	private final Model m;
	private int coordMouseX = 0;
	private int coordMouseY = 0;
	private BarreVerticale bv;
	/*
	 * Graphics Buffering
	 */
	private RenderingThread renderingThread;
	private Graphics buffer;
	private Image image;

	public RenderingThread getRenderingThread() {
		return renderingThread;
	}

	public void setBarreVerticale(BarreVerticale bv) {
		this.bv = bv;
	}

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
		} else {
			renderingThread = new RenderingThread();
			renderingThread.setPriority(8);
			renderingThread.start();
			this.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					if (bv.getModeEdit()) {
						Face selected = m
								.getParticularFace(coordMouseX, coordMouseY);
						if (selected != null){
							if (selected.isSelected())
								selected.setSelected(false);
							else
								selected.setSelected(true);
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

			});

			this.addMouseWheelListener(new MouseWheelListener() {
				public void mouseWheelMoved(MouseWheelEvent e) {
					m.zoom((-e.getPreciseWheelRotation() + 15) / 15);
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
							} else if (Barre.boolButtonX && !Barre.boolButtonY) {
								m.rotationY(e.getY() - coordMouseY);
								coordMouseX = e.getX();
								coordMouseY = e.getY();
							} else if (!Barre.boolButtonX && Barre.boolButtonY) {
								m.rotationX(e.getX() - coordMouseX);
								coordMouseX = e.getX();
								coordMouseY = e.getY();
							}
						} else if (Barre.boolButtonTranslation) {
							if (Barre.boolButtonX && Barre.boolButtonY) {
								m.translation(e.getX() - coordMouseX, e.getY()
										- coordMouseY);
								coordMouseX = e.getX();
								coordMouseY = e.getY();
							} else if (Barre.boolButtonX) {
								m.translation(e.getX() - coordMouseX, 0);
								coordMouseX = e.getX();
								coordMouseY = e.getY();
							} else if (Barre.boolButtonY) {
								m.translation(0, e.getY() - coordMouseY);
								coordMouseX = e.getX();
								coordMouseY = e.getY();
							}
						}
					}
				}
			});
		}
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		for (int i = 0; i < m.getFace().size(); i++) {
			m.getFace().get(i).paint(g);
		}
	}

	public void setD(Dimension dimension) {
		this.m.setD(dimension);
	}

	public void paint(Graphics g) {
		// création du buffer si il n'existe pas
		image = createImage(m.getD().width, m.getD().height);
		buffer = image.getGraphics();
		// on dessine sur le buffer mémoire
		paintComponents(buffer);
		g.drawImage(image, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	class RenderingThread extends Thread {
		/**
		 * Ce thread appelle le rafraichissement de notre fenêtre toutes les 10
		 * milli-secondes
		 */
		public void run() {
			while (true) {
				try {
					repaint();
					sleep(15);
				} catch (Exception e) {
				}
			}
		}
	}

}
