package fr.model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Loïc Cette classe a pour but de lire un fichier texte contenant le
 *         code d'un objet et de le parser puis de le ranger dans une ArrayList.
 */
public class ReadText {

	private final List<Point> pointList;
	private final List<Face> faceList;
	private Scanner scanner;
	private final String file;
	private boolean corrupt;

	/**
	 * 
	 * @param String file
	 * Prend l'url d'un fichier en paramètre et récupère tout le contenu de celui-ci en stockant les points et les faces dans deux ArrayLists.
	 * 
	 */

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

					if(this.corrupt){
						JOptionPane.showMessageDialog(new JFrame(),"Fichier Corrumpu","Error",JOptionPane.ERROR_MESSAGE);
						break;
					}
					faceList.add(new Face(map.get((int)extractLine(line)[0]-1).getP1(),map.get((int)extractLine(line)[0]-1).getP2(),p3,new Color((int)extractLine(line)[3],(int)extractLine(line)[4],(int)extractLine(line)[5])));
				}
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable");
			JOptionPane.showMessageDialog(new JFrame(),"Fichier introuvable","Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param String line
	 * Prend la chaîne de caractère passée en paramètre et retourne un tableau de double contenant les divers points/segments/faces
	 *       du fichier en entrée.
	 */

	public double[] extractLine(String line){
		double [] tab = new double [6];
		int j=0;
		int k=0;
		for (int i=0;i<line.length();i++){
			if(line.charAt(i)==' '){
				if(k==3 || k==4 || k==5){
					//System.out.println(line.substring(j, i));
					if(validColor(Integer.parseInt(line.substring(j, i)))){
						tab[k]=Double.parseDouble(line.substring(j, i));
					}
					else {
						this.corrupt=true;
					}
				}
				else {
					tab[k]=Double.parseDouble(line.substring(j, i));
				}
				j=i+1;
				k++;
			}
		}
		if(k==3 || k==4 || k==5){
			if(validColor(Integer.parseInt(line.substring(j, line.length())))){
				tab[k]=Double.parseDouble(line.substring(j, line.length()));
			}
			else {
				this.corrupt=true;
			}
			//System.out.println(tab[3]+" | "+tab[4]+" | "+tab[5]);
		}
		tab[k]=Double.parseDouble(line.substring(j,line.length()));

		return tab;
	}

	public boolean validColor(int n){
		if(n < 0 || n>255)
			return false;
		return true;
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
