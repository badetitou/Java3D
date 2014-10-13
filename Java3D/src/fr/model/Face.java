package fr.model;

public class Face {

	private final Point p1;
	private final Point p2;
	private final Point p3;

	public Face(Point p1, Point p2, Point p3){
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}

	@Override
	public String toString(){
		return p1+" | " +p2 + " | "+p3;
	}
}
