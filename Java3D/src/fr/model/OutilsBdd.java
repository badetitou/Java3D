package fr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;


 
public class OutilsBdd {

	private String DBPath = "Chemin aux base de donnée SQLite";
	private Connection connection = null;
	private Statement statement = null;

	public OutilsBdd(String dBPath) {
		DBPath = dBPath;
	}

	private void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:"+ DBPath);
			statement = connection.createStatement();
		} catch (Exception e) {
			System.out.println("Erreur de connexion");
			System.out.println(e.getMessage());
		}
	}


	private void close() {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Erreur lors de la fermeture de la base");
			System.out.println(e.getMessage());
		}
	}
	
	public boolean estPresent(String name) {
		this.connect();
		String query = "SELECT * FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		//Return le rs.next() faisait bug le bordel, à voir(?)
    		boolean b = rs.next();
    		this.close();
    		return b;
    	} catch (Exception e) {
    		System.out.println("Erreur dans estPresent");
    		System.out.println(e.getMessage());
    		this.close();
    		return false;
    	}
	}
	
	public String[] getData() {
		this.connect();
		String query = "SELECT COUNT(name) AS i from files";
	   	try {
	   		ResultSet rs = statement.executeQuery(query);
	   		int i = rs.getInt("i");
	   		String[] data = new String[i];
	   		query = "SELECT name from files";
	   		rs = statement.executeQuery(query);
	   		i=0;
	   		while (rs.next()) {
	   			data[i]=rs.getString("name");
	   			i++;
	   		}
	   		this.close();
	   		return data;
	   	} catch (Exception e) {
	   		System.out.println("Erreur dans getData");
	   		System.out.println(e.getMessage());
	   		this.close();
	   		return null;
	   	}
	}
	
	public void addFile(String name, String linkFile, String desc, String author, Date dateAdd, Date dateLastModif, int nbrOpen, int nbrImg, int nbrModif, String linkImg, int size) {
		this.connect();
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
		this.close();
	}
	
	public void addTag(String tag, String file) {
		this.connect();
		String requet = "INSERT INTO tags VALUES('"
				+ tag +"','"
				+ file +")";
		try {
			statement.executeUpdate(requet);
		} catch (Exception e) {
			System.out.println("Erreur dans la requet : " + requet);
			System.out.println(e.getMessage());
		}
		this.close();
	}
	
	public String getLinkFile (String name) {
		this.connect();
		String query = "SELECT linkFile FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		String lien = rs.getString(1);
    		System.out.println(lien);
    		this.close();
    		return lien;
    	} catch (Exception e) {
    		System.out.println("Erreur dans getLinkFile");
    		System.out.println(e.getMessage());
    		this.close();
    		return "";
    	}
    	
	}
	
	public String getDesc (String name) {
		this.connect();
		String query = "SELECT desc FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		this.close();
    		return rs.getString("desc");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getDesc");
    		System.out.println(e.getMessage());    		
    		this.close();
    		return "";
    	}
	}
	
	public String getAuthor (String name) {
		this.connect();
		String query = "SELECT author FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		this.close();
    		return rs.getString("author");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getAuthor");
    		System.out.println(e.getMessage());    		
    		this.close();
    		return "";
    	}
	}
	
	public Date getDateAdd (String name) {
		this.connect();
		String query = "SELECT addDate FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		this.close();
    		return rs.getDate("addDate");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getDateAdd");
    		System.out.println(e.getMessage());    		
    		this.close();
    		return null;
    	}
	}
	
	public Date getDateLastModif (String name) {
		this.connect();
		String query = "SELECT lastModifDate FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		this.close();
    		return rs.getDate("lastModifDate");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getDateLastModif");
    		System.out.println(e.getMessage());    		
    		this.close();
    		return null;
    	}
	}
	
	public int getnbrOppen (String name) {
		this.connect();
		String query = "SELECT nbrOppen FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		this.close();
    		return rs.getInt("nbrOppen");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getLinkFile");
    		System.out.println(e.getMessage());    		
    		this.close();
    		return 0;
    	}
	}
	
	public int getnbrImg (String name) {
		this.connect();
		String query = "SELECT nbrImg FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		this.close();
    		return rs.getInt("nbrImg");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getImg");
    		System.out.println(e.getMessage());    		
    		this.close();
    		return 0;
    	}
	}
	
	public int getnbrModif (String name) {
		this.connect();
		String query = "SELECT nbrModif FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		this.close();
    		return rs.getInt("nbrModif");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getNbrModif");
    		System.out.println(e.getMessage());    		
    		this.close();
    		return 0;
    	}
	}
	
	public String getLinkImg (String name) {
		this.connect();
		String query = "SELECT linkImg FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		this.close();
    		return rs.getString("linkImg");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getLinkImg");
    		System.out.println(e.getMessage());    		
    		this.close();
    		return "";
    	}
	}
	
	public int getSize (String name) {
		this.connect();
		String query = "SELECT size FROM files WHERE name='"+name+"'";
    	try {
    		ResultSet rs = statement.executeQuery(query);
    		this.close();
    		return rs.getInt("size");    		
    	} catch (Exception e) {
    		System.out.println("Erreur dans getSize");
    		System.out.println(e.getMessage());    		
    		this.close();
    		return 0;
    	}
	}
}
