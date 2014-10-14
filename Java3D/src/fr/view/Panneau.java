package fr.view;

import javax.swing.JPanel;

import fr.model.Model;


@SuppressWarnings("serial")
public class Panneau extends JPanel{
	Model m;
	public Panneau (Model m){
		this.m=m;
		System.out.println(m.getFace());
		//this.setBackground(Color.GREEN);
		for (int i=0;i<m.getFace().size();i++){
			//this.add(m.getFace().get(i));
		}
		
	}
}
