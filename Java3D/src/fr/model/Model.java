package fr.model;

/**
 * 
 * @author Benoit
 *
 */
public class Model {
	ReadText rt;

	public Model(String url) {
		rt = new ReadText(url);
	}

	public void rotationX(double r) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList().get(i).multiplier(new double[][] { 
					{ 1, 0, 0 },
					{ 0, Math.cos(r), -Math.sin(r) },
					{ 0, Math.sin(r), Math.cos(r) } });
		}
	}

	public void rotationY(double r) {
		for (int i = 0; i < rt.getPointList().size(); ++i) {
			rt.getPointList().get(i).multiplier(new double[][] { 
					{ Math.cos(r), 0, Math.sin(r) },
					{ 0, 1, 0 },
					{ -Math.sin(r), 0, Math.cos(r) } });
		}
	}

}
