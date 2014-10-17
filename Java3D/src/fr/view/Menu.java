package fr.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener{

	private final	JMenuBar menuBar;
	private final	JMenu menuFile;
	private final	JMenu menuEdit;
	private final	JMenuItem	menuFileNew;
	private final	JMenuItem	menuFileOpen;
	private final	JMenuItem	menuFileSave;
	private final	JMenuItem	menuFileSaveAs;
	private final	JMenuItem	menuFileExit;
	private final JMenu menuHelp;
	public Menu()
	{
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension((int) Window.outil.getScreenSize().getWidth(),30));

		menuFile=new JMenu("File");
		menuEdit=new JMenu("Edit");
		menuHelp=new JMenu("Help");

		menuFile.setPreferredSize(new Dimension(40,30));
		menuEdit.setPreferredSize(new Dimension(40,30));
		menuHelp.setPreferredSize(new Dimension(40,30));

		menuFileNew = new JMenuItem("New project");
		menuFileOpen = new JMenuItem("Open project");
		menuFileSave = new JMenuItem("Save");
		menuFileSaveAs = new JMenuItem("Save as..");
		menuFileExit = new JMenuItem("Exit");

		menuFile.add(menuFileNew);
		menuFile.add(menuFileOpen);
		menuFile.add(menuFileSave);
		menuFile.add(menuFileSaveAs);
		menuFile.add(menuFileExit);

		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);

		this.setLayout(new FlowLayout(0,0,0));
		this.add(menuBar);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
