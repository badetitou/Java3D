package fr.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Loïc Cette classe a pour but de lire un fichier texte contenant le
 *         code d'un objet et de le parser puis de le ranger dans une ArrayList.
 */
public class ReadText {

	private final List<Point> pointList;
	private final List<Face> faceList;
	private Scanner scanner;
	private final String file;
	@SuppressWarnings("resource")
	public ReadText(String file) {
		pointList=new ArrayList<Point>();
		faceList=new ArrayList<Face>();
		this.file=file;
		try {
			scanner = new Scanner(new File(file));
			int i=0;
			int nbPoints=0;
			int nbSegments=0;
			int nbFaces=0;
			String line="";
			// On boucle sur chaque champ detecté
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (i==0){
					nbPoints=Integer.parseInt(line.substring(0));
					nbSegments=Integer.parseInt(line.substring(2));
					nbFaces=Integer.parseInt(line.substring(4));
				}

				else if (i<=nbPoints){
					pointList.add(new Point(Integer.parseInt(line.substring(0)), Integer.parseInt(line.substring(2)), Integer.parseInt(line.substring(4))));
				}

				else if (i> nbPoints && i<= nbSegments){

				}

				else {

				}
				i++;
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable");
			e.printStackTrace();
		}

	}

	public String getFile(){
		return this.file;
	}

	public List<Point> getPointList(){
		return this.pointList;
	}

	public List<Face> getFaceList(){
		return this.faceList;
	}

}
