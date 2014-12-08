package fr.model;
/**
 * @author Loïc
 */
public class Point implements Comparable<Point> {
	public double x;
	public double y;
	public double z;
	private int position; // Position du point pour l'export
	
	public Point(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public void setPosition(int i){
		this.position = i;
	}
	
	public int getPosition(){
		return position;
	}

	public int compareTo(Point p) {
		if (position < p.position){
			return -1;
		} else if (position > p.position){
			return 1;
		}else {
			return 0;
		}
	}
	
	public String toString(){
		return this.x + " " + this.y + " " +this.z;
	}
}
