package fr.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Loic
 *
 */
public class Window extends JFrame{

	JPanel panel;
	public Window(JPanel panel) {
		super("3D Lib");
		//this.setIconImage(new ImageIcon("rsc/images/icon.png").getImage());
		this.panel=panel;
		this.getContentPane().add(panel);
		this.setBounds(200,80,800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
