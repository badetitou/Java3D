package fr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
