package fr.model;

import java.awt.Color;
import java.awt.Polygon;

/**
 * @author Lo�c
 */
public class Face implements Comparable<Face> {

	private final int segment1;
	private final int segment2;
	private final int segment3;
	private Point p1;
	private Point p2;
	private Point p3;
	private Color color;
	private Model mod;
	private boolean selected = false;
	
	public Color getColor(){
		return color;
	}
	
	public int getSegment1() {
		return segment1;
	}

	public int getSegment2() {
		return segment2;
	}

	public int getSegment3() {
		return segment3;
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public Point getP3() {
		return p3;
	}

	public Face(double tab, double tab2, double tab3,Point p1, Point p2, Point p3, Color c) {
		
		this.segment1 = (int) tab;
		this.segment2 = (int) tab2;
		this.segment3 = (int) tab3;
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
		if ((this.getP3().z + this.getP2().z + this.getP1().z) / 3.0 < (f.getP3().z + f.getP2().z + f.getP1().z) / 3.0) {
			return -1;
			
		} else if ((this.getP3().z + this.getP2().z + this.getP1().z) / 3.0 > (f.getP3().z + f.getP2().z + f.getP1().z) / 3.0) {
			return 1;
		}
		return 0;
	}
	
	public Polygon getTriangle(){
		return new Polygon(new int[]{(int)(getP1().x + mod.xTranslate), (int)(getP2().x + mod.xTranslate), (int)(getP3().x + mod.xTranslate)}, new int[]{(int)(getP1().y + mod.yTranslate), (int)(getP2().y + mod.yTranslate), (int)(getP3().y + mod.yTranslate)}, 3);
	}

	public Color calculLumiere(){
		Point N = new Point((getP2().y - getP1().y) * (getP3().z - getP1().z) - (getP2().z - getP1().z)
				* (getP3().y - getP1().y), (getP2().z - getP1().z) * (getP3().x - getP1().x) - (getP2().x - getP1().x)
				* (getP3().z - getP1().z), (getP2().x - getP1().x) * (getP3().y - getP1().y) - (getP2().y - getP1().y)
				* (getP3().x - getP1().x));
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
				(cosPositifB), this.color.getAlpha()));
	}

	public void setColor(Color c) {
		this.color = c;
	}
}
