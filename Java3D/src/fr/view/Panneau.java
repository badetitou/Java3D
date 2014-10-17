package fr.view;

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

	public Panneau(Model mod) {
		this.m = mod;
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
				m.rotationX(coordMouseX - e.getX());
				m.rotationZ(coordMouseY - e.getY());
				coordMouseX = e.getX();
				coordMouseY = e.getY();
				repaint();
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