package fr.model;

import Jama.Matrix;

public class Matrice {
	private Matrix matrice;

	public Matrice(double[][] ensemblePoints) {
		matrice = new Matrix(ensemblePoints);
	}

	/**
	 * @param x
	 * @return la matrice qui se trouve à la x collonne
	 */
	public Matrice getPoint(int x){
		double[][] mat = new double[0][matrice.getColumnDimension()];
		for(int i = 0;i<matrice.getColumnDimension();++i){
			mat[0][i] = matrice.get(x, i);
		}
		return new Matrice(mat);
	}
	
	/**
	 * @param x position x dans la matrice 
	 * @param y positon y dans la matrice
	 * @return l'entier stocker à la position x, y dans la matrice
	 */
	public double get(int x, int y){
		return matrice.get(x, y);
	}
	
	
	/**
	 * 
	 * @author Benoit
	 * @param rotation
	 * @return la nouvelle matrice après rotation sur l'axe x de rotation
	 */
	public Matrix rotationX(double rotation) {
		Matrix c = new Matrix(matrice.getColumnDimension(),
				matrice.getRowDimension() + 1);
		for (int i = 0; i < matrice.getColumnDimension(); ++i) {
			for (int j = 0; j < matrice.getRowDimension(); ++j) {
				c.set(i, j, matrice.get(i, j));
			}
		}
		for (int i = 0; i < matrice.getColumnDimension(); ++i) {
			for (int j = 0; j < matrice.getRowDimension() + 1; ++j) {
				c.set(i, j, 1);
			}
		}
		double[][] tableauRotation = { { 1, 0, 0, 0 },
				{ 0, Math.cos(rotation), -Math.sin(rotation), 0 },
				{ 0, Math.sin(rotation), Math.cos(rotation), 0 },
				{ 0, 0, 0, 1 } };
		Matrix matriceRotation = new Matrix(tableauRotation);
		c.arrayTimesEquals(matriceRotation);
		
		for (int i = 0; i < matrice.getColumnDimension(); ++i) {
			for (int j = 0; j < matrice.getRowDimension(); ++j) {
				matrice.set(i, j, c.get(i, j));
			}
		}
		return matrice;
	}

	/**
	 * 
	 * @author Benoit
	 * @param rotation
	 * @return la nouvelle matrice après rotation sur l'axe y de rotation
	 */
	public Matrix rotationY(double rotation) {
		Matrix c = new Matrix(matrice.getColumnDimension(),
				matrice.getRowDimension() + 1);
		for (int i = 0; i < matrice.getColumnDimension(); ++i) {
			for (int j = 0; j < matrice.getRowDimension(); ++j) {
				c.set(i, j, matrice.get(i, j));
			}
		}
		for (int i = 0; i < matrice.getColumnDimension(); ++i) {
			for (int j = 0; j < matrice.getRowDimension() + 1; ++j) {
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
		for (int i = 0; i < matrice.getColumnDimension(); ++i) {
			for (int j = 0; j < matrice.getRowDimension(); ++j) {
				matrice.set(i, j, c.get(i, j));
			}
		}
		return matrice;
	}
	
	/**
	 * 
	 * @author Benoit
	 * @param rotation
	 * @return la nouvelle matrice après rotation sur l'axe z de rotation
	 */
	public Matrix rotationZ(double rotation) {
		Matrix c = new Matrix(matrice.getColumnDimension(),
				matrice.getRowDimension() + 1);
		for (int i = 0; i < matrice.getColumnDimension(); ++i) {
			for (int j = 0; j < matrice.getRowDimension(); ++j) {
				c.set(i, j, matrice.get(i, j));
			}
		}
		for (int i = 0; i < matrice.getColumnDimension(); ++i) {
			for (int j = 0; j < matrice.getRowDimension() + 1; ++j) {
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
		for (int i = 0; i < matrice.getColumnDimension(); ++i) {
			for (int j = 0; j < matrice.getRowDimension(); ++j) {
				matrice.set(i, j, c.get(i, j));
			}
		}
		return matrice;
	}

	/**
	 * 
	 * @return le determinant de la matrice englobant tout les points.
	 */
	public double determinant(){
		return matrice.det();
	}
}
