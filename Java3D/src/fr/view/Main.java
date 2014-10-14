package fr.view;

import javax.swing.JPanel;

import fr.model.ReadText;


public class Main {


	public static void main(String args[]) {
		ReadText rt = new ReadText("ressources/image/tetra");
		System.out.println(rt.getFaceList().toString());
		JPanel p = new JPanel();
		for (int i=0;i<rt.getFaceList().size();i++){
			//rt.getFaceList().get(i).paint(g);
		}
		//Window w = new Window();

	}
}
