package fr.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.Random;

/**
 * @author Loïc
 */
@SuppressWarnings("serial")
public class Face implements Comparable<Face> {

	private final Point p1;
	private final Point p2;
	private final Point p3;
	private Color color=Color.BLUE;

	public Face(Point p1, Point p2, Point p3){
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}

	@Override
	public String toString(){
		return p1+" | " +p2 + " | "+p3 + "\n---\n";
	}

	public Color getColor(){
		return this.color;
	}

	public void setColor(Color color){
		this.color=color;
	}

	/**
	 * permet de trier avec Collections.sort()
	 */
	public int compareTo(Face f) {
		if ((this.p3.z + this.p2.z + this.p1.z)/3.0 < (f.p3.z + f.p2.z + f.p1.z)/3.0){
			return -1;
		}
		else if ((this.p3.z + this.p2.z + this.p1.z)/3.0  > (f.p3.z + f.p2.z + f.p1.z)/3.0){
			return 1;
		}
		return 0;
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		GeneralPath p0 = new GeneralPath();
		p0.moveTo(p1.x+120, -p1.y+120);
	    p0.lineTo(p2.x+120,-p2.y+120);
	    p0.lineTo(p3.x+120, -p3.y+120);
	    p0.moveTo(p2.x+120, -p2.y+120);
	    p0.lineTo(p1.x+120, -p1.y+120);
	    p0.closePath();
	    g2.fill(p0);
		
	}
}
