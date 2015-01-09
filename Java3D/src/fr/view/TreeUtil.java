package fr.view;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeUtil {
	/**
	 * 
	 */
	protected static void addChildren(DefaultTreeModel treeModel,DefaultMutableTreeNode parentNode) {
		//Cr�ation d'un Fichier avec le nom du r�pertoire ouvert
		File selectedFile = (File)parentNode.getUserObject();

		//on supprime tout les enfants du r�pertoire ouvert
		parentNode.removeAllChildren();
		//On informe le treeModel que sa Structure � chang�
		treeModel.nodeStructureChanged(parentNode);

		//Listage du r�pertoire
		File[] children = selectedFile.listFiles();

		int x = 0;

		//On cr�e en premier les r�pertoire
		for(int i=0;i<children.length;i++){
			if(children[i].isDirectory()){
				//On insert de nouveaux neux dans le treeModel
				treeModel.insertNodeInto(new DefaultMutableTreeNode(new MyFile(children[i].getAbsolutePath())),parentNode,x);
				/*MyFile est une classe qui h�rite de File
				 * new MyFile(children[i].getAbsolutePath())) on cr�e un Fichier avec le chemin complet des fichiers qui on �t� list�.
				 * Et gr�ce a ce chemin complet on va pouvoir cr�er un DefaultMutableTreeNode
				 * 
				 */
				x++;
			}
		}
		//On cr�e les fichiers
		for(int i=0;i<children.length;i++){
			if(!children[i].isDirectory()){
				treeModel.insertNodeInto(new DefaultMutableTreeNode(new MyFile(children[i].getAbsolutePath())),parentNode,x);
				x++;
			}
		}
	}
}
