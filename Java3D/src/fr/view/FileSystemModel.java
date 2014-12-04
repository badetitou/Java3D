package fr.view;

import java.io.File;
import java.util.Vector;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileSystemModel implements TreeModel{

	private final String root; // The root identifier

	private final Vector listeners; // Declare the listeners vector


	public FileSystemModel() {

		File tempFile = new File("fichiers"+File.separator);
		root = tempFile.getParent();

		listeners = new Vector();
	}

	public void addTreeModelListener(TreeModelListener l) {
		if (l != null && !listeners.contains(l)) {
			listeners.addElement(l);
		}

	}

	public Object getChild(Object parent, int index) {
		File directory = (File) parent;
		String[] directoryMembers = directory.list();
		return (new File(directory, directoryMembers[index]));
	}

	public int getChildCount(Object parent) {
		File fileSystemMember = (File) parent;
		if (fileSystemMember.isDirectory()) {
			String[] directoryMembers = fileSystemMember.list();
			return directoryMembers.length;
		}

		else {

			return 0;
		}
	}

	public int getIndexOfChild(Object parent, Object child) {
		File directory = (File) parent;
		File directoryMember = (File) child;
		String[] directoryMemberNames = directory.list();
		int result = -1;

		for (int i = 0; i < directoryMemberNames.length; ++i) {
			if (directoryMember.getName().equals(directoryMemberNames[i])) {
				result = i;
				break;
			}
		}

		return result;
	}

	public Object getRoot() {
		return (new File(root));
	}

	public boolean isLeaf(Object node) {
		return ((File) node).isFile();
	}

	public void removeTreeModelListener(TreeModelListener arg0) {
		// TODO Auto-generated method stub

	}

	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub

	}


}
