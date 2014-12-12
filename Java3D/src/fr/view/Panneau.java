package fr.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

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
	MyDeskTopPane dp;
	private boolean control = false;
	/*
	 * Low: 0 Medium: 1 Hight: 2
	 */
	private int qualite = 0;

	public void setQualite(int i) {
		qualite = i;
		repaint();
	}

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
			this.setFocusable(true);
			this.addKeyListener(new KeyListener() {

				public void keyTyped(KeyEvent e) {
				}

				public void keyReleased(KeyEvent e) {
					control = false;
				}

				public void keyPressed(KeyEvent e) {
					control = true;
				}
			});
			this.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					if (getBarreVerticale().getModeEdit()) {
						if (!control) {
							m.reinitSelected();
						}
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
					requestFocus();
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
					m.zoom((double) ((-e.getWheelRotation() + 15.0)) / 15.0);
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
							} else if (Barre.boolButtonY) {
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
		m.trieFace();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		if (this.qualite == 2) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
					RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		} else if (qualite == 1){
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
					RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		} else {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_SPEED);
			g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
					RenderingHints.VALUE_COLOR_RENDER_SPEED);
		}
		super.paintComponent(g);

		if (getBarreVerticale().isSquelette()) {
			for (Face f : m.getFace()) {
				g.draw(f.getTriangle());
			}
		} else if (getBarreVerticale().isModeEdit()) {
			for (Face f : m.getFace()) {
				g.setColor(f.calculLumiere());
				g.fill(f.getTriangle());
				if (f.isSelected()) {
					g.setColor(new Color(255 - g.getColor().getRed(), 255 - g
							.getColor().getGreen(), 255 - g.getColor()
							.getBlue()));
					g.draw(f.getTriangle());
				}
			}
		} else if (getBarreVerticale().isBb1() || getBarreVerticale().isBb2()) {
			for (Face f : m.getFace()) {
				g.setColor(f.calculLumiere());
				g.fillPolygon(f.getTriangle());
			}
		} else if (getBarreVerticale().isDot()) {
			for (fr.model.Point p : m.getListPoint())
				g.drawRect((int) (p.x + m.xTranslate), (int) (p.y + m.yTranslate), 1, 1);
		}
		g.dispose();
	}
}
