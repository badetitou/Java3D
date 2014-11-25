package fr.model;

import java.awt.Color;
import java.awt.Polygon;

/**
 * @author Loïc
 */
public class Face implements Comparable<Face> {

	private final Point p1;
	private final Point p2;
	private final Point p3;
	private Color color;
	private Model mod;
	private boolean selected = false;
	
	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public Point getP3() {
		return p3;
	}
	
	public Color getColor(){
		return this.color;
	}

	public Face(Point p1, Point p2, Point p3,Color c) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		if(c.getRed()==0 && c.getGreen()==0 &&c.getBlue()==0)
			this.color=new Color(255,255,255);
		else
			this.color=c;
	}

	public void setModel(Model m){
		this.mod=m;
	}

	public void setSelected(boolean b){
		this.selected = b;
	}
	
	public boolean isSelected(){
		return selected;
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
	
	public Polygon getTriangle(){
		return new Polygon(new int[]{(int)(p1.x + mod.getD().getWidth() / 2 + mod.xTranslate), (int)(p2.x + mod.getD().getWidth() / 2 + mod.xTranslate), (int)(p3.x + mod.getD().getWidth() / 2 + mod.xTranslate)}, new int[]{(int)(p1.y + mod.getD().getHeight() / 2 + mod.yTranslate), (int)(p2.y+ mod.getD().getHeight() / 2 + mod.yTranslate), (int)(p3.y+ mod.getD().getHeight() / 2 + mod.yTranslate)}, 3);
	}

	public Color calculLumiere(){
		Point N = new Point((p2.y - p1.y) * (p3.z - p1.z) - (p2.z - p1.z)
				* (p3.y - p1.y), (p2.z - p1.z) * (p3.x - p1.x) - (p2.x - p1.x)
				* (p3.z - p1.z), (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y)
				* (p3.x - p1.x));
		Point L = new Point(0, 0, 1);
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
		return (new Color((cosPositifR), (cosPositifV),
				(cosPositifB)));
	}
}
