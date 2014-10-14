package fr.model;

import java.awt.Color;
import java.awt.Graphics;

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
		return p1+" | " +p2 + " | "+p3;
	}

	public Color getColor(){
		return this.color;
	}

	public void setColor(Color color){
		this.color=color;
	}

	@Override
	public void paint(Graphics g){
		g.setColor(this.color);
		g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
		g.drawLine((int)p1.x, (int)p1.y, (int)p3.x, (int)p3.y);
		g.drawLine((int)p3.x, (int)p3.y, (int)p2.x, (int)p2.y);
	}

	/**
	 * permet de trier avec Collections.sort()
	 */
	public int compareTo(Face f) {
		if ((this.p3.z + this.p2.z + this.p1.z)/3 <f.p3.z + f.p2.z + f.p1.z){
			return -1;
		}
		else if ((this.p3.z + this.p2.z + this.p1.z)/3  > f.p3.z + f.p2.z + f.p1.z){
			return 1;
		}
		return 0;
	}
}
