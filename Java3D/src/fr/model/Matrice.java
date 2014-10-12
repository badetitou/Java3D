package fr.model;

import Jama.Matrix;

public class Matrice {
	private Matrix points;

	public Matrice(double[][] ensemblePoints) {
		points = new Matrix(ensemblePoints);
	}

	/**
	 * 
	 * @return la matrice avec les points
	 */
	public Matrix getMatrice() {
		return points;
	}

	/**
	 * 
	 * @author Benoit
	 * @param rotation
	 * @return la nouvelle matrice après rotation sur l'axe x de rotation
	 */
	public Matrix rotationX(double rotation) {
		Matrix c = new Matrix(points.getColumnDimension(),
				points.getRowDimension() + 1);
		for (int i = 0; i < points.getColumnDimension(); ++i) {
			for (int j = 0; j < points.getRowDimension(); ++j) {
				c.set(i, j, points.get(i, j));
			}
		}
		for (int i = 0; i < points.getColumnDimension(); ++i) {
			for (int j = 0; j < points.getRowDimension() + 1; ++j) {
				c.set(i, j, 1);
			}
		}
		double[][] tableauRotation = { { 1, 0, 0, 0 },
				{ 0, Math.cos(rotation), -Math.sin(rotation), 0 },
				{ 0, Math.sin(rotation), Math.cos(rotation), 0 },
				{ 0, 0, 0, 1 } };
		Matrix matriceRotation = new Matrix(tableauRotation);
		c.arrayTimesEquals(matriceRotation);
		
		for (int i = 0; i < points.getColumnDimension(); ++i) {
			for (int j = 0; j < points.getRowDimension(); ++j) {
				points.set(i, j, c.get(i, j));
			}
		}
		return points;
	}

	/**
	 * 
	 * @author Benoit
	 * @param rotation
	 * @return la nouvelle matrice après rotation sur l'axe y de rotation
	 */
	public Matrix rotationY(double rotation) {
		Matrix c = new Matrix(points.getColumnDimension(),
				points.getRowDimension() + 1);
		for (int i = 0; i < points.getColumnDimension(); ++i) {
			for (int j = 0; j < points.getRowDimension(); ++j) {
				c.set(i, j, points.get(i, j));
			}
		}
		for (int i = 0; i < points.getColumnDimension(); ++i) {
			for (int j = 0; j < points.getRowDimension() + 1; ++j) {
				c.set(i, j, 1);
			}
		}
		double[][] tableauRotation = { 
				{ Math.cos(rotation), 0, Math.sin(rotation), 0 },
				{ 0, 1, 0, 0 },
				{ -Math.sin(rotation), 0, Math.cos(rotation), 0 },
				{ 0, 0, 0, 1 } };
		Matrix matriceRotation = new Matrix(tableauRotation);
		
		c.arrayTimesEquals(matriceRotation);
		for (int i = 0; i < points.getColumnDimension(); ++i) {
			for (int j = 0; j < points.getRowDimension(); ++j) {
				points.set(i, j, c.get(i, j));
			}
		}
		return points;
	}
	
	/**
	 * 
	 * @author Benoit
	 * @param rotation
	 * @return la nouvelle matrice après rotation sur l'axe z de rotation
	 */
	public Matrix rotationZ(double rotation) {
		Matrix c = new Matrix(points.getColumnDimension(),
				points.getRowDimension() + 1);
		for (int i = 0; i < points.getColumnDimension(); ++i) {
			for (int j = 0; j < points.getRowDimension(); ++j) {
				c.set(i, j, points.get(i, j));
			}
		}
		for (int i = 0; i < points.getColumnDimension(); ++i) {
			for (int j = 0; j < points.getRowDimension() + 1; ++j) {
				c.set(i, j, 1);
			}
		}
		double[][] tableauRotation = { 
				{ Math.cos(rotation), -Math.sin(rotation),0, 0 },
				{ Math.sin(rotation), Math.cos(rotation), 0, 0 },
				{ 0, 0, 1, 0 },
				{ 0, 0, 0, 1 } };
		Matrix matriceRotation = new Matrix(tableauRotation);

		c.arrayTimesEquals(matriceRotation);
		for (int i = 0; i < points.getColumnDimension(); ++i) {
			for (int j = 0; j < points.getRowDimension(); ++j) {
				points.set(i, j, c.get(i, j));
			}
		}
		return points;
	}

	
}
