package fr.model;

import Jama.Matrix;

public class Matrice {
	Matrix points;
	
	public Matrice(double[][] ensemblePoints){
		points = new Matrix(ensemblePoints);
	}
	
	
}
