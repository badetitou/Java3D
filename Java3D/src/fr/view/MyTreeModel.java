package fr.view;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MyTreeModel extends DefaultTreeModel {

	/**
	 * 
	 */
	public MyTreeModel(String file) {
		//création du premier neux
		super(new DefaultMutableTreeNode(new MyFile("fichiers"+File.separator+file)));
		//		Appele de la fonction addChildren de la classe TreeUtil
		//Elle demande en paramètre DefaultTreeModel, DefaultMutableTreeNode
		TreeUtil.addChildren(this,(DefaultMutableTreeNode)getRoot());
		/*
		 * This représente cette class
		 * getRoot est une méthode de la classe DefaultTreeModel
		 */
	}

	@Override
	public boolean isLeaf(Object arg0) {
		return !((File)((DefaultMutableTreeNode)arg0).getUserObject()).isDirectory();
		/*
		 * isLeaf permet de savoir si l'objet passé en paramètre est une feuille ou non.
		 * Et ici on va testé si cette objet est un répertoire ou non
		 * on caste notre argument en File afin d'utiliser la methode isDirectory()
		 */
	}
}
