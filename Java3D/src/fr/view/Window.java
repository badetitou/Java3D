package fr.view;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
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
	Toolkit outil;

	public Window() {
		super("3D Lib");
		outil = getToolkit();
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());
		m = new Model("ressources/image/tie.gts");
		panel=new Panneau(m);
		this.getContentPane().add(panel);
		this.setSize(outil.getScreenSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
