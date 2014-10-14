package fr.model;

import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Benoit
 *
 */
public class Model {
	private ReadText rt;

	/**
	 * 
	 * @param url est le chemin vers le fichier à étudier
	 */
	public Model(String url) {
		rt = new ReadText(url);
		trieFace();
	}
	
	private void trieFace(){
		Collections.sort(rt.getFaceList());
	}
	
	/**
	 * 
	 * @return la liste des Faces
	 */
	public List<Face> getFace(){
		return rt.getFaceList();
	}

	/**
	 * 
	 * @param r est la valeur en radiant de la rotation à faire en X
	 */
	public void rotationX(double r) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList().get(i).multiplier(new double[][] { 
					{ 1, 0, 0 },
					{ 0, Math.cos(r), -Math.sin(r) },
					{ 0, Math.sin(r), Math.cos(r) } });
		}
		trieFace();
	}

	/**
	 * 
	 * @param r est la valeur de la rotation à faire en Y
	 */
	public void rotationY(double r) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList().get(i).multiplier(new double[][] { 
					{ Math.cos(r), 0, Math.sin(r) },
					{ 0, 1, 0 },
					{ -Math.sin(r), 0, Math.cos(r) } });
		}
		trieFace();
	}
	
	/**
	 * Je sais aps si ça va servir mais dans le doute maintenant on l'a
	 * 
	 * @param r est la valeur de la rotation à faire en Z
	 */
	public void rotationZ(double r) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList().get(i).multiplier(new double[][] { 
					{ Math.cos(r), -Math.sin(r),0 },
					{ Math.sin(r), Math.cos(r), 0 },
					{ 0, 0, 0 } });
		}
		trieFace();
	}
	
	/**
	 * Double pour plus de precision possible
	 * 
	 * @param k et la force du zoom
	 */
	public void zoom(double k){
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList().get(i).multiplier(new double[][] { 
					{ k, 0, 0 },
					{ 0, k, 0 },
					{ 0, 0, k } });
		}
	}

}
