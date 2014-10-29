package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;


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
	private static int count = 1, PROGBAR_MAX=100,TIMER_PAUSE = 20;
	private static SplashScreen ss;
	private static Timer progressBarTimer;
	private MyDeskTopPane dp;
	ActionListener al = new ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent evt) {
			progressBar.setValue(count);
			if (PROGBAR_MAX == count) {
				ss.dispose();
				progressBarTimer.stop();//stop the timer
				new Window(dp);
			}
			progressBar.setStringPainted(true);
			progressBar.setString(count+"%");
			count++;//increase counter

		}
	};
	public ProgressBar(SplashScreen ss){
		progressBar.setMaximum(PROGBAR_MAX);
		progressBar.setBorderPainted(false);
		progressBar.setBackground(Color.lightGray);
		progressBar.setPreferredSize(new Dimension(180,18));
		//progressBar.setForeground(Color.DARK_GRAY);
		ProgressBar.ss=ss;
		this.setLayout(new FlowLayout(0,0,350));
		this.setOpaque(false);
		this.add(progressBar);
		startProgressBar();
	}

	private void startProgressBar() {
		progressBarTimer = new Timer(TIMER_PAUSE, al);
		progressBarTimer.start();
		dp=new MyDeskTopPane();

	}
}
