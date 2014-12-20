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
 *         code d'un objet et de le parser puis de le ranger dans une ArrayList
 */
public class ReadText {

	private final List<Point> pointList;
	private final List<Face> faceList;
	private Scanner scanner;
	private final String file;
	private boolean corrupt;
	Map<Integer,CouplePoint> segment;
	/**
	 * 
	 * @param String file
	 * Prend l'url d'un fichier en paramètre et récupère tout le contenu de celui-ci en stockant les points et les faces dans deux ArrayLists.
	 * 
	 */

	public ReadText(String file) {
		pointList=new ArrayList<Point>();
		faceList=new ArrayList<Face>();
		this.file=file;
		try {
			scanner = new Scanner(new File(file));
			int i=0;
			int j=0;
			int nbPoints=0;
			int nbSegments=0;
			int nbFaces=0;
			String line="";
			double tab[];
			segment = new HashMap<Integer,CouplePoint>();

			// On boucle sur chaque champ detecté

			int positionBoucle = 0;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (i==0){
					int z=0;
					int g=0;
					int tab0[] = new int [3];
					for (int v=0;v<line.length();v++){
						if(line.charAt(v)==' ' && g <= 2){
							tab0[g]=Integer.parseInt(line.substring(z, v));
							z=v+1;
							g++;
						}
					}
					if(g==2)
						tab0[g]=Integer.parseInt(line.substring(z,line.length()));
					nbPoints=tab0[0];
					nbSegments=tab0[1];
					nbFaces=tab0[2];
				}
				else if (i<=nbPoints){
					tab=extractLine(line);
					pointList.add(new Point(tab[0], tab[1], tab[2]));
					pointList.get(positionBoucle).setPosition(positionBoucle);
					++positionBoucle;
				}
				else if (i> nbPoints && i<= nbPoints+nbSegments){
					tab=extractLine(line);
					segment.put(j, new CouplePoint(pointList.get((int) (tab[0]-1)),pointList.get((int) (tab[1]-1)), tab[0], tab[1]));
					j++;
				}
				else {
					tab=extractLine(line);
					Point p3=null;
					if (!((segment.get((int)tab[0]-1).getP1().equals(segment.get((int)tab[2]-1).getP1())))&& !(segment.get((int)tab[0]-1).getP2().equals((segment.get((int)tab[2]-1).getP1())))){
						p3=segment.get((int)tab[2]-1).getP1();
					}
					else {
						p3=segment.get((int)tab[2]-1).getP2();
					}

					if(this.corrupt){
						//JOptionPane.showMessageDialog(new JFrame(),"Fichier Corrumpu","Error",JOptionPane.ERROR_MESSAGE);
						break;
					}
					faceList.add(new Face(tab[0], tab[1] , tab[2] , segment.get((int)tab[0]-1).getP1(),segment.get((int)tab[0]-1).getP2(),p3 ,new Color((int)tab[3],(int)tab[4],(int)tab[5])));
				}
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("fichier introuvable");
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
		try {
			int j=0;
			int k=0;
			for (int i=0;i<line.length();i++){
				if(line.charAt(i)==' '){
					if(k==3 || k==4 || k==5){
						if(line.substring(j, i)!=null){
							if(validColor(Integer.parseInt(line.substring(j, i)))){
								tab[k]=Double.parseDouble(line.substring(j, i));
							}
							else {
								this.corrupt=true;
							}
						}
						else {
							tab[k]=0;
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
				if(line.substring(j, line.length())!=null){
					if(validColor(Integer.parseInt(line.substring(j, line.length())))){
						tab[k]=Double.parseDouble(line.substring(j, line.length()));
					}
					else {
						this.corrupt=true;
					}
				}
				else {
					tab[k]=0;
				}
				//System.out.println(tab[3]+" | "+tab[4]+" | "+tab[5]);
			}

			tab[k]=Double.parseDouble(line.substring(j,line.length()));
		}catch(Exception e){
			JOptionPane.showMessageDialog(new JFrame(),"Fichier corrompu","Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		};

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

	public Map<Integer, CouplePoint> getSegment() {
		return segment;
	}


}
