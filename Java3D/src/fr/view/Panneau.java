package fr.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import fr.model.ReadText;

public class Panneau extends JPanel{
	ReadText rt;
	public Panneau (ReadText rt){
		this.rt=rt;
		//this.setBackground(Color.GREEN);
		for (int i=0;i<rt.getFaceList().size();i++){
			this.add(rt.getFaceList().get(i));
		}
		System.out.println(rt.getFaceList().toString());
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i=0;i<rt.getFaceList().size();i++){
			rt.getFaceList().get(i).paint(g);
		}
	}
}
