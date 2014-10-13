package fr.view;

import fr.model.ReadText;


public class Main {


	public static void main(String args[]) {
		ReadText rt = new ReadText("ressources/image/tetra");
		System.out.println(rt.getPointList().toString());
		//Window w = new Window();

	}
}
