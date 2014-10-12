package fr.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Loïc Cette classe a pour but de lire un fichier texte contenant le
 *         code d'un objet et de le parser puis de le ranger dans une ArrayList.
 */
public class ReadText {

	private final Map<Integer,Integer[]> map;
	private Scanner scanner;
	private final String file;
	@SuppressWarnings("resource")
	public ReadText(String file) {
		map=new HashMap<Integer,Integer[]>();
		this.file=file;
		try {
			scanner = new Scanner(new File(file));
			// On boucle sur chaque champ detecté
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				System.out.println(line);
				//faites ici votre traitement
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

	public Map<Integer,Integer[]> getMap(){
		return this.map;
	}

}
