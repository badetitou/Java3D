package fr.view;

import javax.swing.JFrame;

import fr.model.ReadText;

/**
 * 
 * @author Loic
 *
 */
public class Window extends JFrame{

	Panneau panel;
	ReadText rt;
	public Window() {
		super("3D Lib");
		//this.setIconImage(new ImageIcon("rsc/images/icon.png").getImage());
		rt = new ReadText("ressources/image/tetra");
		panel=new Panneau(rt);
		this.getContentPane().add(panel);
		this.setBounds(200,80,800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
