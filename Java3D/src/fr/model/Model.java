package fr.model;

import java.util.Collections;
import java.util.List;

import fr.view.BarreVerticale;
import fr.view.MyDeskTopPane;

/**
 * 
 * @author Benoit
 *
 */
public class Model {
	private final ReadText rt;
	public int vue;

	/**
	 * 
	 * @param url
	 *            est le chemin vers le fichier à étudier
	 */
	public Model(String url, int vue) {
		rt = new ReadText(url);
		trieFace();
		this.vue = vue;
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
	public void translation(double x, double y, double z) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList()
					.get(i)
					.multiplier(
							new double[][] { { 1, 0, 0, x }, { 0, 1, 0, -y },
									{ 0, 0, 1, z }, { 0, 0, 0, 1 } });
		}
		trieFace();
	}

	public void zoomAuto() {
		double maxX = 0;
		double maxY = 0;
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			if (rt.getPointList().get(i).x > maxX){
				maxX = rt.getPointList().get(i).x;
			}
		}
		if (BarreVerticale.bb1){
			zoom(MyDeskTopPane.dimension.getWidth()/maxX);
		}
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

}
