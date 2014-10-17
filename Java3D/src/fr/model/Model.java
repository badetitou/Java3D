package fr.model;

import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Benoit
 *
 */
public class Model {
	private final ReadText rt;

	/**
	 * 
	 * @param url
	 *            est le chemin vers le fichier � �tudier
	 */
	public Model(String url) {
		rt = new ReadText(url);
		this.zoom(5);
		trieFace();
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
	 *            est la valeur en radiant de la rotation � faire en X
	 */
	public void rotationZ(int r) {
		int sensRotation = 1;
		if (r < 0) {
			r = -r;
			sensRotation = -1;
		}
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			for (int j = 0; i < r; ++j) {
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

	public void translation(double x, double y, double z) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList()
					.get(i)
					.multiplier(
							new double[][] { { 1, 0, 0, x }, { 0, 1, 0, y },
									{ 0, 0, 1, z }, { 0, 0, 0, 1 } });
		}
		trieFace();
	}

	/**
	 * 
	 * @param r
	 *            est la valeur de la rotation � faire en Y
	 */
	public void rotationX(int r) {
		int sensRotation = 1;
		if (r < 0){
			r = -r;
			sensRotation = -1;
		}
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			for (int j = 0;j<r; ++j){
				rt.getPointList()
				.get(i)
				.multiplier(
						new double[][] {
								{ Math.cos(sensRotation * Math.PI / 1024), 0,
									Math.sin(sensRotation * Math.PI / 1024), 0 },
									{ 0, 1, 0, 0 },
									{ -Math.sin(sensRotation * Math.PI / 1024), 0,
										Math.cos(sensRotation * Math.PI / 1024), 0 },
										{ 0, 0, 0, 1 } });
			}
		}
		trieFace();
	}

	/**
	 * Je sais aps si �a va servir mais dans le doute maintenant on l'a
	 * 
	 * @param r
	 *            est la valeur de la rotation � faire en Z
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
