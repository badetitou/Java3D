package fr.model;
/**
 * @author Loïc
 * Cette classe ne sert qu'à stocker temporairement les segments du fichier de l'objet.
 */
public class CouplePoint {
	private final Point p1;
	private final Point p2;

	public CouplePoint(Point p1,Point p2){
		this.p1=p1;
		this.p2=p2;
	}

	public Point getP1(){
		return this.p1;
	}

	public Point getP2(){
		return this.p2;
	}

	@Override
	public String toString(){
		return p1+" " +p2;
	}
}
