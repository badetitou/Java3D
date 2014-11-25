package fr.model;

import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Benoit
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
		centrage();
		trieFace();
		this.zoomAuto(d);
		this.vue = vue;

		for (int i = 0; i < rt.getFaceList().size(); ++i) {
			rt.getFaceList().get(i).setModel(this);
		}
		this.d = d;
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

	public void setD(Dimension d) {
		this.d = d;
	}

	public void recentrer() {
		xTranslate = 0;
		yTranslate = 0;
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
	public void rotationX(int r) {

		double sensRotation = 1.0;
		if (r < 0) {
			r = -r;
			sensRotation = -1.0;
		}

		for (int i = 0; i < rt.getPointList().size(); ++i) {
			for (int j = 0; j < r; ++j) {
				rt.getPointList().get(i).x = rt.getPointList().get(i).x
						* Math.cos(sensRotation * Math.PI / 1024.0)
						+ rt.getPointList().get(i).z
						* Math.sin(sensRotation * Math.PI / 1024.0);
				rt.getPointList().get(i).z = rt.getPointList().get(i).x
						* -Math.sin(sensRotation * Math.PI / 1024.0)
						+ rt.getPointList().get(i).z
						* Math.cos(sensRotation * Math.PI / 1024.0);
			}
		}

	}

	/**
	 * 
	 * @param r
	 *            est la valeur en radiant de la rotation � faire en Y
	 */
	public void rotationY(int r) {
		double sensRotation = 1.0;
		if (r < 0) {
			r = -r;
			sensRotation = -1.0;
		}
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			for (int j = 0; j < r; ++j) {
				rt.getPointList().get(i).y = rt.getPointList().get(i).y
						* Math.cos(sensRotation * Math.PI / 1024.0)
						+ rt.getPointList().get(i).z
						* -Math.sin(sensRotation * Math.PI / 1024.0);
				rt.getPointList().get(i).z = rt.getPointList().get(i).y
						* Math.sin(sensRotation * Math.PI / 1024.0)
						+ rt.getPointList().get(i).z
						* Math.cos(sensRotation * Math.PI / 1024.0);
			}
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

	public void zoomAuto(Dimension d) {
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
