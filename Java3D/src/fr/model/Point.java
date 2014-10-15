package fr.model;
/**
 * @author Loïc
 */
public class Point {
	public double x;
	public double y;
	public double z;
	public Point(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}

	public void multiplier(double[][] a){
		x = x*a[0][0] + x*a[0][1] + x*a[0][2];
		y = y*a[1][0] + y*a[1][1] + y*a[1][2];
		z = z*a[2][0] + z*a[2][1] + z*a[2][2];
	}

	public boolean equals(Point p){
		if(p.x == this.x && p.y==this.y && p.z==z){
			return true;
		}
		return false;
	}

	@Override
	public String toString(){
		return x+" "+y+" "+ " "+ z;
	}
}
