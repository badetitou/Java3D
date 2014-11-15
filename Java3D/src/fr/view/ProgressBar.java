package fr.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private MyDeskTopPane dp;
	private Thread t ;
	private Thread t2;
	public static String url="ressources/image/icosa.gts";
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
		System.out.println("cc1");
		t = new Thread() {

			@Override
			public void run() {
				dp=new MyDeskTopPane(url);
			}
		};
		System.out.println("cc2");
		t.start();
		t2=new Thread(){
			@Override
			public void run() {
				while(t.isAlive()){
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {}
				}
				ss.dispose();
				new Window(dp);

				try {
					this.sleep(50);
				} catch (InterruptedException e) {}
				BufferedImage screen=null;
				try {
					//System.out.println(Window.outil.getScreenSize());
					screen = new Robot().createScreenCapture(new Rectangle((int)Window.outil.getScreenSize().getWidth()/3-35,(int)Window.outil.getScreenSize().getHeight()/5-2,(int)MyDeskTopPane.dimension.getWidth()-35,(int)MyDeskTopPane.dimension.getHeight()-35));
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ImageIO.write(screen, "png", new File("ressources/screenshot.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		System.out.println("cc3");
		t2.start();
	}
}
