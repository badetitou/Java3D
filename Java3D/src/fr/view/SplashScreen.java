package fr.view;

import javax.swing.JWindow;

/**
 * @author Loïc
 * Charge l'écran de chargement du logiciel
 * 
 */

public class SplashScreen extends JWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SplashScreen splashScreen;

	public SplashScreen() {
		splashScreen=this;
		createSplash();
	}

	private void createSplash() {
		SplashScreenPanel scp=new SplashScreenPanel(SplashScreen.splashScreen);
		this.setSize(800,400);
		this.getContentPane().add(scp);
		this.setVisible(true);
		setLocationRelativeTo(null);
	}

}
