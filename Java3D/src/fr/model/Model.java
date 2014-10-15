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
	 *            est le chemin vers le fichier à étudier
	 */
	public Model(String url) {
		rt = new ReadText(url);
		this.zoom(25);
		trieFace();
	}

	private void trieFace() {
		Collections.sort(rt.getFaceList());
		System.out.println("Trié");
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
	 *            est la valeur en radiant de la rotation à faire en X
	 */
	public void rotationX(double r) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList()
					.get(i)
					.multiplier(
							new double[][] { { 1, 0, 0, 0 },
									{ 0, Math.cos(r), -Math.sin(r), 0 },
									{ 0, Math.sin(r), Math.cos(r), 0 },
									{ 0, 0, 0, 1 } });
		}
		trieFace();
	}

	/**
	 * 
	 * @param r
	 *            est la valeur de la rotation à faire en Y
	 */
	public void rotationY(double r) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList()
					.get(i)
					.multiplier(
							new double[][] { { Math.cos(r), 0, Math.sin(r) , 0},
									{ 0, 1, 0 , 0},
									{ -Math.sin(r), 0, Math.cos(r), 0 } ,
									{0,0,0,1}});
		}
		trieFace();
	}

	/**
	 * Je sais aps si ça va servir mais dans le doute maintenant on l'a
	 * 
	 * @param r
	 *            est la valeur de la rotation à faire en Z
	 */
	public void rotationZ(double r) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList()
					.get(i)
					.multiplier(
							new double[][] { { Math.cos(r), -Math.sin(r), 0, 0 },
									{ Math.sin(r), Math.cos(r), 0, 0 },
									{ 0, 0, 0, 0 } ,
									{0,0,0,1}});
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
