package fr.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;

import fr.model.Face;
import fr.model.Model;

public class Panneau extends JPanel implements MouseListener {
	/*
	 * Model necessaire info
	 */
	private static final long serialVersionUID = 1L;
	private final Model m;
	private int coordMouseX = 0;
	private int coordMouseY = 0;
	private MyDeskTopPane dp;
	private boolean control = false;
	private JPopupMenu popMenu;

	/*
	 * TEST
	 */
	private JRadioButtonMenuItem low;
	private JRadioButtonMenuItem medium;
	private JRadioButtonMenuItem high;
	private JMenuItem boutonCentre;
	private JMenuItem zoomPlus;
	private JMenuItem zoomMoins;
	private JMenuItem fixerDimmension;
	private JFrame  dimensionFrame = new JFrame();
	private JTextField fixHauteur;
	private JTextField fixLargeur;
	private JTextField fixProfondeur;
	/*
	 * END-TEST
	 */

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

			/*
			 * Tout les listener du Paneau ^^
			 */
			this.setFocusable(true);

			this.addKeyListener(new KeyListener() {

				public void keyTyped(KeyEvent e) {
					
				}

				public void keyReleased(KeyEvent e) {
					control = false;
				}

				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_CONTROL)
						control = true;
					if (e.getKeyCode() == KeyEvent.VK_A && control){
						for(Face f : m.getFace())
							f.setSelected(true);
						repaint();
					}
				}
			});

			this.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					if (MouseEvent.BUTTON1 == arg0.getButton()) {
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
				}

				public void mouseEntered(MouseEvent arg0) {
					requestFocus();
				}

				public void mouseExited(MouseEvent arg0) {
				}

				public void mousePressed(MouseEvent arg0) {
				}

				/*
				 * Clic Droit Pop-Up Menu
				 */
				public void mouseReleased(MouseEvent arg0) {
					if (arg0.getButton() == MouseEvent.BUTTON3) {
						popMenu.setLocation(arg0.getLocationOnScreen());
						popMenu.setVisible(true);
					} else {
						popMenu.setVisible(false);
					}
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
					if (SwingUtilities.isLeftMouseButton(e) && m.vue == 0) {
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
			initPopMenu();
		}
		m.trieFace();
		repaint();
	}

	/*
	 * permet de separer la création du popup du reste du constructeur pour plus
	 * de visibilité
	 */
	private void initPopMenu() {
		// Init composant
		popMenu = new JPopupMenu("Menu");
		low = new JRadioButtonMenuItem("low");
		medium = new JRadioButtonMenuItem("medium");
		high = new JRadioButtonMenuItem("high");
		boutonCentre = new JMenuItem("Recentre");
		zoomPlus = new JMenuItem("Zoom +");
		zoomMoins = new JMenuItem("Zoom -");
		this.fixerDimmension = new JMenuItem("fixer une dimension");
		
		boutonCentre.addMouseListener(this);
		popMenu.add(boutonCentre);
		boutonCentre.addMouseListener(this);

		// Ajoute les changemet de qualité
		popMenu.addSeparator();
		ButtonGroup bg = new ButtonGroup();
		low.setSelected(true);
		bg.add(low);
		bg.add(medium);
		bg.add(high);
		popMenu.add(low);
		popMenu.add(medium);
		popMenu.add(high);
		low.addMouseListener(this);
		medium.addMouseListener(this);
		high.addMouseListener(this);
		
		//Zoom + | Zoom -
		popMenu.addSeparator();
		popMenu.add(zoomPlus);
		popMenu.add(zoomMoins);
		zoomPlus.addMouseListener(this);
		zoomMoins.addMouseListener(this);
		
		//Fixer Dimension
		popMenu.addSeparator();
		popMenu.add(fixerDimmension);
		fixerDimmension.addMouseListener(this);
		dimensionFrame.setTitle("Fixer Dimmension");
		dimensionFrame.getContentPane().setLayout(new BoxLayout(dimensionFrame.getContentPane(), BoxLayout.PAGE_AXIS));
		fixHauteur = new JTextField("0.0");
		fixLargeur = new JTextField("0.0");
		fixProfondeur = new JTextField("0.0");
		JButton reset = new JButton("reset");
		dimensionFrame.getContentPane().add(new JLabel("Hauteur"));
		dimensionFrame.getContentPane().add(fixHauteur);
		dimensionFrame.getContentPane().add(new JLabel("Largeur"));
		dimensionFrame.getContentPane().add(fixLargeur);
		dimensionFrame.getContentPane().add(new JLabel("Profondeur"));
		dimensionFrame.getContentPane().add(fixProfondeur);
		dimensionFrame.getContentPane().add(reset);
		
		fixHauteur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					double i = Double.parseDouble(fixHauteur.getText());
					m.setHauteurFixed(i);
				} catch (Exception e){
					fixHauteur.setText(fixHauteur.getText().substring(0, fixHauteur.getText().length()-1));
				}finally{
					fixLargeur.setText("0.0");
					fixProfondeur.setText("0.0");
					repaint();
				}
			}
		});
		fixLargeur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					double i = Double.parseDouble(fixLargeur.getText());
					m.setLargeurFixed(i);
				} catch (Exception e){
					fixLargeur.setText(fixLargeur.getText().substring(0, fixLargeur.getText().length()-1));
				}finally{
					fixHauteur.setText("0.0");
					fixProfondeur.setText("0.0");
					repaint();
				}
				
			}
		});
		fixProfondeur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					double i = Double.parseDouble(fixProfondeur.getText());
					m.setProfondeurFixed(i);
				} catch (Exception e){
					fixProfondeur.setText(fixProfondeur.getText().substring(0, fixProfondeur.getText().length()-1));
				}finally{
					fixHauteur.setText("0.0");
					fixLargeur.setText("0.0");
					repaint();
				}	
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fixHauteur.setText("0.0");
				fixLargeur.setText("0.0");
				fixProfondeur.setText("0.0");
				m.resetFixedValue();
				repaint();
			}
		});
		dimensionFrame.setAlwaysOnTop(true);
		dimensionFrame.pack();
	}

	public void disableVisibilityDimensionfix(){
		this.dimensionFrame.setVisible(false);
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
		} else if (qualite == 1) {
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
				g.drawRect((int) (p.x + m.xTranslate),
						(int) (p.y + m.yTranslate), 1, 1);
		}
		if (!getBarreVerticale().isBb2()) {
			g.setColor(Color.BLACK);
			g.drawString("Hauteur : " + m.getHauteurModel(), 0, 15);
			g.drawString("Largeur : " + m.getLargeurModel(), 0, 30);
			g.drawString("Profondeur : " + m.getProfondeurModel(), 0, 45);
			g.setColor(Color.RED);
			g.drawString("Clic droit pour plus d'options", 0, 60);
		}
		g.dispose();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(boutonCentre)) {
			if (dp.getBarreVerticale().isBb1()
					|| dp.getBarreVerticale().isModeEdit()) {
				this.dp.getModel().zoomAuto();
			} else {
				this.dp.getModel().zoomAuto();
			}
			this.dp.repaint();
		} else if (e.getSource().equals(low)) {
			this.dp.getPanel().setQualite(0);
		} else if (e.getSource().equals(medium)) {
			this.dp.getPanel().setQualite(1);
		} else if (e.getSource().equals(high)) {
			this.dp.getPanel().setQualite(2);
		} else if (e.getSource().equals(zoomPlus)){
			m.zoom(1.2);
			repaint();
		} else if (e.getSource().equals(zoomMoins)){
			m.zoom(0.8);
			repaint();
		} else if (e.getSource().equals(fixerDimmension)){
			dimensionFrame.setVisible(true);
		}

		popMenu.setVisible(false);
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(boutonCentre)) {
			boutonCentre.setArmed(true);
		} else if (e.getSource().equals(low)) {
			low.setArmed(true);
		} else if (e.getSource().equals(medium)) {
			medium.setArmed(true);
		} else if (e.getSource().equals(high)) {
			high.setArmed(true);
		} else if (e.getSource().equals(zoomPlus)){
			zoomPlus.setArmed(true);
		} else if (e.getSource().equals(zoomMoins)){
			zoomMoins.setArmed(true);
		} else if (e.getSource().equals(fixerDimmension)){
			fixerDimmension.setArmed(true);
		}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(boutonCentre)) {
			boutonCentre.setArmed(false);
		} else if (e.getSource().equals(low)) {
			low.setArmed(false);
		} else if (e.getSource().equals(medium)) {
			medium.setArmed(false);
		} else if (e.getSource().equals(high)) {
			high.setArmed(false);
		} else if (e.getSource().equals(zoomPlus)){
			zoomPlus.setArmed(false);
		} else if (e.getSource().equals(zoomMoins)){
			zoomMoins.setArmed(false);
		} else if (e.getSource().equals(fixerDimmension)){
			fixerDimmension.setArmed(false);
		}
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

}