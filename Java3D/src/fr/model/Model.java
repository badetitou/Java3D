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
	 *            est le chemin vers le fichier à étudier
	 */
	public Model(String url, int vue, Dimension d) {
		rt = new ReadText(url);
		trieFace();
		this.zoomAuto(d);
		this.vue = vue;

		for (int i = 0; i < rt.getFaceList().size(); ++i) {
			rt.getFaceList().get(i).setModel(this);
		}
		this.d = d;
		centrage();
	}
	
	private void centrage(){
		//TO DO centre le point x de l'image;
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
	 *            est la valeur de la rotation à faire en X
	 */
	public void rotationX(int r) {

		int sensRotation = 1;
		if (r < 0) {
			r = -r;
			sensRotation = -1;
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
	 *            est la valeur en radiant de la rotation à faire en Y
	 */
	public void rotationY(int r) {
		int sensRotation = 1;
		if (r < 0) {
			r = -r;
			sensRotation = -1;
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
	 *            deplace sur l'axe z ( ne sert à rien dans ce programme)
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
