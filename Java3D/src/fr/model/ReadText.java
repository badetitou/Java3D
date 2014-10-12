package fr.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Loïc Cette classe a pour but de lire un fichier texte contenant le
 *         code d'un objet et de le parser puis de le ranger dans une ArrayList.
 */
public class ReadText {

	private List<String> list = new ArrayList<String>();
	
	public ReadText() {
		try {
			InputStream is = new FileInputStream("ressources/image/tetra");
			Reader r = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(r);
			while(br.ready()){
				String traitement = br.readLine();
				int current = 0;
				for(int i = 0;i<traitement.length(); ++i){
					if (traitement.charAt(i) == ' '){
						list.add(traitement.substring(current, i));
						current = i;
					}
				}
				list.add(traitement.substring(current,traitement.length()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> getList(){
		return this.list;
	}

}
