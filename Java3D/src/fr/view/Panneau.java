package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
	MyDeskTopPane dp;

	Thread t;
	Image img;
	Graphics2D g2d;

	public BarreVerticale getBarreVerticale() {
		return dp.getBarreVerticale();
	}

	public List<Face> getAllSelectedFace() {
		List<Face> lf = new ArrayList<Face>();
		for (int i = 0; i < m.getFace().size(); ++i) {
			if (m.getFace().get(i).isSelected()) {
				lf.add(m.getFace().get(i));
			}
		}
		return lf;
	}

	public Model getM() {
		return m;
	}

	public Panneau(Model mod, MyDeskTopPane dp) {
		this.dp = dp;
		this.m = mod;
		if (m.vue == 1) {
			m.rotationX(0);
			m.rotationY(515);
		} else if (m.vue == 2) {
			m.rotationX(0);
			m.rotationY(-515);
		} else if (m.vue == 3) {
			m.rotationX(515);
			m.rotationY(0);
		} else {
			this.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					if (getBarreVerticale().getModeEdit()) {
						Face selected = m.getParticularFace(coordMouseX,
								coordMouseY);
						if (selected != null) {
							if (selected.isSelected())
								selected.setSelected(false);
							else
								selected.setSelected(true);
						}
						repaint();
					}
				}

				public void mouseEntered(MouseEvent arg0) {
				}

				public void mouseExited(MouseEvent arg0) {
				}

				public void mousePressed(MouseEvent arg0) {
				}

				public void mouseReleased(MouseEvent arg0) {
				}
			});

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
								m.rotationY(coordMouseY - e.getY());
							} else if (Barre.boolButtonX) {
								m.rotationX(e.getX() - coordMouseX);
							} else {
								m.rotationY(coordMouseY - e.getY());
							}

						} else if (Barre.boolButtonTranslation) {
							if (Barre.boolButtonX && Barre.boolButtonY) {
								m.translation(e.getX() - coordMouseX, e.getY()
										- coordMouseY);
							} else if (Barre.boolButtonX) {
								m.translation(e.getX() - coordMouseX, 0);
							} else if (Barre.boolButtonY) {
								m.translation(0, e.getY() - coordMouseY);
							}

						}
						coordMouseX = e.getX();
						coordMouseY = e.getY();
						m.trieFace();
						repaint();
					}
				}
			});
		}
	}

	@Override
	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		
		if (getBarreVerticale().isSquelette()) {
			for (Face f : m.getFace()) {
				g.setColor(f.calculLumiere());
				g.drawPolygon(f.getTriangle());
			}
		} else if (getBarreVerticale().isModeEdit()){
			for (Face f : m.getFace()){
				g.setColor(f.calculLumiere());
				g.drawPolygon(f.getTriangle());
				if (f.isSelected()){
					g.setColor(new Color(255 - g.getColor().getRed(), 255 - g
							.getColor().getGreen(), 255 - g.getColor().getBlue()));
					g.drawPolygon(f.getTriangle());
				} else {
					
				}
			}
		} else if (getBarreVerticale().isBb1()){
			for (Face f : m.getFace()) {
				g.setColor(f.calculLumiere());
				g.fillPolygon(f.getTriangle());
			}
		}
		g.dispose();
	}

	public void setD(Dimension dimension) {
		this.m.setD(dimension);
	}
}
