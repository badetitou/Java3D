package fr.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public ReadText(String file) {
		pointList=new ArrayList<Point>();
		faceList=new ArrayList<Face>();
		Map<Integer,CouplePoint> map;
		this.file=file;
		try {
			scanner = new Scanner(new File(file));
			int i=0;
			int j=0;
			double nbPoints=0;
			double nbSegments=0;
			double nbFaces=0;
			String line="";
			map = new HashMap<Integer,CouplePoint>();

			// On boucle sur chaque champ detecté
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (i==0){
					nbPoints=extractLine(line)[0];
					nbSegments=extractLine(line)[1];
					nbFaces=extractLine(line)[2];
				}

				else if (i<=nbPoints){
					pointList.add(new Point(extractLine(line)[0], extractLine(line)[1], extractLine(line)[2]));
				}

				else if (i> nbPoints && i<= nbPoints+nbSegments){
					map.put(j, new CouplePoint(pointList.get((int) (extractLine(line)[0]-1)),pointList.get((int) (extractLine(line)[1]-1))));
					j++;
				}

				else {
					Point p3=null;
					if (!((map.get((int)extractLine(line)[0]-1).getP1().equals(map.get((int)extractLine(line)[2]-1).getP1())))&& !(map.get((int)extractLine(line)[0]-1).getP2().equals((map.get((int)extractLine(line)[2]-1).getP1())))){
						p3=map.get((int)extractLine(line)[2]-1).getP1();
					}
					else {
						p3=map.get((int)extractLine(line)[2]-1).getP2();
					}
					faceList.add(new Face(map.get((int)extractLine(line)[0]-1).getP1(),map.get((int)extractLine(line)[0]-1).getP2(),p3));
				}
				i++;
			}
			System.out.println(faceList.toString());

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable");
			e.printStackTrace();
		}

	}

	public double[] extractLine(String line){
		double [] tab = new double [3];
		int j=0;
		int k=0;
		for (int i=0;i<line.length();i++){
			if(line.charAt(i)==' '){
				tab[k]=Double.parseDouble(line.substring(j, i));
				j=i+1;
				k++;
			}
		}
		tab[k]=Double.parseDouble(line.substring(j,line.length()));

		return tab;
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
