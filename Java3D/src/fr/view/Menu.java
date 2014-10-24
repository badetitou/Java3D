package fr.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Menu extends JMenuBar implements ActionListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final	JMenu menuFile;
	private final	JMenu menuEdit;
	private final	JMenuItem	menuFileNew;
	private final	JMenuItem	menuFileOpen;
	private final	JMenuItem	menuFileSave;
	private final	JMenuItem	menuFileSaveAs;
	private final	JMenuItem	menuFileExit;
	private final 	JMenuItem 	menuEditZoom;
	private final 	JMenuItem 	menuEditRotate;
	private final JMenu menuHelp;
	public Menu()
	{
		this.setPreferredSize(new Dimension((int) Window.outil.getScreenSize().getWidth(),30));

		menuFile=new JMenu("File");
		menuEdit=new JMenu("Edit");
		menuHelp=new JMenu("Help");

		menuFile.setPreferredSize(new Dimension(40,30));
		menuEdit.setPreferredSize(new Dimension(40,30));
		menuHelp.setPreferredSize(new Dimension(40,30));

		menuFileNew = new JMenuItem("New project");
		menuFileOpen = new JMenuItem("Open project..");
		menuFileSave = new JMenuItem("Save..");
		menuFileSaveAs = new JMenuItem("Save as..");
		menuFileExit = new JMenuItem("Exit");

		menuFileNew.setMnemonic(KeyEvent.VK_H);
		menuFile.setMnemonic(KeyEvent.VK_F);


		menuEditZoom = new JMenuItem("Zoom");
		menuEditRotate = new JMenuItem("Rotate");


		menuFile.add(menuFileNew);
		menuFile.add(menuFileOpen);
		menuFile.add(menuFileSave);
		menuFile.add(menuFileSaveAs);
		menuFile.addSeparator();
		menuFile.add(menuFileExit);

		menuEdit.add(menuEditZoom);
		menuEdit.add(menuEditRotate);

		this.add(menuFile);
		this.add(menuEdit);
		this.add(menuHelp);

		menuFileOpen.addActionListener(this);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(menuFileOpen)) {
			JFileChooser dialogue = new JFileChooser(new File("."));
			File fichier;
			dialogue.showOpenDialog(null);
			fichier = dialogue.getSelectedFile();
			System.out.println(fichier);
		}
	}
}
