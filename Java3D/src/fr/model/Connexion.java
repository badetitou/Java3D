package fr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;



/* La base contient une table File avec comme collones : idf, name, linkFile, linkDesc, author, dateAdd, dateLastModif, nbrOpen, nbrLine, nbrImg, nbrRea, linkTag
 * tout en varchar sauf les dates et les nbr 
 * */
 
public class Connexion {

	private String DBPath = "Chemin aux base de donnée SQLite";
	private Connection connection = null;
	private Statement statement = null;

	public Connexion(String dBPath) {
		DBPath = dBPath;
	}

	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:"+ DBPath);
			statement = connection.createStatement();
			System.out.println("Connexion a " + DBPath + " avec succès");
		} catch (Exception e) {
			System.out.println("Erreur de connexion");
			System.out.println(e.getMessage());
		}
	}


	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void createTableFile() {
		String requet = "Create table File (idf INT PRIMARY KEY, name VARCHAR(20), linkFile VARCHAR(100), linkDesc VARCHAR(100), author VARCHAR(20), dateAdd DATE, dateLastModif DATE, nbrOpen INT, nbrLine INT, nbrImg INT, nbrRea INT, linkTag VARCHAR(100))";
		try {
			statement.executeUpdate(requet);
		} catch (Exception e) {
			System.out.println("Erreur dans la requet : " + requet);
		}
	}
	
	
	public void addFile(String name, String linkFile, String linkDesc, String author, Date dateAdd, Date dateLastModif, int nbrOpen, int nbrLine, int nbrImg, int nbrRea, String linkTag) {
		int i=0;
		String requet = "SELECT MAX('idf') AS idMax FROM file";
		try {
			ResultSet rs = statement.executeQuery(requet);
			if (rs.next())
				i=rs.getInt("idMax");
		} catch (SQLException e) {
			System.out.println("Erreur dans la requet : " + requet);
		}
		String dateA=dateAdd.getYear()+"-"+dateAdd.getMonth()+"-"+dateAdd.getDay();
		String dateM=dateLastModif.getYear()+"-"+dateLastModif.getMonth()+"-"+dateLastModif.getDay();
		requet = "INSERT INTO file VALUES('"
				+ i 	+"','"
				+ name +"','"
				+ linkFile +"','"
				+ linkDesc +"','"
				+ author +"','"
				+ dateA +"','"
				+ dateM +"','"
				+ nbrOpen +"','"
				+ nbrLine +"','"
				+ nbrImg +"','"
				+ nbrRea +"','"
				+ linkTag +"')";
		try {
			statement.executeUpdate(requet);
		} catch (Exception e) {
			System.out.println("Erreur dans la requet : " + requet);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public ResultSet query(String requet) {
		ResultSet resultat = null;
		try {
			resultat = statement.executeQuery(requet);
		} catch (SQLException e) {
			System.out.println("Erreur dans la requet : " + requet);
		}
		return resultat;
	}


	public void requet (String requet) {
		try {
			statement.executeUpdate(requet);
		} catch (Exception e) {
			System.out.println("Erreur dans la requet : " + requet);
		}
	}
}
