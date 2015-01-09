package fr.view;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class myExpensionListener implements TreeExpansionListener {

	/**
	 * Constructeur de la Class
	 */
	public myExpensionListener() {
		super();
		// TODO Auto-generated constructor stub
	}

	//S'exécute lorsque lorsque qu'un dossier est ouvert
	public void treeExpanded(TreeExpansionEvent arg0) {
		//Appele de la fonction addChildren de la classe TreeUtil
		//Elle demande en paramètre DefaultTreeModel, DefaultMutableTreeNode
		TreeUtil.addChildren((DefaultTreeModel)((JTree)arg0.getSource()).getModel(),(DefaultMutableTreeNode)arg0.getPath().getLastPathComponent());
	}
	/*getSource est une Instance de JTree, ce qui va nous permetre de pouvoir utiliser getModel qui retourne un TreeModel
	 * qui lui pourras être casté en DefaultTreeModel.
	 * getPath donne le chemin complet du répertoire ouvert depuis la racine.
	 * getLastPathComponent retourne le nom du dernier répertoire
	 */

	//on n'effectue aucune action lorsque qu'un répertoire est fermé
	public void treeCollapsed(TreeExpansionEvent arg0) {

	}

}
