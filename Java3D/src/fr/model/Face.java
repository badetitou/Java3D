package fr.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
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
	public static int i = 0;

	public Face(Point p1, Point p2, Point p3){
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}

	@Override
	public String toString(){
		return p1+" | " +p2 + " | "+p3 ;
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
		g2.setColor(this.color);
		Random r = new Random();
		int i = r.nextInt(300);
		if(i < 50 )
			g2.setColor(Color.RED);
		else if(i < 100 )
			g2.setColor(Color.YELLOW);
		else if (i<150)
			g2.setColor(Color.DARK_GRAY);
		else if(i<200)
			g2.setColor(Color.CYAN);
		else if(i<250)
			g2.setColor(Color.GREEN);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		GeneralPath p0 = new GeneralPath();
		p0.moveTo(p1.x+400, -p1.y+400);
		p0.lineTo(p2.x+400, -p2.y+400);
		p0.lineTo(p3.x+400, -p3.y+400);
		p0.moveTo(p2.x+400, -p2.y+400);
		p0.lineTo(p1.x+400, -p1.y+400);
		p0.closePath();
		g2.fill(p0);
		/*
		int [] xCoord ={(int)p1.x+200,(int)p2.x+200,(int)p3.x+200};
		int [] yCoord ={(int)p1.y+200,(int)p2.y+200,(int)p3.y+200};
		Polygon poly = new Polygon(xCoord,yCoord,3);
		g2.fill(poly);
		*/

	}
}
