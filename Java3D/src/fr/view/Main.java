package fr.view;

import javax.swing.SwingUtilities;

/**
 * @author Lo�c
 * Le main du projet. Lance directement l'�cran de chargement.
 */

public class Main {
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				SplashScreen sc= new SplashScreen();
			}
		});
	}
}
