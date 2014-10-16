package fr.model;
/**
 * @author Loïc
 */
public class Point {
	public double x;
	public double y;
	public double z;
	public double t = 1;
	public Point(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}

	public void multiplier(double[][] a){
		x = x*a[0][0] + y*a[0][1] + z*a[0][2] + t*a[0][3];
		y = x*a[1][0] + y*a[1][1] + z*a[1][2] + t*a[1][3];
		z = x*a[2][0] + y*a[2][1] + z*a[2][2] + t*a[2][3];
		t = x*a[3][0] + y*a[3][1] + z*a[3][2] + t*a[3][3];
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
