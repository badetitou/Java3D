package fr.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import fr.model.Face;
import fr.model.Model;
import fr.model.Point;

public class Panneau extends JPanel {
	Model m;

	public Panneau(Model m) {
		this.m = m;
		m.zoom(0.1);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < m.getFace().size(); i++) {
			m.getFace().get(i).paint(g);
		}
		System.out.print(Face.i);
	}
}
