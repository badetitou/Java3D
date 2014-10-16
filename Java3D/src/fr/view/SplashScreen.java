package fr.view;

import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

public class SplashScreen extends JWindow{
	private static JProgressBar progressBar = new JProgressBar();
	private static int count = 1, TIMER_PAUSE = 25,PROGBAR_MAX=100;
	private static SplashScreen splashScreen;
	private static Timer progressBarTimer;
	ActionListener al = new ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent evt) {
			progressBar.setValue(count);
			if (PROGBAR_MAX == count) {
				splashScreen.dispose();
				progressBarTimer.stop();//stop the timer
				Window w = new Window();
			}
			count++;//increase counter

		}
	};

	public SplashScreen() {
		splashScreen=this;
		createSplash();
	}

	private void createSplash() {
		progressBar.setMaximum(PROGBAR_MAX);
		//container.add(progressBar, BorderLayout.SOUTH);
		//pack();
		SplashScreenPanel scp=new SplashScreenPanel();
		this.setSize(800,400);
		/*JPanel p=new JPanel();
		p.setLayout(new FlowLayout());
		p.add(scp);*/
		//p.add(progressBar);
		this.getContentPane().add(scp);
		//this.getContentPane().add(progressBar);
		this.setVisible(true);
		setLocationRelativeTo(null);
		startProgressBar();
	}

	private void startProgressBar() {
		progressBarTimer = new Timer(TIMER_PAUSE, al);
		progressBarTimer.start();
	}
}
