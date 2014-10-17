package fr.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.Random;

import fr.view.Panneau;

/**
 * @author Loïc
 */
public class Face implements Comparable<Face> {

	private final Point p1;
	private final Point p2;
	private final Point p3;
	private Color color = Color.BLACK;
	public static int i = 0;

	public Face(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	@Override
	public String toString() {
		return p1 + " | " + p2 + " | " + p3;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * permet de trier avec Collections.sort()
	 */
	public int compareTo(Face f) {
		if ((this.p3.z + this.p2.z + this.p1.z) / 3.0 < (f.p3.z + f.p2.z + f.p1.z) / 3.0) {
			return -1;
		} else if ((this.p3.z + this.p2.z + this.p1.z) / 3.0 > (f.p3.z + f.p2.z + f.p1.z) / 3.0) {
			return 1;
		}
		return 0;
	}

	/**
	 * 
	 * Dessine la face selon ses points. La méthode est appelée pour chaque
	 * face.
	 */

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.color);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Point N = new Point((p2.y - p1.y) * (p3.z - p1.z) - (p2.z - p1.z) * (p3.y - p1.y),
				(p2.z - p1.z) * (p3.x - p1.x) - (p2.x - p1.x) * (p3.z - p1.z), (p2.x - p1.x)
						* (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x));
		Point L = new Point(0,0,1);
		double scal = N.x*L.x - N.y*L.y + N.z*L.z;
		double amplN = Math.pow(N.x, 2) + Math.pow(N.y, 2) + Math.pow(N.z, 2);
		double amplL = Math.pow(L.x, 2) + Math.pow(L.y, 2) + Math.pow(L.z, 2);
		double cosValue = scal/((Math.sqrt(amplL))*(Math.sqrt(amplN)));
		int cosPositif = (int) (255 * cosValue);
		if (cosPositif < 0)
			cosPositif = -cosPositif;
		this.setColor(new Color((int) (cosPositif) ,(int) (cosPositif), (int)(cosPositif)));
		
		GeneralPath p0 = new GeneralPath();
		p0.moveTo(p1.x + Panneau.d.getWidth() / 2,
				-p1.y + Panneau.d.getHeight() / 2);
		p0.lineTo(p2.x + Panneau.d.getWidth() / 2,
				-p2.y + Panneau.d.getHeight() / 2);
		p0.lineTo(p3.x + Panneau.d.getWidth() / 2,
				-p3.y + Panneau.d.getHeight() / 2);
		p0.moveTo(p2.x + Panneau.d.getWidth() / 2,
				-p2.y + Panneau.d.getHeight() / 2);
		p0.lineTo(p1.x + Panneau.d.getWidth() / 2,
				-p1.y + Panneau.d.getHeight() / 2);
		p0.closePath();
		g2.fill(p0);
	}
}
