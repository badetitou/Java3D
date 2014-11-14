package fr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


 
public class OutilsBdd {

	private String DBPath = "Chemin aux base de donnée SQLite";
	private Connection connection = null;
	private Statement statement = null;

	public OutilsBdd(String dBPath) {
		DBPath = dBPath;
	}

	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:"+ DBPath);
			statement = connection.createStatement();
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
			System.out.println("Erreur lors de la fermeture de la base");
			System.out.println(e.getMessage());
		}
	}

	
	public void addFile(String name, String linkFile, String desc, String author, Date dateAdd, Date dateLastModif, int nbrOpen, int nbrImg, int nbrModif, String linkImg, int size) {
		String dateA=dateAdd.getYear()+"-"+dateAdd.getMonth()+"-"+dateAdd.getDay();
		String dateM=dateLastModif.getYear()+"-"+dateLastModif.getMonth()+"-"+dateLastModif.getDay();
		String requet = "INSERT INTO files VALUES('"
				+ name +"','"
				+ linkFile +"','"
				+ desc +"','"
				+ author +"','"
				+ dateA +"','"
				+ dateM +"','"
				+ nbrOpen +"','"
				+ nbrImg +"','"
				+ nbrModif +"','"
				+ linkImg +"','"
				+ size +")";
		try {
			statement.executeUpdate(requet);
		} catch (Exception e) {
			System.out.println("Erreur dans la requet : " + requet);
			System.out.println(e.getMessage());
		}
	}
	
	public void addTag(String tag, String file) {
		String requet = "INSERT INTO tags VALUES('"
				+ tag +"','"
				+ file +")";
		try {
			statement.executeUpdate(requet);
		} catch (Exception e) {
			System.out.println("Erreur dans la requet : " + requet);
			System.out.println(e.getMessage());
		}
	}
	
	public String getLinkFile (String name) {
		String query = "SELECT linkFile FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getString("linkFile");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getLinkFile");
    		System.out.println(e.getMessage());    		
    		return "";
    	}
	}
	
	public String getDesc (String name) {
		String query = "SELECT desc FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getString("desc");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getDesc");
    		System.out.println(e.getMessage());    		
    		return "";
    	}
	}
	
	public String getAuthor (String name) {
		String query = "SELECT author FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getString("author");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getAuthor");
    		System.out.println(e.getMessage());    		
    		return "";
    	}
	}
	
	public Date getDateAdd (String name) {
		String query = "SELECT addDate FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getDate("addDate");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getDateAdd");
    		System.out.println(e.getMessage());    		
    		return null;
    	}
	}
	
	public Date getDateLastModif (String name) {
		String query = "SELECT lastModifDate FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getDate("lastModifDate");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getDateLastModif");
    		System.out.println(e.getMessage());    		
    		return null;
    	}
	}
	
	public int getnbrOppen (String name) {
		String query = "SELECT nbrOppen FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getInt("nbrOppen");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getLinkFile");
    		System.out.println(e.getMessage());    		
    		return 0;
    	}
	}
	
	public int getnbrImg (String name) {
		String query = "SELECT nbrImg FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getInt("nbrImg");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getImg");
    		System.out.println(e.getMessage());    		
    		return 0;
    	}
	}
	
	public int getnbrModif (String name) {
		String query = "SELECT nbrModif FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getInt("nbrModif");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getNbrModif");
    		System.out.println(e.getMessage());    		
    		return 0;
    	}
	}
	
	public String getLinkImg (String name) {
		String query = "SELECT linkImg FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getString("linkImg");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getLinkImg");
    		System.out.println(e.getMessage());    		
    		return "";
    	}
	}
	
	public int getSize (String name) {
		String query = "SELECT size FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		return rs.getInt("size");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getSize");
    		System.out.println(e.getMessage());    		
    		return 0;
    	}
	}
}
