package fr.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class ProgressBar extends JPanel{
	private static JProgressBar progressBar = new JProgressBar();
	private static int count = 1, PROGBAR_MAX=100,TIMER_PAUSE = 25;
	private static SplashScreen ss;
	private static Timer progressBarTimer;
	ActionListener al = new ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent evt) {
			progressBar.setValue(count);
			if (PROGBAR_MAX == count) {
				ss.dispose();
				progressBarTimer.stop();//stop the timer
				Window w = new Window();
			}
			count++;//increase counter

		}
	};
	public ProgressBar(SplashScreen ss){
		progressBar.setMaximum(PROGBAR_MAX);
		this.ss=ss;
		this.setLayout(new FlowLayout(0,10,350));
		this.setOpaque(false);
		this.add(progressBar);
		startProgressBar();
	}

	private void startProgressBar() {
		progressBarTimer = new Timer(TIMER_PAUSE, al);
		progressBarTimer.start();
	}
}
