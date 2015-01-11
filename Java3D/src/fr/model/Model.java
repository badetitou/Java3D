package fr.model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Benoit et oui
 * 
 */
public class Model {
	private final ReadText rt;
	public int vue;
	public double xTranslate = 0;
	public double yTranslate = 0;
	private Dimension d;
	private double hauteurFixed = 0.0;
	private double largeurFixed = 0.0;
	private double profondeurFixed = 0.0;

	public double getHauteurModel() {
		if (hauteurFixed != 0.0) {
			return hauteurFixed;
		} else if (largeurFixed != 0.0) {
			return (getMaxY() + Math.abs(getMinY())) * rapportLargeurModel();
		} else if (profondeurFixed != 0.0) {
			return (getMaxY() + Math.abs(getMinY())) * rapportProfondeurModel();
		}
		return getMaxY() + Math.abs(getMinY());
	}

	public double getLargeurModel() {
		if (largeurFixed != 0.0) {
			return largeurFixed;
		} else if (hauteurFixed != 0.0) {
			return (getMaxX() + Math.abs(getMinX())) * rapportHauteurModel();
		} else if (profondeurFixed != 0.0) {
			return (getMaxX() + Math.abs(getMinX())) * rapportProfondeurModel();
		}
		return getMaxX() + Math.abs(getMinX());
	}

	public double getProfondeurModel() {
		if (profondeurFixed != 0.0) {
			return profondeurFixed;
		} else if (hauteurFixed != 0.0) {
			return (getMaxZ() + Math.abs(getMinZ())) * rapportHauteurModel();
		} else if (largeurFixed != 0.0) {
			return (getMaxZ() + Math.abs(getMinZ())) * rapportLargeurModel();
		}
		return getMaxZ() + Math.abs(getMinZ());
	}

	private double rapportHauteurModel() {
		return hauteurFixed / (getMaxY() + Math.abs(getMinY()));
	}

	private double rapportLargeurModel() {
		return largeurFixed / (getMaxX() + Math.abs(getMinX()));
	}

	private double rapportProfondeurModel() {
		return profondeurFixed / (getMaxZ() + Math.abs(getMinZ()));
	}

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
		recentrer();
		zoomAuto();
		this.vue = vue;
		for (int i = 0; i < rt.getFaceList().size(); ++i) {
			rt.getFaceList().get(i).setModel(this);
		}
	}

	/**
	 * Methode qui de donner une couleur a toute les faces seledtionne
	 * 
	 * @param c
	 *            La nouvelle color
	 */
	public void changeColor(Color c) {
		for (Face f : getFace())
			if (f.isSelected())
				f.setColor(c);
	}

	/**
	 * Permet de centrer l'image pour des rotations centrees
	 */
	private void centrage() {
		double xMax = getMaxX();
		double xMin = getMinX();
		double yMax = getMaxY();
		double yMin = getMinY();
		double zMax = getMaxZ();
		double zMin = getMinY();
		for (Point p : rt.getPointList()) {
			p.x = p.x - (xMax + xMin) / 2;
			p.y = p.y - (yMax + yMin) / 2;
			p.z = p.z - (zMax + zMin) / 2;
		}
	}

	/**
	 * 
	 * @return dimension dans laquel ce trouve le model
	 */
	public Dimension getD() {
		return d;
	}

	/**
	 * 
	 * @param d
	 *            nouvelle dimension dans laquel le model ce situe
	 */
	public void setDimension(Dimension d) {
		this.d = d;
	}

	public double getHauteurFixed() {
		return hauteurFixed;
	}

	public void setHauteurFixed(double hauteurFixed) {
		if (hauteurFixed >= 0.0) {
			this.hauteurFixed = hauteurFixed;
			this.largeurFixed = 0.0;
			this.profondeurFixed = 0.0;
		}
	}

	public double getLargeurFixed() {
		return largeurFixed;
	}

	public void setLargeurFixed(double largeurFixed) {
		if (largeurFixed >= 0.0) {
			this.largeurFixed = largeurFixed;
			this.hauteurFixed = 0.0;
			this.profondeurFixed = 0.0;
		}
	}

	public double getProfondeurFixed() {
		return profondeurFixed;
	}

	public void setProfondeurFixed(double profondeurFixed) {
		if (profondeurFixed >= 0.0) {
			this.profondeurFixed = profondeurFixed;
			this.hauteurFixed = 0.0;
			this.largeurFixed = 0.0;
		}
	}

	/**
	 * centre l'image dans son panel
	 */
	public void recentrer() {
		xTranslate = getD().getWidth() / 2;
		yTranslate = getD().getHeight() / 2;
	}

	/**
	 * trie les faces grace à Comparator de face
	 */
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
		r = r / 360.0;
		double tmpX;
		double tmpZ;
		for (Point p : rt.getPointList()) {
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

		r = r / 360.0;
		double tmpY;
		double tmpZ;
		for (Point p : rt.getPointList()) {
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

	/**
	 * centre et zoom automatiquement le model dans son panel
	 */
	public void zoomAuto() {
		recentrer();
		double maxX = getMaxX();
		zoom(d.width / 2 * (maxX + 250));

		double maxY = getMaxY();
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

	/**
	 * 
	 * @return la liste des points du model
	 */
	public List<Point> getListPoint() {
		return rt.getPointList();
	}

	/**
	 * 
	 * @return la Map des segement
	 */
	public Map<Integer, CouplePoint> getSegment() {
		return rt.getSegment();
	}

	/**
	 * 
	 * @param coordMouseX
	 *            position x clique
	 * @param coordMouseY
	 *            position y clique
	 * @return la face clique sur l'ecran
	 */
	public Face getParticularFace(double coordMouseX, double coordMouseY) {
		Face f = null;
		for (int i = 0; i < rt.getFaceList().size(); ++i) {
			if (rt.getFaceList().get(i).getTriangle()
					.contains(coordMouseX, coordMouseY)) {
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

	/**
	 * Enleve tout les faces selectionnés.
	 */
	public void reinitSelected() {
		for (Face f : rt.getFaceList()) {
			f.setSelected(false);
		}
	}

	private double getMaxX() {
		double x = Integer.MIN_VALUE;
		for (Point p : rt.getPointList())
			if (p.x > x)
				x = p.x;
		return x;
	}

	private double getMinX() {
		double x = Integer.MAX_VALUE;
		for (Point p : rt.getPointList())
			if (p.x < x)
				x = p.x;
		return x;
	}

	private double getMaxY() {
		double y = Integer.MIN_VALUE;
		for (Point p : rt.getPointList())
			if (p.y > y)
				y = p.y;
		return y;
	}

	private double getMinY() {
		double y = Integer.MAX_VALUE;
		for (Point p : rt.getPointList())
			if (p.y < y)
				y = p.y;
		return y;
	}

	private double getMaxZ() {
		double z = Integer.MIN_VALUE;
		for (Point p : rt.getPointList())
			if (p.z > z)
				z = p.z;
		return z;
	}

	private double getMinZ() {
		double z = Integer.MAX_VALUE;
		for (Point p : rt.getPointList())
			if (p.z < z)
				z = p.z;
		return z;
	}

	public void resetFixedValue() {
		this.hauteurFixed = 0.0;
		this.largeurFixed = 0.0;
		this.profondeurFixed = 0.0;
	}
}
