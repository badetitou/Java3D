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

	//S'ex�cute lorsque lorsque qu'un dossier est ouvert
	public void treeExpanded(TreeExpansionEvent arg0) {
		//Appele de la fonction addChildren de la classe TreeUtil
		//Elle demande en param�tre DefaultTreeModel, DefaultMutableTreeNode
		TreeUtil.addChildren((DefaultTreeModel)((JTree)arg0.getSource()).getModel(),(DefaultMutableTreeNode)arg0.getPath().getLastPathComponent());
	}
	/*getSource est une Instance de JTree, ce qui va nous permetre de pouvoir utiliser getModel qui retourne un TreeModel
	 * qui lui pourras �tre cast� en DefaultTreeModel.
	 * getPath donne le chemin complet du r�pertoire ouvert depuis la racine.
	 * getLastPathComponent retourne le nom du dernier r�pertoire
	 */

	//on n'effectue aucune action lorsque qu'un r�pertoire est ferm�
	public void treeCollapsed(TreeExpansionEvent arg0) {

	}

}
