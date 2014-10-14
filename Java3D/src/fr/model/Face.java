package fr.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

/**
 * @author Loïc
 */
@SuppressWarnings("serial")
public class Face extends JPanel implements Comparable<Face>{

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

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.color);
		/*
		int[] x = { (int)p1.x, (int)p2.x, (int)p3.x };
		int[] y = { (int)p1.y, (int)p2.y, (int)p3.y };//draw with polygon
		Polygon triangle = new Polygon(x, y, 3);
		g2.drawPolygon(triangle);
		*/
		GeneralPath p0 = new GeneralPath();
		p0.moveTo(p1.x, p1.y);
	    p0.lineTo(p2.x, p2.y);
	    p0.lineTo(p3.x, p3.y);
	    p0.lineTo(p1.x, p1.y);
	    g2.draw(p0);
		
		/*
		g.drawLine((int)p1.x*10, (int)p1.y*10, (int)p2.x*10, (int)p2.y*10);
		g.drawLine((int)p1.x*10, (int)p1.y*10, (int)p3.x*10, (int)p3.y*10);
		g.drawLine((int)p3.x*10, (int)p3.y*10, (int)p2.x*10, (int)p2.y*10);
		*/
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
}
