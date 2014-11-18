package fr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
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

	public void addFile(String name, String linkFile, String desc, String author, int nbrOpen, int nbrImg, int nbrModif, String linkImg, int size) {
		this.connect();
		Calendar rightNow = Calendar.getInstance();
    	String date = rightNow.get(Calendar.YEAR)+"-"+rightNow.get(Calendar.MONTH)+"-"+rightNow.get(Calendar.DAY_OF_MONTH);
		String requet = "INSERT INTO files VALUES('"
				+ name +"','"
				+ linkFile +"','"
				+ desc +"','"
				+ author +"','"
				+ date +"','"
				+ date +"','"
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
			this.close();
		}
		this.close();
	}
	
	public String getLastFiles () {
		this.connect();
		String query = "SELECT name FROM files ORDER BY lastModifDate DESC";
		try {
			ResultSet rs = statement.executeQuery(query);
			String name = rs.getString(1);
			this.close();
			return name;
		} catch (Exception e) {
			System.out.println("Erreur dans getLinkFile");
			System.out.println(e.getMessage());
			this.close();
			return "";
		}
	}
	
	public void updateFile(String name, String desc, Date dateLastModif, int nbrOpen, int nbrImg, int nbrModif, String linkImg, int size) {
		this.connect();
		Calendar rightNow = Calendar.getInstance();
    	String date = rightNow.get(Calendar.YEAR)+"-"+rightNow.get(Calendar.MONTH)+"-"+rightNow.get(Calendar.DAY_OF_MONTH);
		String req = "UPDATE files SET desc='"
				+ desc +"',lastModifDate='"
				+ date +"',nbrOpen='"
				+ nbrOpen +"',nbrImg='"
				+ nbrImg +"',nbrModif='"
				+ nbrModif+"'linkImg='"
				+ linkImg+"' WHERE name='"+name+"'";
		try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans UpdateFile");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
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
			this.close();
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
	
	public void setLinkFile (String link, String name) {
		this.connect();
		String req = "UPDATE files SET linkFile = "+link+" WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setLinkFile");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }
	
	public String getDesc (String name) {
		this.connect();
		String query = "SELECT desc FROM files WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			String description=rs.getString(1);
			this.close();
			return description;
		} catch (Exception e) {
			System.out.println("Erreur dans getDesc");
			System.out.println(e.getMessage());
			this.close();
			return "";
		}
	}
	
	public void setDesc (String desc, String name) {
		this.connect();
    	String req = "UPDATE files SET desc = "+desc+" WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setDesc");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }


	public String getAuthor (String name) {
		this.connect();
		String query = "SELECT author FROM files WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			String auteur=rs.getString(1);
			this.close();
			return auteur;
		} catch (Exception e) {
			System.out.println("Erreur dans getAuthor");
			System.out.println(e.getMessage());
			this.close();
			return "";
		}
	}
	
	public void setAuthor (String author, String name) {
		this.connect();
    	String req = "UPDATE files SET author = "+author+" WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setAuthor");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }

	public String getDateAdd (String name) {
		this.connect();
		String query = "SELECT addDate FROM files WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			String date = rs.getString(1);
			this.close();
			return date;
		} catch (Exception e) {
			System.out.println("Erreur dans getDateAdd");
			System.out.println(e.getMessage());
			this.close();
			return null;
		}
	}
	
	public void setDateAdd (String name) {
		this.connect();
		Calendar rightNow = Calendar.getInstance();
    	String req = "UPDATE files SET addDate = '"+rightNow.get(Calendar.YEAR)+"-"+rightNow.get(Calendar.MONTH)+"-"+rightNow.get(Calendar.DAY_OF_MONTH)+"' WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setDateAdd");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }

	public String getDateLastModif (String name) {
		this.connect();
		String query = "SELECT lastModifDate FROM files WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			String date = rs.getString(1);
			this.close();
			return date;
		} catch (Exception e) {
			System.out.println("Erreur dans getDateLastModif");
			System.out.println(e.getMessage());
			this.close();
			return null;
		}
	}

	public void setDateLastModif (String name) {
		this.connect();
		Calendar rightNow = Calendar.getInstance();
    	String req = "UPDATE files SET lastModifDate = '"+rightNow.get(Calendar.YEAR)+"-"+rightNow.get(Calendar.MONTH)+"-"+rightNow.get(Calendar.DAY_OF_MONTH)+"' WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setDateLastModif");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }
	
	public int getnbrOpen (String name) {
		this.connect();
		String query = "SELECT nbrOpen FROM files WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			int nb=rs.getInt(1);
			this.close();
			return nb;
		} catch (Exception e) {
			System.out.println("Erreur dans getLinkFile");
			System.out.println(e.getMessage());
			this.close();
			return 0;
		}
	}

	public void setNbrOpen (int i, String name) {
		this.connect();
    	String req = "UPDATE files SET nbrOpen = "+i+" WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setNbrOpen");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }
	
	public int getnbrImg (String name) {
		this.connect();
		String query = "SELECT nbrImg FROM files WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			int nbImg=rs.getInt(1);
			this.close();
			return nbImg;
		} catch (Exception e) {
			System.out.println("Erreur dans getImg");
			System.out.println(e.getMessage());
			this.close();
			return 0;
		}
	}

	public void setNbrImg (int i, String name) {
		this.connect();
    	String req = "UPDATE files SET nbrImg = "+i+" WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setNbrImg");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }
	
	public int getnbrModif (String name) {
		this.connect();
		String query = "SELECT nbrModif FROM files WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			int nbModif=rs.getInt(1);
			this.close();
			return nbModif;
		} catch (Exception e) {
			System.out.println("Erreur dans getNbrModif");
			System.out.println(e.getMessage());
			this.close();
			return 0;
		}
	}

	public void setNbrModif (int i, String name) {
		this.connect();
    	String req = "UPDATE files SET nbrModif = "+i+" WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setNbrModif");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }
	
	public String getLinkImg (String name) {
		this.connect();
		String query = "SELECT linkImg FROM files WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			String linkImg=rs.getString(1);
			this.close();
			return linkImg;
		} catch (Exception e) {
			System.out.println("Erreur dans getLinkImg");
			System.out.println(e.getMessage());
			this.close();
			return "";
		}
	}
	
	public void setLinkImg (String link, String name) {
		this.connect();
    	String req = "UPDATE files SET linkImg = "+link+" WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setLinkImg");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }

	public int getSize (String name) {
		this.connect();
		String query = "SELECT size FROM files WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			int size=rs.getInt(1);
			this.close();
			return size;
		} catch (Exception e) {
			System.out.println("Erreur dans getSize");
			System.out.println(e.getMessage());
			this.close();
			return 0;
		}
	}

	public void setSize (int i, String name) {
		this.connect();
    	String req = "UPDATE files SET size = "+i+" WHERE name='"+name+"'";
    	try {
    		statement.executeUpdate(req);
			this.close();
    	} catch (Exception e) {
    		System.out.println("Erreur dans setSize");
    		System.out.println(e.getMessage());    		
			this.close();
    	}
    }
	
}
