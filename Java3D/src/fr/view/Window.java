package fr.view;

import javax.swing.JFrame;
/*
 * By badetitou
 */
public class Window extends JFrame{
	public Window() {
		super("3D Lib");
		//this.setIconImage(new ImageIcon("rsc/images/icon.png").getImage());
		//this.getContentPane().add();
		this.setBounds(200,80,800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
