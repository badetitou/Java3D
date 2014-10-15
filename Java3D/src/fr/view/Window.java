package fr.view;

import javax.swing.JFrame;

import fr.model.Model;

/**
 * 
 * @author Loic
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame{

	Panneau panel;
	Model m;

	public Window() {
		super("3D Lib");
		//this.setIconImage(new ImageIcon("rsc/images/icon.png").getImage());
		m = new Model("ressources/image/triangle");
		panel=new Panneau(m);
		this.getContentPane().add(panel);
		this.setBounds(200,80,800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
