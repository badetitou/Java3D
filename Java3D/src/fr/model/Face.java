package fr.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import fr.view.Panneau;

/**
 * @author Loïc
 */
public class Face implements Comparable<Face> {

	private final Point p1;
	private final Point p2;
	private final Point p3;
	private Color color;
	private Color currentColor;
	private Model mod;
	
	
	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public Point getP3() {
		return p3;
	}

	public Face(Point p1, Point p2, Point p3,Color c) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		if(c.getRed()==0 && c.getGreen()==0 &&c.getBlue()==0)
			this.color=new Color(255,255,255);
		else
			this.color=c;
		calculLumiere();
	}

	public void setModel(Model m){
		this.mod=m;
	}

	public void setSelected(boolean b){
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
		g2.setColor(this.currentColor);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		calculLumiere();
		GeneralPath p0 = new GeneralPath();
		p0.moveTo(p1.x + mod.getD().getWidth() / 2 + mod.xTranslate,
				-p1.y + mod.getD().getHeight() / 2 + mod.yTranslate);
		p0.lineTo(p2.x + mod.getD().getWidth() / 2 + mod.xTranslate,
				-p2.y + mod.getD().getHeight() / 2 + mod.yTranslate);
		p0.lineTo(p3.x + mod.getD().getWidth() / 2 + mod.xTranslate,
				-p3.y + mod.getD().getHeight() / 2 + mod.yTranslate);
		p0.moveTo(p2.x + mod.getD().getWidth() / 2 + mod.xTranslate,
				-p2.y + mod.getD().getHeight() / 2 + mod.yTranslate);
		p0.lineTo(p1.x + mod.getD().getWidth() / 2 + mod.xTranslate,
				-p1.y + mod.getD().getHeight() / 2 + mod.yTranslate);
		p0.closePath();
		g2.fill(p0);
	}

	private void calculLumiere(){

		Point N = new Point((p2.y - p1.y) * (p3.z - p1.z) - (p2.z - p1.z)
				* (p3.y - p1.y), (p2.z - p1.z) * (p3.x - p1.x) - (p2.x - p1.x)
				* (p3.z - p1.z), (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y)
				* (p3.x - p1.x));

		Point L = new Point(0, 0, 1);
		Point G = new Point((p1.x+p1.y+p1.z)/3,(p2.x+p2.y+p2.z)/3,(p2.x+p2.y+p2.z)/3);

		double scal = N.x * L.x - N.y * L.y + N.z * L.z;
		double amplN = Math.pow(N.x, 2) + Math.pow(N.y, 2) + Math.pow(N.z, 2);
		double amplL = Math.pow(L.x, 2) + Math.pow(L.y, 2) + Math.pow(L.z, 2);
		double cosValue = scal / ((Math.sqrt(amplL)) * (Math.sqrt(amplN)));
		int cosPositifR = (int) (this.color.getRed() * cosValue);
		int cosPositifV = (int) (this.color.getGreen() * cosValue);
		int cosPositifB = (int) (this.color.getBlue() * cosValue);

		if (cosPositifR < 0)
			cosPositifR = -cosPositifR;
		if (cosPositifV < 0)
			cosPositifV = -cosPositifV;
		if (cosPositifB < 0)
			cosPositifB = -cosPositifB;
		this.currentColor = (new Color((cosPositifR), (cosPositifV),
				(cosPositifB)));

	}
}
