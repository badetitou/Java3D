package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


/**
 * @author Loïc
 * Traitement de la barre de progression de l'écran de chargement.
 * TIMER_PAUSE = temps pour arriver jusqu'à 100%
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
	//public static String url="ressources/image/icosa.gts";
	public ProgressBar(SplashScreen ss){
		progressBar.setMaximum(PROGBAR_MAX);
		progressBar.setBorderPainted(false);
		progressBar.setBackground(Color.lightGray);
		progressBar.setPreferredSize(new Dimension(180,18));
		progressBar.setIndeterminate(true);
		ProgressBar.ss=ss;
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
				while(t.isAlive()){
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {}
				}
				ss.dispose();
				new Window();
			}
		};
		t2.start();
	}
}
