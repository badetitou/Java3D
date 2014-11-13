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
	}

	public Dimension getD(){
		return d;
	}

	public void setD(Dimension d){
		this.d=d;
	}

	public void recentrer() {
		xTranslate = 0;
		yTranslate = 0;
	}

	private void trieFace() {
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

				rt.getPointList()
				.get(i)
				.multiplier(
						new double[][] {
								{ 1, 0, 0, 0 },
								{
									0,
									Math.cos(sensRotation * Math.PI
											/ 1024),
											-Math.sin(sensRotation
													* Math.PI / 1024), 0 },
													{
														0,
														Math.sin(sensRotation * Math.PI
																/ 1024),
																Math.cos(sensRotation * Math.PI
																		/ 1024), 0 },
																		{ 0, 0, 0, 1 } });
			}
		}
		trieFace();
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
				rt.getPointList()
				.get(i)
				.multiplier(
						new double[][] {
								{
									Math.cos(sensRotation * Math.PI
											/ 1024),
											0,
											Math.sin(sensRotation * Math.PI
													/ 1024), 0 },
													{ 0, 1, 0, 0 },
													{
														-Math.sin(sensRotation
																* Math.PI / 1024),
																0,
																Math.cos(sensRotation * Math.PI
																		/ 1024), 0 },
																		{ 0, 0, 0, 1 } });
			}
		}
		trieFace();
	}

	/**
	 * Je sais aps si ça va servir mais dans le doute maintenant on l'a
	 * 
	 * @param r
	 *            est la valeur de la rotation à faire en Z
	 */
	public void rotationZ(int r) {
		int sensRotation = 1;
		if (r < 0) {
			r = -r;
			sensRotation = -1;

		}
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			for (int j = 0; j < r; ++j) {
				rt.getPointList()
				.get(i)
				.multiplier(
						new double[][] {
								{
									Math.cos(sensRotation * Math.PI
											/ 1024),
											-Math.sin(sensRotation
													* Math.PI / 1024), 0, 0 },
													{
														Math.sin(sensRotation * Math.PI
																/ 1024),
																Math.cos(sensRotation * Math.PI
																		/ 1024), 0, 0 },
																		{ 0, 0, 0, 0 }, { 0, 0, 0, 1 } });
			}
		}
		trieFace();
	}

	/**
	 * Double pour plus de precision possible
	 * 
	 * @param k
	 *            et la force du zoom
	 */
	public void zoom(double k) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList()
			.get(i)
			.multiplier(
					new double[][] { { k, 0, 0, 0 }, { 0, k, 0, 0 },
							{ 0, 0, k, 0 }, { 0, 0, 0, 1 } });
		}
	}

	public Face getParticularFace(int coordMouseX, int coordMouseY) {
		Face f = null;
		for (int i = 0; i < rt.getFaceList().size(); ++i) {
			// TODO badetitou récuperer face la plus haute avec les coord
		}
		return f;
	}
}
