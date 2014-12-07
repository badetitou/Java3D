package fr.model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Benoit
 * et oui
 * 
 */
public class Model {
	private final ReadText rt;
	public int vue;
	public double xTranslate = 0;
	public double yTranslate = 0;
	private Dimension d;

	/**
	 * 
	 * @param url
	 *            est le chemin vers le fichier � �tudier
	 */
	public Model(String url, int vue, Dimension d) {
		rt = new ReadText(url);
		this.d = d;
		centrage();
		trieFace();
		this.zoomAuto();
		this.vue = vue;
		for (int i = 0; i < rt.getFaceList().size(); ++i) {
			rt.getFaceList().get(i).setModel(this);
		}
	}
	
	public void changeColor(Color c){
		for(Face f : getFace())
			if (f.isSelected())
				f.setColor(c);
	}

	private void centrage(){
		double xMax = 0;
		double xMin = 0;
		double yMax = 0;
		double yMin = 0;
		double zMax = 0;
		double zMin = 0;
		for (int i = 0; i<rt.getPointList().size(); ++i){
			if (rt.getPointList().get(i).x > xMax)
				xMax = rt.getPointList().get(i).x;
			else if (rt.getPointList().get(i).x < xMin)
				xMin = rt.getPointList().get(i).x;
			if (rt.getPointList().get(i).y > yMax)
				yMax = rt.getPointList().get(i).y;
			else if (rt.getPointList().get(i).y < yMin)
				yMin = rt.getPointList().get(i).y;
			if (rt.getPointList().get(i).z > zMax)
				zMax = rt.getPointList().get(i).z;
			else if (rt.getPointList().get(i).z < zMin)
				zMin = rt.getPointList().get(i).z;
		}
		for(int i = 0;i < rt.getPointList().size();++i){
			rt.getPointList().get(i).x = rt.getPointList().get(i).x - ((xMax + xMin)/2);
			rt.getPointList().get(i).y = rt.getPointList().get(i).y - ((yMax + yMin)/2);
			rt.getPointList().get(i).z = rt.getPointList().get(i).z - ((zMax + zMin)/2);
		}
	}

	public Dimension getD() {
		return d;
	}
	
	public void setDimension(Dimension d){
		this.d = d;
	}

	public void recentrer() {
		xTranslate =  getD().getWidth() / 2;
		yTranslate =  getD().getHeight() / 2;
	}

	public void trieFace() {
		Collections.sort(rt.getFaceList());
	}

	/**
	 * 
	 * @return la liste des Faces
	 */
	public List<Face> getFace() {
		return rt.getFaceList();
	}

	/**
	 * 
	 * @param r
	 *            est la valeur de la rotation � faire en X
	 */
	public void rotationX(double r) {
		r = r/360.0;
		double tmpX;
		double tmpZ;
		for (Point p : rt.getPointList()){
			tmpX = p.x;
			tmpZ = p.z;
			p.x = tmpX * Math.cos(r) + tmpZ * Math.sin(r);
			p.z = tmpZ * Math.cos(r) - tmpX * Math.sin(r);
		}
	}

	/**
	 * 
	 * @param r
	 *            est la valeur en radiant de la rotation � faire en Y
	 */
	public void rotationY(double r) {
		
		r = r/360.0;
		double tmpY;
		double tmpZ;
		for (Point p : rt.getPointList()){
			tmpY = p.y;
			tmpZ = p.z;
			p.y = tmpY * Math.cos(r) - tmpZ * Math.sin(r);
			p.z = tmpZ * Math.cos(r) + tmpY * Math.sin(r);
		}
	}

	/**
	 * Permet de deplace une figure
	 * 
	 * @param x
	 *            deplace sur l'axe x
	 * @param y
	 *            deplace sur l'axe y
	 * @param z
	 *            deplace sur l'axe z ( ne sert � rien dans ce programme)
	 */
	public void translation(double x, double y) {
		xTranslate += x;
		yTranslate += y;
	}

	public void zoomAuto() {
		recentrer();
		double maxX = 0.0;
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			if (Math.abs(rt.getPointList().get(i).x) > maxX) {
				maxX = Math.abs(rt.getPointList().get(i).x);
			}
		}
		zoom((d.getWidth() / 2 - 20) / maxX);

		double maxY = 0.0;
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			if (Math.abs(rt.getPointList().get(i).y) > maxY) {
				maxY = Math.abs(rt.getPointList().get(i).y);
			}
		}
		if (maxY > d.getHeight() / 2 - 50)
			zoom((d.getHeight() / 2 - 50) / maxY);
	}

	/**
	 * Double pour plus de precision possible
	 * 
	 * @param k
	 *            et la force du zoom
	 */
	public void zoom(double k) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList().get(i).x *= k;
			rt.getPointList().get(i).y *= k;
			rt.getPointList().get(i).z *= k;
		}
	}

	public Face getParticularFace(double coordMouseX, double coordMouseY) {
		Face f = null;
		for (int i = 0; i < rt.getFaceList().size(); ++i) {
			if (rt.getFaceList().get(i).getTriangle().contains(coordMouseX, coordMouseY)) {
				if (f != null) {
					if (rt.getFaceList().get(i).getP1().z
							+ rt.getFaceList().get(i).getP2().z
							+ rt.getFaceList().get(i).getP3().z > f.getP1().z
							+ f.getP2().z + f.getP3().z) {
						f = rt.getFaceList().get(i);
					}
				} else {
					f = rt.getFaceList().get(i);
				}
			}

		}
		return f;
	}
}
