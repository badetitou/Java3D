package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import fr.model.OutilsBdd;


/**
 * @author Lo�c
 * Traitement de la barre de progression de l'�cran de chargement.
 * TIMER_PAUSE = temps pour arriver jusqu'� 100%
 * 
 */

public class ProgressBar extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JProgressBar progressBar = new JProgressBar();
	private static int PROGBAR_MAX=100;
	private static SplashScreen ss;
	private Thread t ;
	private Thread t2;
	private final ArrayList<MyDeskTopPane> listeFichiersRecents;
	private final String[] listeLastFiles;
	OutilsBdd obdd;
	//public static String url="ressources/image/icosa.gts";
	public ProgressBar(SplashScreen ss){
		progressBar.setMaximum(PROGBAR_MAX);
		progressBar.setBorderPainted(false);
		progressBar.setBackground(Color.lightGray);
		progressBar.setPreferredSize(new Dimension(180,18));
		progressBar.setIndeterminate(true);
		obdd= new OutilsBdd("Database.db");
		ProgressBar.ss=ss;
		listeLastFiles=obdd.getLastFiles();
		//System.out.println(listeLastFiles[4]);
		listeFichiersRecents=new ArrayList<MyDeskTopPane>();
		this.setLayout(new GridLayout(2,1,0,4));
		this.setOpaque(false);
		JLabel jl = new JLabel(" Chargement des fichiers ...");
		jl.setForeground(Color.white);
		this.add(jl);
		this.add(progressBar);
		startProgressBar();
	}

	private void startProgressBar() {
		t = new Thread() {
			@Override
			public void run() {
				try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
		t2=new Thread(){
			@Override
			public void run() {
				String lastFileGoodString;
				while(t.isAlive()){
					for(int i=0;i<5;i++){
						if(listeLastFiles[i]==null){
							listeFichiersRecents.add(null);
						}
						else{
							lastFileGoodString = listeLastFiles[i];
							lastFileGoodString.replaceAll("/", File.separator);
							for(int j = 0;j<lastFileGoodString.length();++j){
								if (lastFileGoodString.charAt(j) == '\\')
									lastFileGoodString = lastFileGoodString.substring(0, j) + File.separator + lastFileGoodString.substring(j+1, lastFileGoodString.length());
							}
							listeFichiersRecents.add(new MyDeskTopPane(lastFileGoodString));
						}
					}
				}
				ss.dispose();
				new Window(listeFichiersRecents);
			}
		};
		t2.start();
	}
}
