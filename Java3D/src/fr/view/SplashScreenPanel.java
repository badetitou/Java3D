package fr.view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Loïc
 * Charge l'écran de chargement du logiciel
 * 
 */

public class SplashScreenPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	public SplashScreenPanel(SplashScreen ss){

		try {
			img=ImageIO.read(new File("ressources/image/800x400.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new FlowLayout(0,70,330));
		ProgressBar progressBar = new ProgressBar(ss);
		this.add(progressBar);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,this);
	}

}
